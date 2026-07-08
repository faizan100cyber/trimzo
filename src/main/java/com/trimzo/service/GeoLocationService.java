package com.trimzo.service;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.net.InetAddress;

@Service
@Slf4j
public class GeoLocationService {

    private DatabaseReader dbReader;

    // Load GeoLite2 database when app starts
    @PostConstruct
    public void init() {
        try {
            File database = ResourceUtils.getFile(
                    "classpath:GeoLite2-City.mmdb");
            dbReader = new DatabaseReader
                    .Builder(database).build();
            log.info("GeoLite2 database loaded successfully");
        } catch (Exception e) {
            log.error("Failed to load GeoLite2 database: {}",
                    e.getMessage());
        }
    }

    /**
     * Returns country and city for the given IP address.
     * Returns "Unknown" if lookup fails.
     */
    public String[] lookup(String ipAddress) {
        try {
            InetAddress ip = InetAddress.getByName(ipAddress);
            CityResponse response = dbReader.city(ip);
            String country = response.getCountry().getName();
            String city = response.getCity().getName();
            return new String[]{
                    country != null ? country : "Unknown",
                    city != null ? city : "Unknown"
            };
        } catch (Exception e) {
            return new String[]{"Unknown", "Unknown"};
        }
    }
}