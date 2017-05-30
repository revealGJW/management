package com.cars.management.dao;

import com.cars.management.model.Car;
import com.cars.management.model.CarPrice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by GJW on 2017/5/29.
 */
@Mapper
public interface CarPriceDAO {
    String TABLE_NAME = " carprice ";
    String INSERT_FILEDS = " carId, price, discount, changeDate, status ";
    String SELECT_FILEDS = "id, " + INSERT_FILEDS;
    @Insert( {" insert into ", TABLE_NAME ,"(", INSERT_FILEDS,
            " ) values (#{carId}, #{price}, #{discount}, #{changeDate}, #{status})"})
    int addCarPrice(CarPrice carPrice);

    @Select( {" select " + SELECT_FILEDS + " from " + TABLE_NAME + " where id = #{id}"})
    CarPrice selectCarPrice(@Param("id") int id);

    @Select( {" select " + SELECT_FILEDS + " from " + TABLE_NAME + " where id = #{id} and status = 'µ±Ç°' "})
    CarPrice selectLatestPrice(@Param("id") int id);

    List<CarPrice> selectCarPrices(@Param("carId") int carId, @Param("offset") int offset, @Param("limit") int limit);
    //List<CarPrice> selectCarPricesByCarId(@Param("carId") int carId, @Param("offset") int offset, @Param("limit") int limit);
}
