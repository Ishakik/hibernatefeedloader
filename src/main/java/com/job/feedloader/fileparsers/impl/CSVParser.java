package com.job.feedloader.fileparsers.impl;

import com.job.feedloader.configuration.CSVConfiguration;
import com.job.feedloader.exceptions.FeedParserException;
import com.job.feedloader.fileparsers.CustomFileParser;
import com.job.feedloader.models.MasterData;
import com.job.feedloader.utils.FeedInputStringUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CSVParser implements CustomFileParser {

    @Override
    public List<MasterData> parse(String path) throws FeedParserException {

        if (!fileExists(path)) {
            throw new FeedParserException("provided path : " + path + " to the file does not exists");
        }

        List<MasterData> masterData = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(path));
            String header = scanner.nextLine();
            int rowNumber = 1;
            if(isValidHeader(header)) {
                throw new FeedParserException("invalid header from feed file : " + header);
            }
            while(scanner.hasNext()) {
                String row = scanner.nextLine();
                MasterData masterDataRow = parseRow(row, ++rowNumber);
                masterData.add(masterDataRow);
            }
        } catch (FileNotFoundException e) {
            throw new FeedParserException("an error occurred while processing the file : " + e.getMessage());
        }

        return masterData;
    }

    private static boolean isValidHeader(String header){
        return Arrays.asList(header.split("|")).containsAll(CSVConfiguration.FEED_HEADERS);
    }

    private static MasterData parseRow(String row, int rowNumber) throws FeedParserException {
        List<String> columns = Arrays.asList(row.split(","));

        if(columns.size() != CSVConfiguration.FEED_FILE_COLUMN_LENGTH){
            throw new FeedParserException(String.format("Feed row number %s does not have enough " +
                    "data to parse it into object", rowNumber));
        }

        List<String> cols = columns.stream().map(col -> FeedInputStringUtil.nullifyIfEmpty(col)).collect(Collectors.toList());

        return new MasterData(
                cols.get(0),
                cols.get(1),
                cols.get(2),
                cols.get(3),
                cols.get(4),
                cols.get(5),
                cols.get(6),
                cols.get(7),
                cols.get(8),
                cols.get(9),
                cols.get(10),
                cols.get(11),
                cols.get(12),
                cols.get(13),
                cols.get(14),
                cols.get(15),
                cols.get(16)
                );
    }
}
