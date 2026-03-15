package org.ptm.ptm.Exception;

public class EmailAlreadyExists extends RuntimeException{
    public EmailAlreadyExists(String message){
        super(message);
    }
}
