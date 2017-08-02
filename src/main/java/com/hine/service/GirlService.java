package com.hine.service;

import com.hine.entity.Girl;
import com.hine.mapper.GirlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 齐海阳
 * Date: 2017/7/25
 * Time: 15:45
 */
@Service
public class GirlService {

    @Autowired
    private GirlMapper girlRepository;

    /**
     * 有事物的添加多条数据
     */
    @Transactional
    public void insertTwoGirl(){
        Girl girlA = new Girl();
        girlA.setAge(77);
        girlA.setCupSize("10D");
        girlRepository.save(girlA);

        Girl girlB = new Girl();
        girlB.setAge(88);
        girlRepository.save(girlB);
    }

}
