package com.choosemyride.exceptions;

public class MultipleLocationsFoundException extends Exception {

    public MultipleLocationsFoundException(String address) {
        super(address);
    }

}
