package pl.bartekbiegajlo.pfswcho;

import io.ipgeolocation.api.Geolocation;
import io.ipgeolocation.api.GeolocationParams;
import io.ipgeolocation.api.GeolocationTimezone;
import io.ipgeolocation.api.IPGeolocationAPI;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HomeController {

    @Value("${geoip.api-key}")
    private String geoApiKey;

    private static final String[] IP_HEADERS = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };

    @GetMapping("/")
    public String getIpInfo(HttpServletRequest request) throws IOException {
        String clientIP = getIpAddr(request);
        System.out.println(clientIP);

        IPGeolocationAPI api = new IPGeolocationAPI(geoApiKey);
        GeolocationParams geoParams = new GeolocationParams();
        geoParams.setIPAddress(clientIP);
        geoParams.setFields("geo,time_zone,currency");

        Geolocation geolocation = api.getGeolocation(geoParams);
        int responseSatus = geolocation.getStatus();
        if (responseSatus == HttpStatus.LOCKED.value()) {
            return "Cannot get geolocation info - " + clientIP + " is a bogon IP address.";
        }
        if (responseSatus == HttpStatus.OK.value()) {
            GeolocationTimezone timezone = geolocation.getTimezone();
            return "IP address:\t" + clientIP + '\n'
                    + "Time zone:\t" + timezone.getName() + '\n'
                    + "Date:\t" + timezone.getCurrentTime();
        }

        return "Error: Cannot get geolocation data from IP (" + clientIP + ")";
    }

    private static String getIpAddr(HttpServletRequest request) {
        for (String header: IP_HEADERS) {
            String value = request.getHeader(header);
            if (value == null || value.isEmpty()) {
                continue;
            }
            String[] parts = value.split("\\s*,\\s*");
            return parts[0];
        }

        return request.getRemoteAddr();
    }
}
