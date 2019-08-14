package com.security;

import java.nio.file.attribute.UserPrincipal;
import java.security.SignatureException;
import java.util.Date;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class JWToken {

    @Value("isa-project")
    private String appname;

	@Value("secret")
	private String secret;
	
	@Value("6000")
	private int expires;
	
	
	private static final Logger logger = LoggerFactory.getLogger(JWToken.class);
	 

    public String generateJWToken(Authentication authentication) {
 
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        System.out.println(userDetails.getUsername());
 
        return Jwts.builder()
                        .setIssuer(appname)
		                .setSubject((userDetails.getUsername()))
		                .setIssuedAt(new Date())
		                .setExpiration(genExpDate())
		                .signWith(SignatureAlgorithm.HS512, secret)
		                .compact();
    }
    
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: ", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: ", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: ", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: ", e);
        } 
        
        return false;
    }
    
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(secret)
			                .parseClaimsJws(token)
			                .getBody().getSubject();
    }

    private Date genExpDate() {
        return new Date((new Date()).getTime() + expires * 1000);
    }


    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }


    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            claims.setIssuedAt(new Date());
            refreshedToken = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(genExpDate())
                    .signWith(SignatureAlgorithm.HS512, secret)
                    .compact();
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }

        return null;
    }








}
