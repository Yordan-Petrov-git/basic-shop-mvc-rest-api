package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Item;

import java.math.BigDecimal;
import java.util.Collection;

public interface PurchasingService {

    void calculateVat(BigDecimal vat);

    void calculateTotalPrice(Collection<Item> items);

}
