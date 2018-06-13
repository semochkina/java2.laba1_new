package matrixes;

public class MatrixOutOfBoundException extends MyMatrixException {
    public MatrixOutOfBoundException() {
    }

    public MatrixOutOfBoundException(String message) {
        super(message);
    }

    public MatrixOutOfBoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

