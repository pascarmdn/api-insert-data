package com.example.api_insert_data.service.impl;

import com.example.api_insert_data.entity.AlertMsgSource;
import com.example.api_insert_data.repository.AlertMsgSourceRepository;
import com.example.api_insert_data.service.AlertMsgSourceService;
import com.example.api_insert_data.util.DateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AlertMsgSourceImpl implements AlertMsgSourceService {
    @Autowired
    AlertMsgSourceRepository alertMsgSourceRepository;

    @Autowired
    DateUtil dateUtil;

    @Override
    public AlertMsgSource saveData(String param) throws JsonProcessingException {
        AlertMsgSource data = new AlertMsgSource();
        ObjectMapper objectMapper = new ObjectMapper();
        DecimalFormat df = new DecimalFormat("#.##");

        JsonNode dataJson = objectMapper.readTree(param);

        int totalSuccess = 0;
        int totalFailed = 0;
        for (JsonNode dataList : dataJson.get("list")) {
            if (dataList.get("transactionStatus").asText().equalsIgnoreCase("success")) {
                totalSuccess += dataList.get("totalTransaction").asInt();
            } else {
                totalFailed += dataList.get("totalTransaction").asInt();
            }
        }
        data.setServicesCode(dataJson.get("projectName").asText());
        data.setSourceDate(dataJson.get("sourceDate").asText());
        data.setFrekuensi(AlertMsgSource.Frekuensi.valueOf(dataJson.get("frekuensi").asText()));
        data.setProcessDate(LocalDateTime.now());
        data.setProcessStartTime(dateUtil.stringTimeToLocalTome(dataJson.get("timeStarts").asText(), "HHmmss"));
        data.setProcessEndTime(dateUtil.stringTimeToLocalTome(dataJson.get("timeEnd").asText(), "HHmmss"));
        data.setProcessTimeInterval(dateUtil.processTimeInterval(data.getProcessStartTime(), data.getProcessEndTime()));
        data.setMemberAlias(dataJson.get("nameMember").asText());
        data.setMemberCode(dataJson.get("memberCode").asText());
        data.setTotalTrx(dataJson.get("totalAllTransaction").asInt());
        data.setTotalTrxSuccess(totalSuccess);
        data.setTotalTrxFailed(totalFailed);
        data.setTotalPercentSuccess(Double.parseDouble(df.format(((double) totalSuccess
                / (totalSuccess + totalFailed)) * 100)));
        data.setTotalPercentFailed(Double.parseDouble(df.format(((double) totalFailed
                / (totalSuccess + totalFailed)) * 100)));
        data.setDetailPayload(dataJson.toString());

        return alertMsgSourceRepository.save(data);
    }
}
