package vn.vvinh.be.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import vn.vvinh.be.entity.Account;
import vn.vvinh.be.exception.ExpiredToken;
import vn.vvinh.be.exception.InValidToken;

import java.io.IOException;

@Component
public class Filter extends OncePerRequestFilter {
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Autowired
    TokenHandler tokenHandler;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("/login") || uri.contains("/register") || uri.contains("swagger-ui") || uri.contains("v3") || uri.contains("/verify") || uri.contains("/dashboard/admin")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = getToken(request);
        if (token == null) {
            resolver.resolveException(request, response, null, new InValidToken("Empty Token!"));
        } else {
            String id;
            try {
                id = tokenHandler.getInfoByToken(token);
            } catch (ExpiredJwtException expiredJwtException) {
                resolver.resolveException(request, response, null, new ExpiredToken("Expired Token!"));
                return;
            } catch (MalformedJwtException malformedJwtException) {
                resolver.resolveException(request, response, null, new InValidToken("Invalid Token!"));
                return;
            }

            if (id != null) {
                // token chuẩn
                // tạo 1 đối tượng mà spring security hiểu
                Account userDetails = (Account) userDetailsService.loadUserByUsername(id);
                //token hop le
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                // chạy đc tới đây => set up cho thằng spring security ok hết r
                // truy cập vào chạy api
                // filter cho phép truy cập thì mới đc truy cập
                filterChain.doFilter(request, response);
            } else {
                // token tào lao
                resolver.resolveException(request, response, null, new InValidToken("Invalid Token!"));
            }
        }
    }

    private String getToken(HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");
        if (authorization == null)
            return null;

        String token = authorization.split(" ")[1];
        return token;
    }
}
