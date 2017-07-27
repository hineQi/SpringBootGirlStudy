package com.hine.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户所需记忆单词表
 * Created by 齐海阳
 * Date: 2017/7/27
 * Time: 15:00
 */
@Entity
@Table(name = "bas_need_recite_words")
public class NeedReciteWords implements Serializable{

    private static final long serialVersionUID = -8648210371490953953L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(columnDefinition = "bigint(32) comment '表主键Id'")
    private Long id;

    @NotNull
    @Column(name = "user_id", columnDefinition = "bigint(32) comment '用户Id'")
    private Long userId;

    @NotNull
    @Column(name = "words_id", columnDefinition = "bigint(32) comment '单词Id'")
    private Long wordsId;

    @NotNull
    @Column(name = "private_words_id", columnDefinition = "bigint(32) comment '关联用户所选单词表Id'")
    private Long privateWordsId;

    @NotNull
    @Column(name = "status", columnDefinition = "int comment '状态'")
    private Integer status;

    @NotBlank
    @Temporal(TemporalType.TIME)
    @Column(name = "create_time", columnDefinition = "datetime comment '创建时间'")
    private Date createTime;

    @NotBlank
    @Temporal(TemporalType.TIME)
    @Column(name = "update_status_time", columnDefinition = "datetime comment '修改状态时间'")
    private Date updateStatusTime;

    @NotNull
    @Column(name = "finish_number", columnDefinition = "int comment '完成次数'")
    private Integer finishNumber;

    public NeedReciteWords() {
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

    public Long getPrivateWordsId() {
        return privateWordsId;
    }

    public void setPrivateWordsId(Long privateWordsId) {
        this.privateWordsId = privateWordsId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateStatusTime() {
        return updateStatusTime;
    }

    public void setUpdateStatusTime(Date updateStatusTime) {
        this.updateStatusTime = updateStatusTime;
    }

    public Integer getFinishNumber() {
        return finishNumber;
    }

    public void setFinishNumber(Integer finishNumber) {
        this.finishNumber = finishNumber;
    }

    @Override
    public String toString() {
        return "NeedReciteWords{" +
                "id=" + id +
                ", userId=" + userId +
                ", wordsId=" + wordsId +
                ", privateWordsId=" + privateWordsId +
                ", status=" + status +
                ", createTime='" + createTime + '\'' +
                ", updateStatusTime='" + updateStatusTime + '\'' +
                ", finishNumber=" + finishNumber +
                '}';
    }
}
