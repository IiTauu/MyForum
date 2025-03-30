package mail;


import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public final class MailUtil {
    private final String USER = "iitauforum@163.com";
    private final String AUTHORCODE = "SLsDwnfEaxTXN8ib";
    private final String PASSWARD = "_APUim3bdGntu2u";
    private final String HOST = "smtp.163.com";
    private final String PORT = "465";

    private Properties setProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.socketFactory.port", PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        return props;
    }

    public boolean sendMail(String recipient, String text, String subject) {
        Session session = Session.getInstance(setProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER, AUTHORCODE);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USER));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
            System.out.println("Email sent successfully!");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String sendVerification(String recipient)
            throws MailFailedDeliveryException {
        String subject = "邮箱验证";
        String verificationCode = VerificationCodeGenerator.generate();
        StringBuffer textBuffer = new StringBuffer();
        textBuffer.append("您的邮箱验证码为\n\t");
        textBuffer.append(verificationCode);
        textBuffer.append("\n请在3分钟内完成验证，且不要将验证码透露给他人。如果您没有申请验证，请忽略该邮件。");
        String text = textBuffer.toString();
        if(!sendMail(recipient,text,subject)) {
            throw new MailFailedDeliveryException("验证码发送失败！");
        }
        return verificationCode;
    }


}


