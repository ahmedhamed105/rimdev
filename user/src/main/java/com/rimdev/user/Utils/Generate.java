package com.rimdev.user.Utils;

import java.security.SecureRandom;

public class Generate {
	

	 final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	public  String token(int length){
		SecureRandom rnd = new SecureRandom();
		   StringBuilder sb = new StringBuilder( length );
		   for( int i = 0; i < length; i++ ) 
		      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		   return sb.toString();
	}

}
