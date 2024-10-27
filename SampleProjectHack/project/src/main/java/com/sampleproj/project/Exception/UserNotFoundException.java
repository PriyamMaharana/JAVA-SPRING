package com.sampleproj.project.Exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long id) {
        super("Could not found user with ID: "+id);
    }

}
