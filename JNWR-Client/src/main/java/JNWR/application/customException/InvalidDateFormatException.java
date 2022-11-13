package JNWR.application.customException;

public class InvalidDateFormatException extends Exception{
    public InvalidDateFormatException(String msg){
        super(msg);
    }
}
