package com.shop.advance.academy.yordan.petrov.git.shop.data.models;

import com.shop.advance.academy.yordan.petrov.git.shop.data.models.enums.OrderStatus;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.enums.PaymentType;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.enums.ShipmentType;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Class model for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    private String number;
    private BigDecimal tax;
    private BigDecimal totalPrice;
    private ShoppingCart shoppingCart;
    private OrderStatus orderStatus = OrderStatus.NONE;
    private ShipmentType shipmentType = ShipmentType.NONE;
    private PaymentType paymentType = PaymentType.NONE;

    /**
     * Constructor
     */
    public Order() {
    }

    /**
     * Method for
     *
     * @return
     */
    @Column(name = "number")
    public String getNumber() {
        return this.number;
    }

    /**
     * Method for
     *
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Method for
     *
     * @return
     */
    @Column(name = "tax", precision = 10, scale = 2)
    @DecimalMax(value = "100.00", message = "max 100.00")
    @DecimalMin(value = "0.00", message = "min 0.00")
    public BigDecimal getTax() {
        return this.tax;
    }

    /**
     * Method for
     *
     * @param vat
     */
    public void setTax(BigDecimal vat) {
        this.tax = vat;
    }

    /**
     * Method for
     *
     * @return
     */
    @Column(name = "price")
    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }

    /**
     * Method for
     *
     * @param price
     */
    public void setTotalPrice(BigDecimal price) {
        this.totalPrice = price;
    }

    /**
     * Method for
     *
     * @return
     */
    @ManyToOne(targetEntity = ShoppingCart.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "sopping_cart_id", referencedColumnName = "id")
    public ShoppingCart getShoppingCart() {
        return this.shoppingCart;
    }

    /**
     * Method for
     *
     * @param shoppingCart
     */
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    /**
     * Method for
     *
     * @return
     */
    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    /**
     * V
     *
     * @param orderStatus
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Method for
     *
     * @return
     */
    @Column(name = "shipment_type")
    @Enumerated(EnumType.STRING)
    public ShipmentType getShipmentType() {
        return this.shipmentType;
    }

    /**
     * Method for
     *
     * @param shipmentType
     */
    public void setShipmentType(ShipmentType shipmentType) {
        this.shipmentType = shipmentType;
    }

    /**
     * Method for
     *
     * @return
     */
    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    public PaymentType getPaymentType() {
        return this.paymentType;
    }

    /**
     * Method for
     *
     * @param paymentType
     */
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }


    /**
     * Method for
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return Objects.equals(number, order.number) &&
                Objects.equals(tax, order.tax) &&
                Objects.equals(totalPrice, order.totalPrice) &&
                orderStatus == order.orderStatus &&
                shipmentType == order.shipmentType &&
                paymentType == order.paymentType;
    }

    /**
     * Method for
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number, tax, totalPrice, orderStatus, shipmentType, paymentType);
    }


    /**
     * Method for
     *
     * @return
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("number='").append(number).append('\'');
        sb.append(", vat=").append(tax);
        sb.append(", price=").append(totalPrice);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", shipmentType=").append(shipmentType);
        sb.append(", paymentType=").append(paymentType);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
