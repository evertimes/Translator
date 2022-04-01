package com.evertimes.translator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class IpService {
    public static final String X_FORWARDED_FOR = "X-FORWARDED-FOR";
    private HttpServletRequest request;

    @Autowired
    public IpService(HttpServletRequest request) {
        this.request = request;
    }

    public String getCurrentIP() {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader(X_FORWARDED_FOR);
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }
}
