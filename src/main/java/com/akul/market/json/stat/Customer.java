package com.akul.market.json.stat;

import com.akul.market.repository.BuyerRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private String name;
    private List<BuyerRepository.BuyerStatJSON> purchases;
    private Long totalExpenses;
}
