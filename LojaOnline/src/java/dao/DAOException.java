package dao;

public class DAOException extends Exception {

    public DAOException(String message, Throwable cause) {
       super(message, cause);
    }
    
    @Override
    public String getMessage() {
        return super.getMessage();
    }
    
}
