package com.joaolucas.shopjj.exceptions;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String message){
        super(message);
    }

}
