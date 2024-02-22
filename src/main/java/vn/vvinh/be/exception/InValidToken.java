package vn.vvinh.be.exception;

public class InValidToken extends RuntimeException{
    public InValidToken(String message){
        super(message);
    }
}
