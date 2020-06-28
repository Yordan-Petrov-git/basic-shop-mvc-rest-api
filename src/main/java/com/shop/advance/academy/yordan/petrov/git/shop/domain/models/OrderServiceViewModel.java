package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ShoppingCart;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.OrderStatus;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.PaymentType;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.ShipmentType;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderServiceViewModel {
    private Long id;
    private String number;
    private BigDecimal vat;
    private BigDecimal price;
    private ShoppingCartServiceViewModel shoppingCart;
    private OrderStatus orderStatus = OrderStatus.NONE;
    private ShipmentType shipmentType = ShipmentType.NONE;
    private PaymentType paymentType = PaymentType.NONE;


    public OrderServiceViewModel() {
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

    public BigDecimal getVat() {
        return this.vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ShoppingCartServiceViewModel getShoppingCart() {
        return this.shoppingCart;
    }

    public void setShoppingCart(ShoppingCartServiceViewModel shoppingCart) {
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
        if (!(o instanceof OrderServiceViewModel)) return false;
        OrderServiceViewModel that = (OrderServiceViewModel) o;
        return Objects.equals(number, that.number) &&
                Objects.equals(vat, that.vat) &&
                Objects.equals(price, that.price) &&
                orderStatus == that.orderStatus &&
                shipmentType == that.shipmentType &&
                paymentType == that.paymentType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, vat, price, orderStatus, shipmentType, paymentType);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderServiceViewModel{");
        sb.append("number='").append(number).append('\'');
        sb.append(", vat=").append(vat);
        sb.append(", price=").append(price);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", shipmentType=").append(shipmentType);
        sb.append(", paymentType=").append(paymentType);
        sb.append('}');
        return sb.toString();
    }
}

