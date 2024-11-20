package com.akul.market.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.akul.market.entity.Buyer;
import com.akul.market.repository.BuyerRepository;
import com.akul.market.search.FindBuyerByDates;
import com.akul.market.search.FindBuyerByMinMax;
import com.akul.market.search.FindBuyerByProduct;
import com.akul.market.service.BuyerService;

import java.util.List;

// для тестирования работы приложения через веб - какие данные возвращаются из БД

@RestController
@RequestMapping("/buyer")
public class BuyerController {

    private final BuyerService buyerService; // сервис для обращений к БД

    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }


    // Поиск покупателей по фамилии
    @PostMapping("/findByName")
    public ResponseEntity<List<Buyer>> findByName(@RequestBody String text) {
        return ResponseEntity.ok(buyerService.findByName(text));
    }

    // Поиск покупателей, купивших товар не менее, чем указанное число раз
    @PostMapping("/findByProduct")
    public ResponseEntity<List<Buyer>> findBuyerByProduct(@RequestBody FindBuyerByProduct searchFields) {
        return ResponseEntity.ok(buyerService.findBuyerByProduct(searchFields.getProductName(),searchFields.getMinPurchases()));
    }

    // Поиск покупателей, у которых общая стоимость всех покупок за всё время попадает в интервал (>мин, <макс)
    @PostMapping("/findMinMax")
    public ResponseEntity<List<Buyer>> findMaxMin(@RequestBody FindBuyerByMinMax searchFields) {
        return ResponseEntity.ok(buyerService.findMinMax(searchFields.getMinSum(), searchFields.getMaxSum()));
    }


    // Поиск покупателей, купивших меньше всего товаров. Возвращается не более, чем указанное число покупателей.
    @PostMapping("/findBad")
    public ResponseEntity<List<Buyer>> findBad(@RequestBody Integer limit) {
        return ResponseEntity.ok(buyerService.findBad(limit));
    }


    // Cтатистика по покупателям за период (кто чего и сколько купил в сумме)
    @PostMapping("/buyerStat")
    public ResponseEntity<List<BuyerRepository.BuyerStatJSON>> buyersByDate(@RequestBody FindBuyerByDates buyerByDates) {
        return ResponseEntity.ok(buyerService.buyersByDate(buyerByDates.getDateFrom(), buyerByDates.getDateTo()));
    }



}
