package pl.edu.wat.projektai.ai.exceptions;

public class InvalidUsernameOrPasswordException extends RuntimeException {
    public InvalidUsernameOrPasswordException(String msg){
        super(msg);
    }
}
