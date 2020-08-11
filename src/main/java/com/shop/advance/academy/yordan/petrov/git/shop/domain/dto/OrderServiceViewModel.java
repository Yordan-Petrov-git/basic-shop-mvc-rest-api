package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;

import com.shop.advance.academy.yordan.petrov.git.shop.data.models.enums.OrderStatus;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.enums.PaymentType;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.enums.ShipmentType;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Class dto for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public class OrderServiceViewModel {

    private Long id;
    private String number;
    private BigDecimal tax;
    private BigDecimal totalPrice;
    private ShoppingCartServiceViewModel shoppingCart;
    private OrderStatus orderStatus = OrderStatus.NONE;
    private ShipmentType shipmentType = ShipmentType.NONE;
    private PaymentType paymentType = PaymentType.NONE;

    /**
     * Constructor
     */
    public OrderServiceViewModel() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets number.
     *
     * @return the number
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * Sets number.
     *
     * @param number the number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Gets tax.
     *
     * @return the tax
     */
    public BigDecimal getTax() {
        return this.tax;
    }

    /**
     * Sets tax.
     *
     * @param tax the tax
     */
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return this.totalPrice;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(BigDecimal price) {
        this.totalPrice = price;
    }

    /**
     * Gets shopping cart.
     *
     * @return the shopping cart
     */
    public ShoppingCartServiceViewModel getShoppingCart() {
        return this.shoppingCart;
    }

    /**
     * Sets shopping cart.
     *
     * @param shoppingCart the shopping cart
     */
    public void setShoppingCart(ShoppingCartServiceViewModel shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    /**
     * Gets order status.
     *
     * @return the order status
     */
    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    /**
     * Sets order status.
     *
     * @param orderStatus the order status
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Gets shipment type.
     *
     * @return the shipment type
     */
    public ShipmentType getShipmentType() {
        return this.shipmentType;
    }

    /**
     * Sets shipment type.
     *
     * @param shipmentType the shipment type
     */
    public void setShipmentType(ShipmentType shipmentType) {
        this.shipmentType = shipmentType;
    }

    /**
     * Gets payment type.
     *
     * @return the payment type
     */
    public PaymentType getPaymentType() {
        return this.paymentType;
    }

    /**
     * Sets payment type.
     *
     * @param paymentType the payment type
     */
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * Gets total price.
     *
     * @return the total price
     */
    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }

    /**
     * Sets total price.
     *
     * @param totalPrice the total price
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderServiceViewModel)) return false;
        OrderServiceViewModel that = (OrderServiceViewModel) o;
        return Objects.equals(number, that.number) &&
                Objects.equals(tax, that.tax) &&
                Objects.equals(totalPrice, that.totalPrice) &&
                orderStatus == that.orderStatus &&
                shipmentType == that.shipmentType &&
                paymentType == that.paymentType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, tax, totalPrice, orderStatus, shipmentType, paymentType);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderServiceViewModel{");
        sb.append("number='").append(number).append('\'');
        sb.append(", vat=").append(tax);
        sb.append(", price=").append(totalPrice);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", shipmentType=").append(shipmentType);
        sb.append(", paymentType=").append(paymentType);
        sb.append('}');
        return sb.toString();
    }
}

