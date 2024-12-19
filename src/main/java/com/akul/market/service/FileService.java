package com.akul.market.service;

import com.akul.market.json.search.SearchResult;
import com.akul.market.json.stat.Stat;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class FileService {
    private static final Logger log = LoggerFactory.getLogger(FileService.class);
    private final JsonService jsonService;
    @Autowired
    private FileService(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    private ObjectMapper mapper = new ObjectMapper();

    public void search(String inputFilePath, String outputFilePath) {
        LinkedHashMap<String, List<Object>> map = null;
        try {
            map = mapper.readValue(new File(inputFilePath), new TypeReference<LinkedHashMap<String, List<Object>>>() {
            });
            SearchResult searchResult = jsonService.searchJson(map);
            mapper.writeValue(new File(outputFilePath), searchResult);
        } catch (IOException e) {
            log.error("Error processing JSON file", e);
        }
    }

    public void stat(String inputFilePath, String outputFilePath) {
        try {
            LinkedHashMap<String, String> map = mapper.readValue(new File(inputFilePath), new TypeReference<LinkedHashMap<String, String>>() {
            });
            Date dateFrom = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("startDate"));
            Date dateTo = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("endDate"));

            Stat stat = jsonService.statJson(dateFrom, dateTo);

            mapper.writeValue(new File(outputFilePath), stat);

        } catch (Exception e) {
            log.error("Error processing JSON file", e);
        }

    }
}
