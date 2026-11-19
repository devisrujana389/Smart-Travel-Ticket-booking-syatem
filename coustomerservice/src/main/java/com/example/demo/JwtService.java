package com.example.demo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private static final String secretkey =
			"4CwpWQv98nYH8WQ5Bv8R/7J7+7TQ8Yt2q3D3M1RrP6g=";
	
//	public JwtService() {
//		try {
//			KeyGenerator KeyGen=KeyGenerator.getInstance("HmacSHA256");
//			SecretKey sk=KeyGen.generateKey();
//			secretkey=Base64.getEncoder().encodeToString(sk.getEncoded());
//		}catch(NoSuchAlgorithmException e) {
//			throw new RuntimeException(e);
//		}
//	}
	public String generateToken(String userName) {
		
		Map<String,Object> claims=new HashMap<>();
		return Jwts.builder()
				.claims()
				.add(claims)
				.subject(userName)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+1000* 60* 30))
				.and()
				.signWith(getKey(), SignatureAlgorithm.HS256)
				.compact();
		
		
//		return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJuYXZpbiIsImlhdCI6MTc4NTczMjQwNywiZXhwIjoxNzg1NzM2MDA3fQ._GXTBhuUoF-Ikiv9fSWE5LJ7lPFFtdB-ezwbavc1Xuw";
	}

	private SecretKey getKey() {
		byte[] keyBytes=Decoders.BASE64.decode(secretkey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	public String extractUserName(String token) {
		
		return extractClaims(token,Claims::getSubject) ;
	}
	private <T> T extractClaims(String token,Function<Claims, T> claimResolver) {
		final Claims claims=extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	private Claims extractAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(getKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	public boolean validateToken(String token, UserDetails userDetails) {
		final String userName=extractUserName(token);
		return (userName.equals(userDetails.getUsername())&& !isTokenExpired(token));
	}
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	private Date extractExpiration(String token) {
		return extractClaims(token,Claims::getExpiration);
	}
}
