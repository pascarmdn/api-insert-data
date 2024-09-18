package com.example.api_insert_data.controller;

import com.example.api_insert_data.entity.AlertMsgSource;
import com.example.api_insert_data.service.AlertMsgSourceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class InsertDataController {

    @Autowired
    AlertMsgSourceService alertMsgSourceService;

    @PostMapping("/post")
    public ResponseEntity<Map<String, Object>> getAllData(@RequestBody String param) throws JsonProcessingException {
        try {
            AlertMsgSource alertMsgSource = alertMsgSourceService.saveData(param);

            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.OK.value());
            response.put("message", "Product successfully saved.");
            response.put("data", alertMsgSource);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
