package com.trimzo.service;

import org.springframework.stereotype.Service;
import ua_parser.Client;
import ua_parser.Parser;

@Service
public class DeviceDetectionService {

    private final Parser uaParser = new Parser();

    /**
     * Parses User-Agent header to extract device info.
     * Returns array: [deviceType, os, browser]
     */
    public String[] parse(String userAgent) {
        if (userAgent == null || userAgent.isBlank()) {
            return new String[]{"Unknown", "Unknown", "Unknown"};
        }

        try {
            Client client = uaParser.parse(userAgent);

            // If device family is "Other" it means desktop
            String deviceType = client.device.family
                    .equalsIgnoreCase("Other")
                    ? "desktop" : "mobile";

            String os = client.os.family;
            String browser = client.userAgent.family;

            return new String[]{deviceType, os, browser};

        } catch (Exception e) {
            return new String[]{"Unknown", "Unknown", "Unknown"};
        }
    }
}