package com.hine.mapper;

import com.hine.entity.Girl;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by 齐海阳
 * Date: 2017/8/2
 * Time: 9:45
 */

public interface GirlMapper {

    @Select("select * from girl")
    @Results({
            @Result(property = "age", column = "age"),
            @Result(property = "cupSize", column = "cup_size")
    })
    List<Girl> findAll();

    @Select("select * from girl where id = #{id}")
    @Results({
            @Result(property = "age", column = "age"),
            @Result(property = "cupSize", column = "cup_size")
    })
    Girl findOne(Long id);

    @Insert("insert into girl(age,cup_size) values(#{age},#{cupSize})")
    @Results({
            @Result(property = "age", column = "age"),
            @Result(property = "cupSize", column = "cup_size")
    })
    Girl save(Girl girl);

    @Update("update girl set age=#{age},cup_size=#{cupSize} where id=#{id}")
    void update(Girl girl);

    @Delete("delete from girl where id=#{id}")
    void delete(Long id);

    @Select("select * from girl where age=#{age}")
    @Results({
            @Result(property = "age", column = "age"),
            @Result(property = "cupSize", column = "cup_size")
    })
    List<Girl> findByAge(Integer age);
}
