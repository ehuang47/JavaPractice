package com.example.d5_mvc_jsp_quiz.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@WebFilter("/*")
public class AuthFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain)
    throws ServletException, IOException {

    final HttpSession session = request.getSession(false);
    final String path = request.getRequestURI();

    final List<String> authPaths = List.of("/login", "/register");
//todo: differentiate based on user role for admin pages
    if (session != null && session.getAttribute("userId") != null) {
      if (authPaths.contains(path)) {
        response.sendRedirect("/home");
      } else {
        filterChain.doFilter(request, response);
      }
    } else {
      // allow user to go to login or registration path if they're not logged in
      if (authPaths.contains(path)) {
        filterChain.doFilter(request, response);
      } else {
        // redirect back to the login page if user is not logged in
        response.sendRedirect("/login");
      }
    }
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    final String path = request.getRequestURI();
    // path may have prefix /WEB-INF/jsp
    return path.contains("/contact-us") && !path.contains("/management");
  }
}
