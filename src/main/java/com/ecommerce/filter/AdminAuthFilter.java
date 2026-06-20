package com.ecommerce.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebFilter;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;

@WebFilter("/jsp/admin/*")
public class AdminAuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {

    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) request;

        HttpServletResponse httpResponse =
                (HttpServletResponse) response;

        HttpSession session =
                httpRequest.getSession(false);

        boolean isLoggedIn =
                session != null
                && session.getAttribute("loggedInAdmin") != null;

        boolean isLoginPage =
                httpRequest.getRequestURI()
                .endsWith("admin-login.jsp");

        if (isLoggedIn || isLoginPage) {

            chain.doFilter(request, response);

        } else {

            httpResponse.sendRedirect(
                    httpRequest.getContextPath()
                    + "/jsp/admin/admin-login.jsp"
            );
        }
    }

    @Override
    public void destroy() {

    }
}