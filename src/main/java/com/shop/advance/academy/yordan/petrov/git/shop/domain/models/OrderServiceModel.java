package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.OrderStatus;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.PaymentType;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.ShipmentType;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderServiceModel {

    private Long id;
    private String number;
    private BigDecimal tax;
    private BigDecimal totalPrice;
    private ShoppingCartServiceModel shoppingCart;
    private OrderStatus orderStatus = OrderStatus.NONE;
    private ShipmentType shipmentType = ShipmentType.NONE;
    private PaymentType paymentType = PaymentType.NONE;

    public OrderServiceModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getTax() {
        return this.tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getPrice() {
        return this.totalPrice;
    }

    public void setPrice(BigDecimal price) {
        this.totalPrice = price;
    }

    public ShoppingCartServiceModel getShoppingCart() {
        return this.shoppingCart;
    }

    public void setShoppingCart(ShoppingCartServiceModel shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public ShipmentType getShipmentType() {
        return this.shipmentType;
    }

    public void setShipmentType(ShipmentType shipmentType) {
        this.shipmentType = shipmentType;
    }

    public PaymentType getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderServiceModel)) return false;
        OrderServiceModel that = (OrderServiceModel) o;
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
        final StringBuilder sb = new StringBuilder("OrderServiceModel{");
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
