package com.example.api_insert_data.service;

import com.example.api_insert_data.entity.AlertMsgSource;
import com.fasterxml.jackson.core.JsonProcessingException;


public interface AlertMsgSourceService {

    AlertMsgSource saveData(String param) throws JsonProcessingException;
}
