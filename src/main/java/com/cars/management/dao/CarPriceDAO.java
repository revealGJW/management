package com.cars.management.dao;

import com.cars.management.model.Car;
import com.cars.management.model.CarPrice;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by GJW on 2017/5/29.
 */
@Mapper
public interface CarPriceDAO {
    String TABLE_NAME = " carprice ";
    String INSERT_FILEDS = " carId, price, discount, changeDate ";
    String SELECT_FILEDS = "id, " + INSERT_FILEDS + "status";
    @Insert( {" insert into ", TABLE_NAME ,"(", INSERT_FILEDS,
            " ) values (#{carId}, #{price}, #{discount}, #{changeDate})"})
    int addCarPrice(CarPrice carPrice);

    @Select( {" select " + SELECT_FILEDS + " from " + TABLE_NAME + " where id = #{id}"})
    CarPrice selectCarPrice(@Param("id") int id);

    @Update(" update " + TABLE_NAME + " set status = '历史' where carId = #{carId} and status = '当前' ")
    void ChangeNowPriceToHistory(@Param("carId") int carId);

    @Select( {" select " + SELECT_FILEDS + " from " + TABLE_NAME + " where carId = #{carId} and status = '当前' "})
    CarPrice selectLatestPrice(@Param("carId") int carId);

    List<CarPrice> selectCarPrices(@Param("carId") int carId, @Param("offset") int offset, @Param("limit") int limit);
    //List<CarPrice> selectCarPricesByCarId(@Param("carId") int carId, @Param("offset") int offset, @Param("limit") int limit);
}
