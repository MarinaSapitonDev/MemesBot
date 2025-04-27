package exceptions;

public class TelegramException extends Exception{
    public TelegramException(String message, Throwable cause){
        super(message, cause);
    }
}
