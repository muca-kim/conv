package com.project.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "product_def")
public class ProductEntity {

    /** 상품등록번호 */
    @Id
    @Getter
    private Integer productNo;
    /** 상품 이름 */
    @Getter
    private String productName;
    /** 설명 */
    @Getter
    private String description;
    /** 추천수 */
    @Getter
    private Integer recommend;
    /** 이미지주소 */
    @Getter
    private String productImg;

    @Transient
    @Setter
    private List<RecommendEntity> recommandList;

    @Builder
    public ProductEntity(String productName, String description, String productImg) {
        this.productName = productName;
        this.description = description;
        this.productImg = productImg;
    }

}