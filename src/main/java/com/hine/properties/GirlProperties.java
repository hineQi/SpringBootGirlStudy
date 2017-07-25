package com.hine.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by 齐海阳
 * 2017-06-22 16:59.
 */
@Component
@ConfigurationProperties(prefix = "girl")
@PropertySource("classpath:/propertiesConfig/girl.yml")
public class GirlProperties {

    private String cupSize;

    private Integer age;

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    //重写toString();
    public String toString(){
        return "这个女孩cupSize："+cupSize+",年龄： "+age+"。";
    }
}
