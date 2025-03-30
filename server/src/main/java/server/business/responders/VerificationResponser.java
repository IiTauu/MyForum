package server.business.responders;

import base.request.VerificationRequest;
import base.response.RegistrationResponse;
import base.response.ServerResponse;
import base.response.VerificationResponse;
import mail.MailUtil;
import server.business.UserState;
import server.repository.manager.RegisterManager;
import server.repository.manager.exception.DuplicateException;
import server.repository.model.RegisterData;


public class VerificationResponser implements Responser<VerificationRequest> {
    @Override
    public ServerResponse respond(VerificationRequest request) {
        String account = request.getAccount();
        try {
            RegisterManager registerManager = new RegisterManager();
            int id = registerManager.add(new RegisterData(
                    0,
                    account,
                    "0",
                    UserState.REGISTER
            ));
            MailUtil mailUtil = new MailUtil();
            String verificationCode = mailUtil.sendVerification(account);
            registerManager.update(new RegisterData(
                    id,
                    account,
                    verificationCode,
                    UserState.REGISTER
            ));
            VerificationResponse response = new VerificationResponse();
            response.setSuccess(true);
            return response;
        } catch (DuplicateException e) {
            //账号已注册
            return new VerificationResponse();
        } catch (Exception e) {
            //邮箱不存在
            return new VerificationResponse();
        }
    }
}
