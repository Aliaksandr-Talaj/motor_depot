package by.talai.data.exception;

public class DaoException extends Exception {

    public static final long serialVersionUID = 1L;

    public DaoException(String message, Exception e) {
        super(message, e);
    }
}

