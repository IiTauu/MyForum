package server.repository.manager.exception;

public class DuplicateException extends DataOperationException {
    private Byte type = 0;
    public static Byte ID = 1;
    public static Byte ACCOUNT = 2;

    public DuplicateException(String message) {
        super(message);
    }

    public DuplicateException(String message, Byte type) {
        super(message);
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
