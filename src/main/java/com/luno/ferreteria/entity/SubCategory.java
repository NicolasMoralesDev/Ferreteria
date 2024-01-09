package com.luno.ferreteria.entity;




import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSubCategory;
    private String title;
    @ManyToOne
    @JoinColumn(name = "idSubCategory")
    private Category cate;

}
