package server.repository.manager.exception;

public class NotFoundException extends DataOperationException {
    private int id = 0;
    private String account = null;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, int id) {
        super(message);
        this.id = id;
    }

    public NotFoundException(String message, String account) {
        super(message);
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }
}
