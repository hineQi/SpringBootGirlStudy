package com.hine.controller;

import com.hine.repository.GirlRepository;
import com.hine.entity.Girl;
import com.hine.service.GirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by 齐海阳
 * Date: 2017/7/24
 * Time: 16:17
 */
@RestController
@RequestMapping("/girl")
public class GirlsController {

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    /**
     * 查询所有女生列表
     * @return
     */
    @GetMapping(value = "/girls")
    public List<Girl> girlList(){
        System.out.println("girlList girlList girlList girlList girlList");
        return girlRepository.findAll();
    }

    /**
     * 添加女生
     * @param girl
     * @return
     */
    @PostMapping(value = "/girls")
    public Girl girlAdd(@Valid Girl girl, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
        }
        return girlRepository.save(girl);
    }

    /**
     * 获取一个女生信息
     * @param id
     * @return
     */
    @GetMapping(value = "/girls/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id){
        return girlRepository.findOne(id);
    }

    /**
     * 修改某个女生信息
     * @param girl
     * @return
     */
    @PutMapping(value = "/girls/{id}")
    public Girl girlUpdate(Girl girl){
        return girlRepository.save(girl);
    }

    /**
     * 删除某个女生
     * @param id
     */
    @DeleteMapping(value = "/girls/{id}")
    public void girlDelete(@PathVariable("id") Integer id){
        girlRepository.delete(id);
    }

    /**
     * 通过年龄查询女生列表
     * @param age
     * @return
     */
    @GetMapping(value = "/girls/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") Integer age){
        return girlRepository.findByAge(age);
    }

    /**
     * 添加两个女孩
     */
    @PostMapping(value = "/girls/two")
    public void girlInserTwo(){
        girlService.insertTwoGirl();
    }
}
