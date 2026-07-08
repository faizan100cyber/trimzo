package com.trimzo.repository;

import com.trimzo.entity.Click;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClickRepository
        extends JpaRepository<Click, Long> {

    // Total clicks for a URL
    Long countByUrlId(Long urlId);

    // Unique clicks by IP address
    @Query("SELECT COUNT(DISTINCT c.ipAddress) " +
            "FROM Click c WHERE c.url.id = :urlId")
    Long countUniqueByUrlId(@Param("urlId") Long urlId);

    // Clicks grouped by country and city
    @Query("SELECT c.country, c.city, COUNT(c) " +
            "FROM Click c WHERE c.url.id = :urlId " +
            "GROUP BY c.country, c.city " +
            "ORDER BY COUNT(c) DESC")
    List<Object[]> getCountryStats(
            @Param("urlId") Long urlId);

    // Clicks grouped by device type
    @Query("SELECT c.deviceType, COUNT(c) " +
            "FROM Click c WHERE c.url.id = :urlId " +
            "GROUP BY c.deviceType " +
            "ORDER BY COUNT(c) DESC")
    List<Object[]> getDeviceStats(
            @Param("urlId") Long urlId);

    // Clicks grouped by referrer source
    @Query("SELECT c.referrerSource, COUNT(c) " +
            "FROM Click c WHERE c.url.id = :urlId " +
            "GROUP BY c.referrerSource " +
            "ORDER BY COUNT(c) DESC")
    List<Object[]> getReferrerStats(
            @Param("urlId") Long urlId);

    // Clicks per day between date range
    @Query("SELECT CAST(c.clickedAt AS date), COUNT(c) " +
            "FROM Click c WHERE c.url.id = :urlId " +
            "AND c.clickedAt BETWEEN :from AND :to " +
            "GROUP BY CAST(c.clickedAt AS date) " +
            "ORDER BY CAST(c.clickedAt AS date)")
    List<Object[]> getTimeline(
            @Param("urlId") Long urlId,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to);

    // Last click for a URL
    Optional<Click> findTopByUrlIdOrderByClickedAtDesc(
            Long urlId);
}