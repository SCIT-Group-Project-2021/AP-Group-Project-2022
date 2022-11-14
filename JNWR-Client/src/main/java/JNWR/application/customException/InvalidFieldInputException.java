package JNWR.application.customException;

public class InvalidFieldInputException extends Exception{
    public InvalidFieldInputException(String msg){
        super(msg);
    }
}