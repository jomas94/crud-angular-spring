package com.jomario.exception;

public class RecordNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
  
    public RecordNotFoundException(Long id ) {
        super("Id "+id + " not found.");
    }
    
  
    

}
