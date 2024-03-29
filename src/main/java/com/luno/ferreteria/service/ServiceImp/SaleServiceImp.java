package com.luno.ferreteria.service.ServiceImp;


import com.luno.ferreteria.dao.ISaleDao;
import com.luno.ferreteria.dto.*;
import com.luno.ferreteria.entity.FeedBack;
import com.luno.ferreteria.entity.Sale;
import com.luno.ferreteria.entity.User;
import com.luno.ferreteria.entity.UserPro;
import com.luno.ferreteria.mappers.SaleMapper;
import com.luno.ferreteria.service.IFeedBackService;
import com.luno.ferreteria.service.IUserPro;
import com.luno.ferreteria.service.SaleService;
import com.luno.ferreteria.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SaleServiceImp implements SaleService {

    @Autowired
    ISaleDao saleDao;

    @Autowired
    IFeedBackService feedServ;

    @Autowired
    IUserPro userProService;

    @Autowired
    UserService userService;

    @Autowired
    SaleMapper saleMapper;

    /**
     * Method for save a sale in the database.
     * @param requestDTO the request with the userId, address, phone, and the list of products with the amount of each one.
     * @return SaleDTO, the sale saved.
     */
    @Override
    public SaleDTO saveSale(CreateSaleRequestDTO requestDTO){


        // Find the user in the database.
        User user = userService.findById(requestDTO.getIdUser());
        User userFlete = userService.findById(requestDTO.getUserFlete());

        // Create the sale mapping the request to a sale entity.
        Sale newSale = saleMapper.saleRequestDtoToSale(requestDTO);

        // Set the user to the sale.
        newSale.setUser(user);
        newSale.setUserFlete(userFlete);

        SaleDTO sale = new SaleDTO();

        // Save the sale in the database and return the sale mapped to a SaleDTO.
        return saleMapper.saleToSaleDTO(saleDao.save(newSale));

    }

    /**
     * Method for get all the sales of a user.
     * @param requestDTO the request with the userId.
     * @param page int, the page number.
     * @return SalePaginationDTO, the sales of the user.
     */
    @Override
    public SalePaginationDTO saleByUserId(UserSalesRequestDTO requestDTO, int page){

        // Getting the user id.
        int idUser = requestDTO.getIdUser();

        // Initializing the pagination.
        Pageable pageable = PageRequest.of(page, 10);

        // Creating the response.
        SalePaginationDTO salePaginationDTO = new SalePaginationDTO();


        // Setting the page number.
        salePaginationDTO.setPage(page);

        // Getting the sales of the user paginated.
        Page<Sale> saleList = saleDao.saleByUserIdPageable(idUser, pageable);

        // Setting the sales of the user.
        salePaginationDTO.setSales(saleMapper.saleListToSaleDTOList(saleList.getContent()));

        // Setting the total pages.
        salePaginationDTO.setTotal(saleList.getTotalPages());


        // Returning the response.
        return salePaginationDTO;
    }

    /**
     * Method for get all the sales paginated. Admin role required.
     * @param page int, the page number.
     * @return SalePaginationDTO, the sales.
     */
    @Override
    public SalePaginationDTO getAllSales(int page){

        // Initializing the pagination.
        Pageable pageable = PageRequest.of(page, 10);

        // Creating the response.
        SalePaginationDTO salePaginationDTO = new SalePaginationDTO();

        // Setting the page number.
        salePaginationDTO.setPage(page);

//        salePaginationDTO.setIdUser(saleDao.findAllPage(pageable).get(1).g);
        // Getting the sales paginated.
        Page<Sale> saleList = saleDao.findAllPage(pageable);

        // Setting the sales.
        salePaginationDTO.setSales(saleMapper.saleListToSaleDTOList(saleList.getContent()));

        // Setting the total pages.
        salePaginationDTO.setTotal(saleList.getTotalPages());

        // Returning the response.
        return salePaginationDTO;
    }

    @Override
    public String putStatusSale(CreateSaleRequestDTO saleRequestDTO) {

        try {

            Sale nueva = saleMapper.saleRequestDtoToSale(saleRequestDTO);
            User user = userService.findById(saleRequestDTO.getIdUser());

            FeedBack feedBack = new FeedBack();
            feedBack.setId(saleRequestDTO.getFeedback().getId());
            feedBack.setObservation(saleRequestDTO.getFeedback().getObservation());
            feedBack.setStars(saleRequestDTO.getFeedback().getStars());
            feedBack.setUsuario(user);


            UserPro userPro = userProService.findByEmail(user.getEmail());
            nueva.setUser(user);
            nueva.setUserFlete(userPro.getUser());
            nueva.setFeedBack(feedBack);

            saleDao.save(nueva);

            return "Estado Actualizado correctamente!";
        } catch (Exception e) {

            return "Error " + e.getMessage();
        }
    }

    @Override
    public String putSale(SaleRequestDTO sale) {

        try {

            Sale nueva = saleMapper.salesRequestDtoToSale(sale);
            User userFlete = userService.findById(sale.getUserFlete());
            User nuevo = userService.findById(sale.getIdUser());

            FeedBack feedBack = feedServ.createFeedBack(sale.getFeedback());

            nueva.setUser(nuevo);
            nueva.setStars(feedBack.getStars());
            nueva.setFeedBack(feedBack);
            nueva.setUserFlete(userFlete);
            nueva.setFeedBack(feedBack);
            saleDao.save(nueva);

            UserPro userPro = userProService.findByEmail(nuevo.getEmail() );

            List<FeedBack> feedBackList = userPro.getFeedback();
            userPro.setStars(userPro.getStars() + sale.getFeedback().getStars());
            userPro.setCantFeedBack(userPro.getCantFeedBack() + 1);
            feedBackList.add(sale.getFeedback());
            userPro.setFeedback(feedBackList);
            userProService.createUserPro(userPro);

        return "Reseña dada correctamente!";
        } catch (Exception e){

            return "Error "+ e.getMessage();
        }
    }

}
