package com.luno.ferreteria.dto;

import com.luno.ferreteria.entity.Item;
import com.luno.ferreteria.entity.SaleStatus;
import com.luno.ferreteria.entity.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for the request of the creation of a sale.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {

    private Integer id;
    private List<Item> itemList = new ArrayList<>();
    private int idUser;
    private String address;
    private String phone;
    @Enumerated(EnumType.STRING)
    private SaleStatus status;
    private LocalDate date;
}