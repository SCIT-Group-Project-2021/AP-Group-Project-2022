package JNWR.application.customException;

public class InvalidIdNumberException extends Exception{
    public InvalidIdNumberException(String msg){
        super(msg);
    }
}
