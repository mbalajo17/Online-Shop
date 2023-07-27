package com.symbiance.onlinestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor

@NoArgsConstructor
@Table(name = "productser")
public class Prduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

   @Column(name = "id",length = 255)
    private Long id;
    @Column(name = "name",length = 255)
    private String name;
    @Column(name = "description",length = 255)
    private String description;
    @Column(name = "price",length = 255)
    private float price;

    @Lob
    @Column(nullable = true, length = Integer.MAX_VALUE)
    @JsonIgnoreProperties ({"hibernateLazyInitializer", "handler"})
    private byte[] imageURL;
@ManyToOne
@JoinColumn(name = "catagory_id")
private Category category;


    public Prduct(Long id) {
        this.id = id;
    }

}
