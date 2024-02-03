package com.luno.ferreteria.dto;

import com.luno.ferreteria.entity.Item;
import com.luno.ferreteria.entity.SaleStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO for the request of the creation of a sale.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSaleRequestDTO {

    private int id;
    private int idUser;

    private List<Item> itemList = new ArrayList<>();
    private String address;
    @Enumerated(EnumType.STRING)
    private SaleStatus status;
    private String phone;
}
