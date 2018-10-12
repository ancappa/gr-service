package it.tim.gr.util;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.tim.gr.model.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class JWTUtils {

	public static final String JWT_SESSION_HEADER_NAME = "sessionJWT";
	public static final String NOT_VALID_MSISDN = "MSISDN non valido";
	
	public static JWTToken getAndValidateJWT(HttpHeaders headers, String msisdn) throws BadRequestException {
		
		if(headers == null) {
			throw new BadRequestException("HttpHeaders not set");
		}
		
		String sessionJWTString = headers.getFirst(JWT_SESSION_HEADER_NAME);

		if(sessionJWTString == null) {
			throw new BadRequestException("sessionJWT not set");
		}
		
    	log.debug("sessionJWTString=["+sessionJWTString+"]");
    	try {
    		
    		JWTToken jwt = decodeJWTString(sessionJWTString);

    		if(msisdn != null) {
    			validateMSISDN(msisdn, jwt.getSim());
    		}
    		
        	return jwt;
    	}
    	catch(BadRequestException bre) {
    		throw bre;
    	}
    	catch(Exception ex ) {
    		log.error("Error in jwt validation " + ex);
    		throw new BadRequestException("Error in sessionJWT validation");
    	}
    	
	}
	
	
	private static boolean validateMSISDN(String msisdn, List<JWTSIM> sim) throws BadRequestException{
		
		boolean res = false;
		if(sim==null || sim.isEmpty()) {
			log.error("SIM list empty");
			throw new BadRequestException(NOT_VALID_MSISDN);
		}
		else {

			for(int i=0; i<sim.size(); i++) {
				JWTSIM current = sim.get(i);
				if(msisdn.equals(current.getMsisdn())) {
					res = true;
					break;
				}
			}
		}
		
		if(!res) {
			log.error("MSISDN non valid");
			throw new BadRequestException(NOT_VALID_MSISDN);
		}
		
		return res;
	}
	
	
	
	public static JWTToken decodeJWTString(String sessionJWTString) throws Exception{
		
		
    	Jwt token = JwtHelper.decode(sessionJWTString);
    	//token.verifySignature(arg0);
    	
    	String decodedToken = token.getClaims();
    	log.debug("decoded token=["+decodedToken+"]");
    	
    	JWTToken jwt = new ObjectMapper().readValue(decodedToken, JWTToken.class);
    	
    	
    	log.debug("JWT=["+jwt+"]");

    	return jwt;
	}
	
	
}
