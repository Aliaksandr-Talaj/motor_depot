package by.talai.service;

public class ServiceException extends Exception{
    public static final long serialVersionUID = 1L;

    public ServiceException(String message, Exception e) {
        super(message, e);
    }
}
