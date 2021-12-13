package com.project.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.project.entity.RecommendEntity;
import com.project.repository.RecommendRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendService {

    @Autowired
    private RecommendRepository repository;

    public List<RecommendEntity> findByProductNoIn(List<Integer> productNoList) {
        return repository.findByProductNoIn(productNoList);
    }

    public Map<Integer, List<RecommendEntity>> getRecommendMap(List<Integer> productNoList) {
        return this.findByProductNoIn(productNoList).stream()
                .collect(Collectors.groupingBy(RecommendEntity::getProductNo));
    }
}