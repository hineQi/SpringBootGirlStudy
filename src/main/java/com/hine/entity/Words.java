package com.hine.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 单词表
 * Created by 齐海阳
 * Date: 2017/7/27
 * Time: 11:20
 */
@Entity
@Table(name = "bas_words")
public class Words implements Serializable {
    private static final long serialVersionUID = -3259561266868496095L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(columnDefinition = "bigint(32) comment '表主键Id'")
    private Long id;

    @NotBlank
    @Column(columnDefinition = "varchar(255) comment '单词内容'")
    private String content;

    public Words() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Words{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
