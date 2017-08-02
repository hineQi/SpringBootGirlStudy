package com.hine.mapper;

import com.hine.entity.Girl;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by 齐海阳
 * Date: 2017/8/2
 * Time: 13:49
 */
@Mapper
public interface GirlMapper {
    List<Girl> findAll();
    Girl findOne(Integer id);
    Girl save(Girl girl);
    void update(Girl girl);
    void delete(Integer id);
    List<Girl> findByAge(Integer age);
}
