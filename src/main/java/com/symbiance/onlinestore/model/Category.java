package com.symbiance.onlinestore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "catagory")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",length = 45)
    private long id;
    @Column(name = "catgoryname",length = 255)
    private String name;


    public Category(long id) {
        this.id = id;
    }
}
