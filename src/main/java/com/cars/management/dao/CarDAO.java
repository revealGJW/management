package com.cars.management.dao;

import com.cars.management.model.Car;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by cfy on 2017/5/29.
 */
@Mapper
public interface CarDAO {
    String TABLE_NAME = " car ";
    String INSERT_FILEDS = " brand, name, seatnum, gearbox ";
    String SELECT_FILEDS = "id, " + INSERT_FILEDS;
    @Insert( {" insert into ", TABLE_NAME ,"(", INSERT_FILEDS,
            " ) values (#{brand}, #{name}, #{seatnum}, #{gearbox})"})
    int addCar(Car car);

    @Select( {" select " + SELECT_FILEDS + " from " + TABLE_NAME + " where id = #{id}"})
    Car selectCar(@Param("id") int id);


    List<Car> selectCars(@Param("offset") int offset, @Param("limit") int limit);

    @Update({" update " + TABLE_NAME + " set brand = #{brand} , name = #{name} , seatnum = #{seatnum}, gearbox = #{gearbox} where id = #{id} "})
    void update(Car car);

    @Delete({"delete from " + TABLE_NAME + " where id = #{id}"})
    void delete(@Param("id") int id);
}
