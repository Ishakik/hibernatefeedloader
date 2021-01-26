package com.job.feedloader.exceptions;

public class FeedParserException extends Exception {

    private String message;

    public FeedParserException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
