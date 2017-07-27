package com.hine.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户表
 * Created by 齐海阳
 * Date: 2017/7/27
 * Time: 15:26
 */
@Entity
@Table(name = "bas_user")
public class User implements Serializable {
    private static final long serialVersionUID = 6102293820780531301L;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(columnDefinition = "bigint(32) comment '表主键Id'")
    private Long id;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                '}';
    }
}
