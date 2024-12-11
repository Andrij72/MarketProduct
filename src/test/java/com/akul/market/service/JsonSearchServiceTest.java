package com.akul.market.service;

import com.akul.market.entity.Buyer;
import com.akul.market.json.search.BuyerResult;
import com.akul.market.json.search.SearchResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class JsonSearchServiceTest {

    @Mock
    private BuyerService buyerService;

    @InjectMocks
    private JsonService jsonService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchJson() {
        // Создаем критерий поиска по фамилии
        LinkedHashMap<String, List<Object>> searchCriteria = new LinkedHashMap<>();
        List<Object> criterias = new ArrayList<>();

        LinkedHashMap<String, Object> criteriaMap = new LinkedHashMap<>();
        criteriaMap.put("lastName", "Smith");
        criterias.add(criteriaMap);

        searchCriteria.put("criterias", criterias);

        Buyer buyer = new Buyer();
        buyer.setFirstName("John");
        buyer.setLastName("Smith");

        List<Buyer> buyers = Collections.singletonList(buyer);
        when(buyerService.findByName("Smith")).thenReturn(buyers);

        SearchResult result = jsonService.searchJson(searchCriteria);

        assertEquals(1, result.getResults().size());
        BuyerResult buyerResult = result.getResults().get(0);
        assertEquals(criteriaMap, buyerResult.getCriteria());
        assertEquals(buyers, buyerResult.getResults());

        // Проверяем, что метод вызван
        verify(buyerService, times(1)).findByName("Smith");
    }
}
