package com.hine.controller;

import com.hine.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 齐海阳
 * 2017-06-22 16:42.
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private GirlProperties girlProperties;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello(){
        return "Hello SpringBootGirlStudy!";
    }

    @RequestMapping(value = "/printGirlProperties/{id}", method = RequestMethod.GET)
    public String printGirlProperties(@PathVariable Long id){
        return "id："+id+"的"+girlProperties.toString();
    }

    @RequestMapping(value = "/printGirlProperties2", method = RequestMethod.GET)
    public String printGirlProperties2(@RequestParam("id") Long id2){
        return "id："+id2+"的"+girlProperties.toString();
    }

    @PostMapping(value = "/postTest")
    public String postRequestTesting(){
        return "postRequestTesting";
    }

}
