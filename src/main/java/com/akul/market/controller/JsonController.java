package com.akul.market.controller;

import com.akul.market.json.search.SearchResult;
import com.akul.market.json.stat.Stat;
import com.akul.market.search.FindBuyerByDates;
import com.akul.market.service.JsonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/json")
public class JsonController {

    private final JsonService jsonService;

    public JsonController(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @PostMapping("/searchWithJson")
    public ResponseEntity<SearchResult> search(@RequestBody Object object) {
        LinkedHashMap<String, List<Object>> map = (LinkedHashMap<String, List<Object>>) object;
        return ResponseEntity.ok(jsonService.searchJson(map));
    }


    @PostMapping("/buyerStatJson")
    public ResponseEntity<Stat> stat(@RequestBody FindBuyerByDates dates) {
        return ResponseEntity.ok(
                jsonService.statJson(dates.getDateFrom(),
                                          dates.getDateTo()));
    }
}
