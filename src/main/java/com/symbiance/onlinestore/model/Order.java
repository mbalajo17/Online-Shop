package com.symbiance.onlinestore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Locale;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "place_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderid;
    private String ordername;
    private String address;
    private BigInteger phonenumber;
    private float quentity;

    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private float total;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "product_id")
    private Prduct prducts;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Order(Long orderid) {
        this.orderid = orderid;
    }
}
