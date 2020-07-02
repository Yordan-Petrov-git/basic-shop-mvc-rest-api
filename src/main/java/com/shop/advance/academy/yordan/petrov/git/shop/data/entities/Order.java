package com.shop.advance.academy.yordan.petrov.git.shop.data.entities;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.OrderStatus;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.PaymentType;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.ShipmentType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    private String number;
    private BigDecimal vat;
    private BigDecimal price;
    private ShoppingCart shoppingCart;
    private OrderStatus orderStatus = OrderStatus.NONE;
    private ShipmentType shipmentType = ShipmentType.NONE;
    private PaymentType paymentType = PaymentType.NONE;

    public Order() {
    }

    @Column(name = "number")
    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "vat")
    public BigDecimal getVat() {
        return this.vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne(targetEntity = ShoppingCart.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "sopping_cart_id", referencedColumnName = "id")
    public ShoppingCart getShoppingCart() {
        return this.shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Column(name = "shipment_type")
    @Enumerated(EnumType.STRING)
    public ShipmentType getShipmentType() {
        return this.shipmentType;
    }

    public void setShipmentType(ShipmentType shipmentType) {
        this.shipmentType = shipmentType;
    }

    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    public PaymentType getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return Objects.equals(number, order.number) &&
                Objects.equals(vat, order.vat) &&
                Objects.equals(price, order.price) &&
                orderStatus == order.orderStatus &&
                shipmentType == order.shipmentType &&
                paymentType == order.paymentType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number, vat, price, orderStatus, shipmentType, paymentType);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("number='").append(number).append('\'');
        sb.append(", vat=").append(vat);
        sb.append(", price=").append(price);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", shipmentType=").append(shipmentType);
        sb.append(", paymentType=").append(paymentType);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
