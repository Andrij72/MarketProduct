package com.akul.market.repository;

import com.akul.market.entity.Buyer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {


    List<Buyer> findByLastNameContainsIgnoreCase(String name);

    @Query(value = """
            select concat(b.last_name, ' ', b.first_name) as buyerName, pr.name as productName, sum(pr.cost) as expenses from shop.buyer b 
            inner join shop.purchase pu on b.id=pu.buyer_id 
            inner join shop.product pr on pr.id = pu.product_id 
            where date(pu.date_purchase) between :dateFrom and :dateTo
            group by b.id, pr.name           
            """, nativeQuery = true)
    List<BuyerStatJSON> buyersByDate(@Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo);

    @Query(value = """
            select b from Buyer b 
            inner join b.purchases pu  
            where pu.product.name=:productName
            group by b 
            having count(b)>=:minPurchases
            """, nativeQuery = true)
    List<Buyer> findBuyerByProduct(@Param("productName") String productName, @Param("minPurchases") Long minPurchases);

    @Query(value = """
            select b.id, b.first_name, b.last_name, sum(pr.cost) as summa
            from shop.buyer b
            inner join shop.purchase pu on b.id = pu.buyer_id
            inner join shop.product pr on pr.id = pu.product_id
            group by b.id, b.first_name, b.last_name
            having sum(pr.cost) < :max and sum(pr.cost) > :min """, nativeQuery = true)
    List<Buyer> findMinMax(Long min, Long max);

    @Query("select b from Buyer b " +
            "left join b.purchases pu  " +
            " group by b order by count(pu.id) asc")
    Page<Buyer> findBad(Pageable page);


    public interface BuyerStatJSON {
        @JsonIgnore
        String getBuyerName();

        String getProductName();

        Long getExpenses();
    }
}
