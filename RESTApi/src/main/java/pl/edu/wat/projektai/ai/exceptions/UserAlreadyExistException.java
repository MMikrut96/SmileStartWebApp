package pl.edu.wat.projektai.ai.exceptions;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String msg){
        super(msg);
    }
}
