package com.service;
import java.util.*;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.bean.EMSLoginBean;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class EMSLoginServices {
	
	
	public static EMSLoginBean generateJWTToken(String email,int role) {
		// Set the secret key

		HashMap<String, String> data = new HashMap<>();
		data.put("email", email);
		data.put("role", String.valueOf(role));
		// Set the algorithm
		String secretKey = "EMS".concat("_").concat(email);
		Algorithm algorithm = Algorithm.HMAC256(secretKey);
		
		// Create the JWT token
		String token = JWT.create()
		    .withIssuer("EMSTeam")
		    .withSubject("yourPassword")
		    .withAudience("users")
		    .withExpiresAt(new Date(System.currentTimeMillis() +  86400000))
		    .withIssuedAt(new Date(System.currentTimeMillis())).withClaim("data", data)
		    .sign(algorithm);
		
		return  new EMSLoginBean(token, secretKey);
		
	}
	
	public static EMSLoginBean geDataFromJWTToken(String token,String secretKey){
		

		// Set the algorithm
		try{
			Algorithm algorithm = Algorithm.HMAC256(secretKey);
			// Create a verifier
			JWTVerifier verifier = JWT.require(algorithm)
			    .withIssuer("EMSTeam")
			    .withSubject("yourPassword")
			    .withAudience("users")
			    .build();

			// Verify the token
			DecodedJWT jwt = verifier.verify(token);
			
			Map<String, Claim> claims = jwt.getClaims();
			
			Map<String, Object> data = (Map<String, Object>) claims.get("data").asMap();
			
			return new EMSLoginBean((String) data.get("email"), (String) data.get("role"));
		}catch(Exception E) {
			E.printStackTrace();
		}
			return null;
		
	}
	
	
	
}
