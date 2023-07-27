package com.symbiance.onlinestore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;

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

    private Date date;
    private float total;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "product_id")
    private Prduct prducts;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Order(String ordername, String address, BigInteger phonenumber, float quentity, Date date,float total, Prduct prducts, User user) {
        this.ordername = ordername;
        this.address = address;
        this.phonenumber = phonenumber;
        this.quentity = quentity;
        this.date = date;
        this.total=total;
        this.prducts = prducts;
        this.user=user;
    }

    public Order(Long orderid) {
        this.orderid = orderid;
    }
}
