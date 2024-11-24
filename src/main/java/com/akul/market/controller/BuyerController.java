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


@RestController
@RequestMapping("/buyer")
public class BuyerController {

    private final BuyerService buyerService; // сервис для обращений к БД

    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }


    /**
     * Search for buyers by last name
     */

    @PostMapping("/findByName")
    public ResponseEntity<List<Buyer>> findByName(@RequestBody String text) {
        return ResponseEntity.ok(buyerService.findByName(text));
    }

    /**
     * Search for customers who have purchased the product
     * at least the specified number of times
     */
    @PostMapping("/findByProduct")
    public ResponseEntity<List<Buyer>> findBuyerByProduct(@RequestBody FindBuyerByProduct searchFields) {
        return ResponseEntity.ok(buyerService.findBuyerByProduct(searchFields.getProductName(), searchFields.getMinPurchases()));
    }

    /***
     * Find customers whose total purchase value for all time falls within
     * the interval (min, max)
     * @param searchFields
     * @return
     */

    @PostMapping("/findMinMax")
    public ResponseEntity<List<Buyer>> findMaxMin(@RequestBody FindBuyerByMinMax searchFields) {
        return ResponseEntity.ok(buyerService.findMinMax(searchFields.getMinSum(), searchFields.getMaxSum()));
    }

    /***
     * Find the customers who bought the least amount of items. Returns no more
     * than the specified number of customers.
     * @param limit
     * @return
     */
    @PostMapping("/findBad")
    public ResponseEntity<List<Buyer>> findBad(@RequestBody Integer limit) {
        return ResponseEntity.ok(buyerService.findBad(limit));
    }

    /***
     * Statistics on buyers for the period (who bought what and how much in total)
     * @param buyerByDates
     * @return
     */
    @PostMapping("/buyerStat")
    public ResponseEntity<List<BuyerRepository.BuyerStatJSON>> buyersByDate(@RequestBody FindBuyerByDates buyerByDates) {
        return ResponseEntity.ok(buyerService.buyersByDate(buyerByDates.getDateFrom(), buyerByDates.getDateTo()));
    }


}
