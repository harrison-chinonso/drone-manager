package chinonsoharrison.exception;

public class DispatchException extends RuntimeException {

    String message;
    Object obj;

    public DispatchException() {
        super("Failed to perform the requested action");
    }

    public DispatchException(Throwable cause) {
        super("Failed to perform the requested action", cause);
    }

    public DispatchException(String message) {
        super(message);
    }

    public DispatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public DispatchException(String message, Object obj) {
        this.message = message;
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

}
