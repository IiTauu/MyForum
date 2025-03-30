package server.business.responders;

import base.request.RegistrationRequest;
import base.response.RegistrationResponse;
import base.response.ServerResponse;
import server.business.UserState;
import server.repository.manager.RegisterManager;
import server.repository.manager.UserManager;
import server.repository.model.RegisterData;
import server.repository.model.UserData;

import java.time.LocalDateTime;

public class RegistrationResponser implements Responser<RegistrationRequest> {
    @Override
    public ServerResponse respond(RegistrationRequest request) {
        String account = request.getAccount();
        try {
            RegisterManager registerManager = new RegisterManager();
            RegisterData data = registerManager.get(account);
            if (!data.getPassword().equals(request.getVerificationCode())) {
                throw new Exception();
            }
            data.setPassword(request.getPassword());
            data.setState(UserState.OFFLINE);
            registerManager.update(data);

            UserManager userManager = new UserManager();
            UserData userData = new UserData(
                    data.getId(),
                    request.getUsername(),
                    data.getAccount(),
                    LocalDateTime.now(),
                    request.getRegistrationDate()
            );
            userManager.add(userData);

            RegistrationResponse response = new RegistrationResponse();
            response.setSuccess(true);
            return response;
        } catch (Exception e) {
            return new RegistrationResponse();
        }
    }
}
