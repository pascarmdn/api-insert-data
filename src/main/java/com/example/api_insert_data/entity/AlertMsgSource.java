package com.example.api_insert_data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "t_alert_msg_source")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertMsgSource {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "services_code", nullable = false, length = 16)
    private String servicesCode;

    @Column(name = "source_date", nullable = false, length = 6)
    private String sourceDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "frekuensi", nullable = false)
    private Frekuensi frekuensi;

    @Column(name = "process_date")
    private LocalDateTime processDate;

    @Column(name = "process_start_time")
    private LocalTime processStartTime;

    @Column(name = "process_end_time")
    private LocalTime processEndTime;

    @Column(name = "process_time_interval")
    private Long processTimeInterval;

    @Column(name = "member_alias")
    private String memberAlias;

    @Column(name = "member_code")
    private String memberCode;

    @Column(name = "total_trx")
    private Integer totalTrx;

    @Column(name = "total_trx_success")
    private Integer totalTrxSuccess;

    @Column(name = "total_percent_success")
    private Double totalPercentSuccess;

    @Column(name = "total_trx_failed")
    private Integer totalTrxFailed;

    @Column(name = "total_percent_failed")
    private Double totalPercentFailed;

    @Column(name = "detail_payload", columnDefinition = "json")
    private String detailPayload;


    public enum Frekuensi {
        DAILY, HOUR, MINUTE
    }
}
