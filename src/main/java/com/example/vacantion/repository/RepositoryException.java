package com.example.vacantion.repository;

/**
 *  Custom Repository exception
 */
public class RepositoryException extends Exception{

    public RepositoryException(String err){
        super(err);
    }
}
