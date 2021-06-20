package com.ecomm.ordermanagementservice.utility;

import java.math.BigDecimal;
import java.util.List;

import com.ecomm.ordermanagementservice.entity.Item;

public class Utility {
    public static BigDecimal countTotalPrice(List<Item> cart){
        BigDecimal total = BigDecimal.ZERO;
        for(int i = 0; i < cart.size(); i++){
            total=total.add(new BigDecimal(cart.get(i).getPrice()));
        }
        return total;
    }
}
