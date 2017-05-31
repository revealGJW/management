package com.cars.management.dao;

import com.cars.management.model.Car;
import com.cars.management.model.Seller;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by GJW on 2017/5/29.
 */
@Mapper
public interface SellerDAO {
    String TABLE_NAME = " seller ";
    String INSERT_FILEDS = " name, age, tel ";
    String SELECT_FILEDS = "id, " + INSERT_FILEDS;
    @Insert( {" insert into ", TABLE_NAME ,"(", INSERT_FILEDS,
            " ) values (#{name}, #{age}, #{tel})"})
    int addSeller(Seller seller);

    @Select( {" select " + SELECT_FILEDS + " from " + TABLE_NAME + " where id = #{id}"})
    Seller selectSeller(@Param("id") int id);

    List<Seller> selectSellers(@Param("offset") int offset, @Param("limit") int limit);

    @Update({" update " + TABLE_NAME + " set name = #{name} , age = #{age} , tel = #{tel} where id = #{id} "})
    void update(Seller seller);

    @Delete({"delete from " + TABLE_NAME + " where id = #{id}"})
    void delete(@Param("id") int id);
}
