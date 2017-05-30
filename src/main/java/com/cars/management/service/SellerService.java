package com.cars.management.service;

import com.cars.management.dao.CarDAO;
import com.cars.management.dao.SellerDAO;
import com.cars.management.model.Car;
import com.cars.management.model.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by GJW on 2017/5/29.
 */
@Service
public class SellerService {
    @Autowired
    SellerDAO sellerDAO;

    public List<Seller> getSellers(int offset, int limit) {
        return sellerDAO.selectSellers(offset, limit);
    }

    public List<Seller> selectSellersByPage(int page) {
        return sellerDAO.selectSellers((page - 1) * 10, 10);
    }
}
