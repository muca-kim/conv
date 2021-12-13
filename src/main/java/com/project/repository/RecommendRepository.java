package com.project.repository;

import java.util.List;

import com.project.entity.RecommendEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendRepository extends JpaRepository<RecommendEntity, Integer> {

    public List<RecommendEntity> findByProductNoIn(List<Integer> productNoList);
}