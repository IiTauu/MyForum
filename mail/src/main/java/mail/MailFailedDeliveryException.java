package mail;

public class MailFailedDeliveryException extends MailException {
    public MailFailedDeliveryException(String message) {
        super(message);
    }
}
