package com.kuaidi100.bdindex.sercurity.filter;

import io.jsonwebtoken.lang.Assert;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public final class RequestCookieRequestMatcher implements RequestMatcher {
    private final String expectCookieName;
    private final String expectedCookieValue;

    public RequestCookieRequestMatcher(String expectedCookieName) {
        this(expectedCookieName, (String) null);
    }

    public RequestCookieRequestMatcher(String expectedCookieName, String expectedCookieValue) {
        Assert.notNull(expectedCookieName, "headerName cannot be null");
        this.expectCookieName = expectedCookieName;
        this.expectedCookieValue = expectedCookieValue;
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        boolean flag = false;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if ("TOKEN".equals(name)) {
                flag = true;
                return flag;
            }
        }
        return flag;
    }

    @Override
    public String toString() {
        return "RequestCookieRequestMatcher [expectedCookieName=" + this.expectCookieName + ", expectedCookieValue=" + this.expectedCookieValue + "]";
    }
}
