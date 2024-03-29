package com.luno.ferreteria.entity;




import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sale {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "item")
    private List<Item> itemList = new ArrayList<>();

    private LocalDate date = LocalDate.now();

    private String address;

    private String phone;

    @Nullable
    private double stars;

    @Nullable
    @ManyToOne
    private FeedBack feedBack;
    
    @Enumerated(EnumType.STRING)
    private SaleStatus status;
    @ManyToOne
    private User userFlete;

    @ManyToOne
    private User user;
}
