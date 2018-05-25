package com.choosemyride.exceptions;

public class NoLocationFoundException extends Exception {

    public NoLocationFoundException(String address) {
        super(address);
    }

}
