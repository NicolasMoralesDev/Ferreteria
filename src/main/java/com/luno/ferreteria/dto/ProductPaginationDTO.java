
package com.luno.ferreteria.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author Nico Morales
 */
@Getter
@Setter
@Component
public class ProductPaginationDTO {
    
    private List <ProductDTO> productos;
    private int page;
    private int total;

    public ProductPaginationDTO() {
    }

    public ProductPaginationDTO(List<ProductDTO> productosDto, int page, int total) {
        this.productos = productosDto;
        this.page = page;
        this.total = total;
    }

}

