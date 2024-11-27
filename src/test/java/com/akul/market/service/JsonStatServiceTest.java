package com.akul.market.service;


import com.akul.market.json.stat.Stat;
import com.akul.market.repository.BuyerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JsonStatServiceTest {

    @Mock
    private BuyerService buyerService;

    @InjectMocks
    private JsonService jsonService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testStatJson_WithAnonimClass() {
        // Устанавливаем даты для теста
        Date dateFrom = new Date(System.currentTimeMillis() - 86400000L * 30); // 30 дней назад
        Date dateTo = new Date();

        // Создаем список с данными статистики
        List<BuyerRepository.BuyerStatJSON> buyers = Arrays.asList(
                new BuyerRepository.BuyerStatJSON() {
                    @Override
                    public String getBuyerName() {
                        return "John Doe";
                    }

                    @Override
                    public String getProductName() {
                        return "Product A";
                    }

                    @Override
                    public Long getExpenses() {
                        return 100L;
                    }
                }
        );

        // Настраиваем поведение мок-объекта
        when(buyerService.buyersByDate(dateFrom, dateTo)).thenReturn(buyers);

        // Вызываем метод
        Stat result = jsonService.statJson(dateFrom, dateTo);

        // Проверка результатов
        assertEquals(1, result.getCustomers().size());
        assertEquals("John Doe", result.getCustomers().get(0).getName());
        assertEquals(100L, result.getCustomers().get(0).getTotalExpenses());
    }

    @Test
    void testStatJson_WithMockBuyerStatJSON() {
        // Устанавливаем даты для теста
        Date dateFrom = new Date(System.currentTimeMillis() - 86400000L * 30); // 30 дней назад
        Date dateTo = new Date();

        // Создаем мокированный BuyerStatJSON
        BuyerRepository.BuyerStatJSON buyerStatJSON = mock(BuyerRepository.BuyerStatJSON.class);
        when(buyerStatJSON.getBuyerName()).thenReturn("John Doe");
        when(buyerStatJSON.getProductName()).thenReturn("Product A");
        when(buyerStatJSON.getExpenses()).thenReturn(100L);

        // Настраиваем поведение buyerService
        when(buyerService.buyersByDate(dateFrom, dateTo)).thenReturn(Collections.singletonList(buyerStatJSON));

        // Выполняем тестируемый метод
        Stat result = jsonService.statJson(dateFrom, dateTo);

        // Проверяем результат
        assertEquals(1, result.getCustomers().size());
        assertEquals("John Doe", result.getCustomers().get(0).getName());
        assertEquals(100L, result.getCustomers().get(0).getTotalExpenses());
    }
}
