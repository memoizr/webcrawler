package utils;

import java.util.HashMap;

// This was necessary as Bing seems to not return any result without realistic headers
// I copied the headers from a browser request and slapped them here. It would be interesting
// to find out exactly what's going on, but that'd take a while.
public class CommonHeaders {
    public static final HashMap<String, String> headers = new HashMap<String, String>() {
        {
            put("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:82.0) Gecko/20100101 Firefox/82.0");
            put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            put("Accept-Language", "en-GB,en;q=0.5");
            put("Connection", "keep-alive");
            put("Upgrade-Insecure-Requests", "1");
            put("Cache-Control", "max-age=0");
            put("TE", "Trailers");
        }
    };
}
