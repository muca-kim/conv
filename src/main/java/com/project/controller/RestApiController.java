package com.project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class RestApiController {

    @PostMapping("/addRecommend")
    public String addRecommend(@RequestParam(required = false) String json) throws JsonProcessingException {
        if (log.isDebugEnabled()) {
            log.debug("Start addRecommend");
        }
        ObjectMapper mapper = new ObjectMapper();

        if (log.isDebugEnabled()) {
            log.debug("End addRecommend");
        }

        return mapper.writeValueAsString(null);

    }
}