package server.business.responders;

import base.model.User;
import base.request.LoginRequest;
import base.response.ConnectionResponse;
import base.response.LoginResponse;
import base.response.ServerResponse;
import server.ChannelManager;
import server.business.Translator;
import server.business.UserState;
import server.business.exceptions.OperationFailedException;
import server.repository.manager.RegisterManager;
import server.repository.manager.UserManager;
import server.repository.model.RegisterData;

import java.util.Objects;

public class LoginResponser implements Responser<LoginRequest> {
    @Override
    public ServerResponse respond(LoginRequest request) {
        try {
            RegisterManager registerManager = new RegisterManager();
            UserManager userManager = new UserManager();
            RegisterData data = registerManager.get(request.getAccount());

            if(data == null || !Objects.equals(data.getPassword(), request.getPassword())) {
                throw new OperationFailedException();
            }

            if(data.getState() == UserState.REGISTER) {
                throw new OperationFailedException();

            }else if(data.getState() == UserState.ONLINE) {
                ChannelManager.getChannelById(data.getId()).writeAndFlush(new ConnectionResponse());

            }else if(data.getState() == UserState.OFFLINE) {
                if(!registerManager.updateState(data.getId(), UserState.ONLINE))
                    throw new OperationFailedException();

            }else if(data.getState() == UserState.FREEZE){
                throw new OperationFailedException();
            }

            User user = new Translator().dataToUser(userManager.get(data.getId()));
            LoginResponse response = new LoginResponse(user);
            response.setSuccess(true);
            return response;

        } catch (Exception e) {
            return new LoginResponse(null);
        }
    }
}
