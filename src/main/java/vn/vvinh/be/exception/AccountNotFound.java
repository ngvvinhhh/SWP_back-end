package vn.vvinh.be.exception;

public class AccountNotFound extends RuntimeException{
    public AccountNotFound(String message){
        super(message);
    }
}
