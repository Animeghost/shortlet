package com.example.shortletBackend.jwt;

import com.example.shortletBackend.config.CustomDetailService;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private CustomDetailService customDetailService;

    public String getUserEmail(String accessToken) throws IOException {
        GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);

        Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(), new JacksonFactory(), credential).setApplicationName("Oauth2").build();

        Userinfo userinfo = oauth2.userinfo().get().execute();

        return userinfo.getEmail();
    }

    public String getUserName(String accessToken) throws IOException {
        GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);

        Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(), new JacksonFactory(), credential).setApplicationName("Oauth2").build();

        Userinfo userinfo = oauth2.userinfo().get().execute();

        return userinfo.getName();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try{
            String jwt = parseJwt(request);
            if (jwt != null ) {
                String username = getUserEmail(jwt);
                String Name = getUserName(jwt);

                UserDetails userDetails = customDetailService.loadUserByUsername(username,Name);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch (Exception e){
            logger.error("Cannot set user authentication {}", e);
        }

        chain.doFilter(request, response);



    }
    private String parseJwt(HttpServletRequest request){
        String headerAuth = request.getHeader("Authorization");

//        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")){
//            return headerAuth.substring(7, headerAuth.length());
//        }

        return headerAuth;
    }

}
