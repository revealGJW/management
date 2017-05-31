package com.cars.management.dao;

import com.cars.management.model.Car;
import com.cars.management.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by GJW on 2017/5/29.
 */
@Mapper
public interface OrderDAO {
    String TABLE_NAME = " orders ";
    String INSERT_FILEDS = " carId, sellerId, createTime, status, finishTime, num, total ";
    String SELECT_FILEDS = " id, " + INSERT_FILEDS;
    @Insert( {" insert into ", TABLE_NAME ,"(", INSERT_FILEDS,
            " ) values (#{carId}, #{sellerId}, #{createTime}, #{status}, #{finishTime}, #{num}, #{total})"})
    int addOrder(Order order);

    @Select( {" select " + SELECT_FILEDS + " from " + TABLE_NAME + " where id = #{id}"})
    Order selectOrder(@Param("id") int id);

    List<Order> selectOrders(@Param("sellerId") int sellerId, @Param("offset") int offset, @Param("limit") int limit);

    @Select( {" select " + SELECT_FILEDS + " from " + TABLE_NAME + " where sellerId = #{sellerId} and status = #{status} order by id limit #{offset}, #{limit}"})
    List<Order> selectOrdersByStatus(@Param("status") String status, @Param("sellerId") int sellerId, @Param("offset") int offset, @Param("limit") int limit);
}
