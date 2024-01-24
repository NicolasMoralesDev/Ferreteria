package com.luno.ferreteria.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSubCategory;
    private String title;
//   @ManyToMany(mappedBy = "subCategory")
//    private List<Product> category;
   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(name = "subCategory_Category",
        joinColumns = @JoinColumn(name = "idSubCategory"),
        inverseJoinColumns = @JoinColumn(name = "idCategory")
   )
   private List<Category> category;

}
