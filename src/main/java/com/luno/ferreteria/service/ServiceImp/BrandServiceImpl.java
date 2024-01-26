package com.luno.ferreteria.service.ServiceImp;

import com.luno.ferreteria.dao.IBrandDao;
import com.luno.ferreteria.entity.Brand;
import com.luno.ferreteria.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements IBrandService {

    @Autowired
    private IBrandDao brandDao;

    @Override
    public String postBrand(Brand brand) {

        try {

            brandDao.save(brand);

            return "Marca creada con Exitoo!";
        } catch (Exception e){

            return e.getMessage();
        }
    }

    @Override
    public List<Brand> getAllBrand() {

        return brandDao.findAll();
    }

    @Override
    public Brand getBrandById(int id) {

        return  brandDao.findById(id).orElse(null);
    }

    @Override
    public String deleteById(int id) {

        try {

            brandDao.deleteById(id);
            return "Marca eliminada con Exito!";

        } catch (Exception e){
            return e.getMessage();
        }
    }

    @Override
    public String putBrand(Brand brand) {

        try {

            brandDao.save(brand);
            return "Marca modificada con Exito!";

        } catch (Exception e){
            return e.getMessage();
        }
    }
}
