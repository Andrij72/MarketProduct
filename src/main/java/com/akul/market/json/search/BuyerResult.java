package com.akul.market.json.search;

import com.akul.market.entity.Buyer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyerResult {
    private Object criteria;
    private List<Buyer> results = new ArrayList<>();
}
