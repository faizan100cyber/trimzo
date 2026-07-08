package com.trimzo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "clicks")
@Getter
@Setter
@NoArgsConstructor
public class Click {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Which URL was clicked
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "url_id")
    private Url url;

    // When was it clicked
    @Column(name = "clicked_at")
    private LocalDateTime clickedAt = LocalDateTime.now();

    // Geo data
    @Column(name = "ip_address")
    private String ipAddress;

    private String country;
    private String city;

    // Device data
    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "device_type")
    private String deviceType;

    private String os;
    private String browser;

    // Referrer data
    private String referrer;

    @Column(name = "referrer_source")
    private String referrerSource;
}