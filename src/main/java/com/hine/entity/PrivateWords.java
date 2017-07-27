package com.hine.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户所选单词表
 * Created by 齐海阳
 * Date: 2017/7/27
 * Time: 14:49
 */
@Entity
@Table(name = "bas_private_words")
public class PrivateWords implements Serializable{

    private static final long serialVersionUID = -410699888127111407L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(columnDefinition = "bigint(32) comment '表主键Id'")
    private Long id;//表Id

    @NotNull
    @Column(name = "user_id", columnDefinition = "bigint(32) comment '用户Id'")
    private Long userId;

    @NotNull
    @Column(name = "words_id", columnDefinition = "bigint(32) comment '单词Id'")
    private Long wordsId;

    @NotNull
    @Column(name = "remember_number", columnDefinition = "int comment '当前记忆次数'")
    private Integer rememberNumber;

    @Temporal(TemporalType.TIME)
    @Column(name = "create_time", columnDefinition = "datetime comment '创建时间'")
    private Date createTime;

    public PrivateWords() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWordsId() {
        return wordsId;
    }

    public void setWordsId(Long wordsId) {
        this.wordsId = wordsId;
    }

    public Integer getRememberNumber() {
        return rememberNumber;
    }

    public void setRememberNumber(Integer rememberNumber) {
        this.rememberNumber = rememberNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "PrivateWords{" +
                "id=" + id +
                ", userId=" + userId +
                ", wordsId=" + wordsId +
                ", rememberNumber=" + rememberNumber +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
