package com.akul.market.service;

import com.akul.market.entity.Buyer;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.akul.market.repository.BuyerRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BuyerService {

    private final BuyerRepository buyerRepository;

    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }


    /**
     * Search buyers by last name
     * @param name the last name of the buyers to search for
     * @return a list of buyers matching the given last name
     */
    public List<Buyer> findByName(String name) {
        return buyerRepository.findByLastNameContainsIgnoreCase(name);
    }


    /**
     * Search customers who have purchased the product at least the specified number of times.
     * @param productName
     * @param minPurchases
     * @return
     */
    public List<Buyer> findBuyerByProduct(String productName, long minPurchases) {
        return buyerRepository.findBuyerByProduct(productName, minPurchases);
    }

    /**
     * Find buyers whose total cost of all purchases over time falls within the interval
     * @param min
     * @param max
     * @return
     */
    public List<Buyer> findMinMax(long min, long max) {
        return buyerRepository.findMinMax(min, max);
    }

    /**
     * Finds the buyers who bought the least amount of items. Returns up to the specified
     * number of buyers.
     * @param count
     * @return
     */
    public List<Buyer> findBad(int count) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        PageRequest pageRequest = PageRequest.of(0, count, sort);
        return buyerRepository.findBad(pageRequest).getContent();
    }

    /**
     * Statistics on buyers for the period (who bought what and how much in total)
     * @param dateFrom
     * @param dateTo
     * @return
     */
    public List<BuyerRepository.BuyerStatJSON> buyersByDate(Date dateFrom, Date dateTo) {
        return buyerRepository.buyersByDate(dateFrom, dateTo);
    }

}
