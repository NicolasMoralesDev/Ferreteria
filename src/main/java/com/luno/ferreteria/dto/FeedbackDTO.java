package com.luno.ferreteria.dto;


import lombok.Data;

@Data
public class FeedbackDTO {

    private int id;
    private int stars;
    private String observation;
    private int usuario;
}
