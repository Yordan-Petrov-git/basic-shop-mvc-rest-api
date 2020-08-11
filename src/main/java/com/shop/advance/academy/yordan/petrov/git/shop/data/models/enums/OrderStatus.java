package com.shop.advance.academy.yordan.petrov.git.shop.data.models.enums;

/**
 * Class enum for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public enum OrderStatus {
    NONE,
    PROCESSING,
    IN_TRANSIT,
    IN_DESTINATION_COUNTRY,
    IN_SELLER_COUNTRY,
    SHIPPED,
    CANCELED,
    PICKED_UP_BY
}
