package com.luno.ferreteria.mappers;

import com.luno.ferreteria.dao.IProductDao;
import com.luno.ferreteria.dto.CreateSaleRequestDTO;
import com.luno.ferreteria.dto.FeedbackDTO;
import com.luno.ferreteria.dto.SaleDTO;
import com.luno.ferreteria.dto.SaleRequestDTO;
import com.luno.ferreteria.entity.Item;
import com.luno.ferreteria.entity.Sale;
import com.luno.ferreteria.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class SaleMapper {

    @Autowired
    IProductDao productDao;

    public SaleMapper() {
    }

    /**
     * This method maps a Sale entity to a SaleDTO
     * @param sale Sale entity to be mapped to a SaleDTO
     * @return SaleDTO
     */
    public SaleDTO saleToSaleDTO(Sale sale) {

        // Create a new SaleDTO
        SaleDTO saleDTO = new SaleDTO();
        FeedbackDTO feedbackDTO = new FeedbackDTO();

        feedbackDTO.setId(sale.getFeedBack().getId());
        feedbackDTO.setObservation(sale.getFeedBack().getObservation());
        feedbackDTO.setUsuario(sale.getFeedBack().getUsuario().getId());
        feedbackDTO.setStars(sale.getFeedBack().getStars());


        // Set the attributes of the SaleDTO
        saleDTO.setId(sale.getId());
        saleDTO.setIdUser(sale.getUser().getId());
        saleDTO.setAddress(sale.getAddress());
        saleDTO.setPhone(sale.getPhone());
        saleDTO.setStatus(sale.getStatus());
        saleDTO.setDate(sale.getDate());
        saleDTO.setUserFlete(sale.getUserFlete().getId());
        saleDTO.setFeedback(feedbackDTO);


        // If the sale has items, map them to a new list of items
        if (sale.getItemList() != null) {

            // Create a new list of items in the SaleDTO
            for (Item item : sale.getItemList()) {
                Item i = new Item();
                i.setProduct(item.getProduct());
                i.setAmount(item.getAmount());

                // Add the item to the list of items in the SaleDTO
                saleDTO.getItemList().add(i);
            }
        }

        // Return the SaleDTO
        return saleDTO;
    }
    public Sale saleRequestDtoToSale(CreateSaleRequestDTO saleDto){


        Sale sale = new Sale();
        sale.setId(saleDto.getId());
        sale.setAddress(saleDto.getAddress());
        sale.setPhone(saleDto.getPhone());
        sale.setStatus(saleDto.getStatus());
        sale.setDate(LocalDate.now());
        for (Item item : saleDto.getItemList()) {
                Item i = new Item();
                i.setSale(sale);
                i.setProduct(productDao.findById(item.getProduct().getIdProduct()).get());
                i.setAmount(item.getAmount());
                sale.getItemList().add(i);
        }
        return sale;
    }

    public Sale salesRequestDtoToSale(SaleRequestDTO saleDto){


        Sale sale = new Sale();
        sale.setId(saleDto.getId());
        sale.setAddress(saleDto.getAddress());
        sale.setPhone(saleDto.getPhone());
        sale.setStatus(saleDto.getStatus());
        sale.setDate(LocalDate.now());
        for (Item item : saleDto.getItemList()) {
            Item i = new Item();
            i.setSale(sale);
            i.setProduct(productDao.findById(item.getProduct().getIdProduct()).get());
            i.setAmount(item.getAmount());
            sale.getItemList().add(i);
        }
        return sale;
    }

    /**
     * This method maps a list of Sale entities to a list of SaleDTOs
     * @param listSale List of Sale entities to be mapped to a list of SaleDTOs
     * @return List of SaleDTOs
     */
    public List<SaleDTO> saleListToSaleDTOList(List<Sale> listSale){

        // Create a new list of SaleDTOs
        List<SaleDTO> listSaleDTO = null;

        // Map the list of Sale entities to a list of SaleDTOs
        listSaleDTO = listSale.stream()
                .map(this::saleToSaleDTO)
                .toList();

        // Return the list of SaleDTOs
        return listSaleDTO;
    }
}
