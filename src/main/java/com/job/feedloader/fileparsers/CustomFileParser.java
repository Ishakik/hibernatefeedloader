package com.job.feedloader.fileparsers;

import com.job.feedloader.exceptions.FeedParserException;

import java.io.File;
import java.util.List;

public interface CustomFileParser<T> {
    public List<T> parse(String path) throws FeedParserException;

    default boolean fileExists(String path) {
        return new File(path).exists();
    }
}
