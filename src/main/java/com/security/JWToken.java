package com.security;

import java.nio.file.attribute.UserPrincipal;
import java.security.SignatureException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.service.UserDetails;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWToken {
	

	@Value("secret")
	private String secret;
	
	@Value("6000")
	private int expires;
	
	
	private static final Logger logger = LoggerFactory.getLogger(JWToken.class);
	 

    public String generateJWToken(Authentication authentication) {
 
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        System.out.println(userDetails.getUsername());
 
        return Jwts.builder()
		                .setSubject((userDetails.getUsername()))
		                .setIssuedAt(new Date())
		                .setExpiration(new Date((new Date()).getTime() + expires*1000))
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
	
	
	
	
	

}
