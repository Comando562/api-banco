package com.example.apibanco.exception;

public class ClienteSrvInternalServErrorException extends RuntimeException{
    public ClienteSrvInternalServErrorException(String message){
        super(message);
    }

    public ClienteSrvInternalServErrorException(String message, Throwable cause){
        super(message);
    }
}
