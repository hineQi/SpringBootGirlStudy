package com.hine.controller;

import com.hine.entity.Girl;
import com.hine.mapper.GirlMapper;
import com.hine.service.GirlService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
    private GirlMapper girlRepository;

    @Autowired
    private GirlService girlService;


    /**
     * 查询所有女生列表
     * @return
     */
    @ApiOperation(value = "获取所有女生列表", notes = "")
    @GetMapping(value = "/girls")
    public List<Girl> girlList(){
        return girlRepository.findAll();
    }

    /**
     * 添加女生
     * @param girl
     * @return
     */
    @ApiOperation(value = "创建女生", notes = "根据Girl对象创建女生")
    @ApiImplicitParam(name = "girl", value = "女生详细实体girl", required = true, dataType = "Girl")
    @PostMapping(value = "/girls")
    public int girlAdd(@RequestBody Girl girl){
        return girlRepository.save(girl);
    }

    /**
     * 获取一个女生信息
     * @param id
     * @return
     */
    @ApiOperation(value = "获取女生详细信息", notes = "根据url的id来获取女生详细信息")
    @ApiImplicitParam(name = "id", value = "女生ID", paramType="path", required = true, dataType = "Long")
    @GetMapping(value = "/girls/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id){
        return girlRepository.findOne(id);
    }

    /**
     * 修改某个女生信息
     * @param girl
     * @return
     */
    @ApiOperation(value = "更新女生详细信息", notes = "根据url的id来指定更新对象，并根据传过来的girl信息来更新女生详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "女生ID", paramType="path", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "girl", value = "女生详细实体girl", required = true, dataType = "Girl")
    })
    @PutMapping(value = "/girls/{id}")
    public int girlUpdate(Girl girl){
        return girlRepository.save(girl);
    }

    /**
     * 删除某个女生
     * @param id
     */
    @ApiOperation(value="删除女生", notes="根据url的id来指定删除对象")
    @DeleteMapping(value = "/girls/{id}")
    @ApiImplicitParam(name = "id", value = "女生ID", paramType="path", required = true, dataType = "Long")
    public void girlDelete(@PathVariable("id") Integer id){
        girlRepository.delete(id);
    }

    /**
     * 通过年龄查询女生列表
     * @param age
     * @return
     */
    @ApiOperation(value="通过年龄查询女生列表", notes="根据url的age来查询女生列表")
    @ApiImplicitParam(name = "age", value = "女生Age", paramType="path", required = true, dataType = "int")
    @GetMapping(value = "/girls/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") Integer age){
        return girlRepository.findByAge(age);
    }

    /**
     * 添加两个女孩
     */
    @ApiIgnore
    @PostMapping(value = "/girls/two")
    public void girlInserTwo(){
        girlService.insertTwoGirl();
    }
}
