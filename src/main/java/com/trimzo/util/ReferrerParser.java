package com.trimzo.util;

import org.springframework.stereotype.Component;

@Component
public class ReferrerParser {

    /**
     * Parses the referrer URL into a readable source name.
     * Returns "Direct" if no referrer is present.
     */
    public String parse(String referrer) {
        if (referrer == null || referrer.isBlank()) {
            return "Direct";
        }

        String r = referrer.toLowerCase();

        if (r.contains("twitter.com") ||
                r.contains("t.co"))         return "Twitter";
        if (r.contains("facebook.com") ||
                r.contains("fb."))          return "Facebook";
        if (r.contains("instagram.com")) return "Instagram";
        if (r.contains("whatsapp"))      return "WhatsApp";
        if (r.contains("linkedin.com"))  return "LinkedIn";
        if (r.contains("youtube.com"))   return "YouTube";
        if (r.contains("google.com"))    return "Google";
        if (r.contains("github.com"))    return "GitHub";
        if (r.contains("telegram"))      return "Telegram";

        return "Other";
    }
}