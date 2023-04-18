package com.jomario.exception;

public class RecordNotFoundeException extends RuntimeException {

    private static final long serialVersionUID = 1L;
  
    public RecordNotFoundeException(Long id ) {
        super("Id "+id + " not found.");
    }
    
  
    

}
