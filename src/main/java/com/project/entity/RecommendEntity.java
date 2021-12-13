package com.project.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.util.Assert;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recommend")
@NoArgsConstructor
@Getter
public class RecommendEntity {

    /** 유저 아이디 */
    private String userId;

    /** 상품 번호 */
    @Id
    private Integer productNo;

    @Builder
    public RecommendEntity(String userId, Integer productNo) {
        Assert.hasText(userId, "userId must not be empty");
        Assert.isNull(productNo, "productNo must not be empty");
        this.userId = userId;
        this.productNo = productNo;
    }

}