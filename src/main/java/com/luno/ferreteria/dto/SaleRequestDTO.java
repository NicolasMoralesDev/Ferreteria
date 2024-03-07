package com.luno.ferreteria.dto;

import com.luno.ferreteria.entity.FeedBack;
import com.luno.ferreteria.entity.Item;
import com.luno.ferreteria.entity.SaleStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SaleRequestDTO {


    private int id;
    private int idUser;
    private int userFlete;
    private List<Item> itemList = new ArrayList<>();
    private String address;
    @Enumerated(EnumType.STRING)
    private SaleStatus status;
    private String phone;
    private FeedBack feedback;
}
