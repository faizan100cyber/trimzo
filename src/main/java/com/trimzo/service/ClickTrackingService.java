package com.trimzo.service;

import com.trimzo.entity.Click;
import com.trimzo.entity.Url;
import com.trimzo.repository.ClickRepository;
import com.trimzo.repository.UrlRepository;
import com.trimzo.util.ReferrerParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClickTrackingService {

    private final ClickRepository clickRepository;
    private final UrlRepository urlRepository;
    private final GeoLocationService geoLocationService;
    private final DeviceDetectionService deviceDetectionService;
    private final ReferrerParser referrerParser;

    /**
     * Tracks a click event asynchronously.
     * Accepts extracted strings — NOT HttpServletRequest!
     * Request object gets recycled before async thread starts.
     */
    @Async
    @Transactional
    public void trackClick(String shortCode,
                           String ipAddress,
                           String userAgent,
                           String referrer) {
        try {
            Url url = urlRepository
                    .findByShortCode(shortCode)
                    .orElseThrow();

            Click click = new Click();
            click.setUrl(url);

            // Set IP address
            click.setIpAddress(ipAddress);

            // Geo lookup using IP
            String[] geo = geoLocationService.lookup(ipAddress);
            click.setCountry(geo[0]);
            click.setCity(geo[1]);

            // Device detection using User-Agent
            String[] device = deviceDetectionService
                    .parse(userAgent);
            click.setUserAgent(userAgent);
            click.setDeviceType(device[0]);
            click.setOs(device[1]);
            click.setBrowser(device[2]);

            // Referrer source detection
            click.setReferrer(referrer);
            click.setReferrerSource(
                    referrerParser.parse(referrer));

            clickRepository.save(click);

        } catch (Exception e) {
            log.error("Click tracking failed for {}: {}",
                    shortCode, e.getMessage());
        }
    }
}