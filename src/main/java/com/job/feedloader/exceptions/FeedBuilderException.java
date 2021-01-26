package com.job.feedloader.exceptions;

public class FeedBuilderException extends Exception {

    private String message;

    public FeedBuilderException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
