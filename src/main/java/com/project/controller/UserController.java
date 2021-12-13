package com.project.controller;

import java.sql.SQLException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.common.Constants;
import com.project.common.Constants.Result;
import com.project.entity.UserEntity;
import com.project.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    /**
     * 새 유저를 등록함
     * 
     * @param json
     * @throws SQLException
     */
    @ResponseBody
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@RequestBody String json) throws SQLException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            UserEntity user = mapper.readValue(json, UserEntity.class);
            System.out.println("userId = " + user.getUserId());
            user.setRole(Constants.Role.PERSONAL.getValue());
            String pwd = user.getUserPassword();

            // 패스워드 인코딩
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setUserPassword(encoder.encode(pwd));

            // 유저 등록
            userRepository.save(user);
            return "{\"result\" : " + Result.SUCCESS.getValue() + "}";
        } catch (Exception e) {
            log.warn("user add fail");
            throw new SQLException();
        }
    }
}