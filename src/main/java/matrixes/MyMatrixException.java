package matrixes;

public class MyMatrixException extends Exception{
    public MyMatrixException() {
    }

    public MyMatrixException(String message) {
        super(message);
    }

    public MyMatrixException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyMatrixException(Throwable cause) {
        super(cause);
    }
}
