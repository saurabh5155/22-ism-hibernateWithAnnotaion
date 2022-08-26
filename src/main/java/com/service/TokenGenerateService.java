package com.service;

import org.springframework.stereotype.Service;

@Service
public class TokenGenerateService {
	
	public String generateTokan(int size) {
		String allWords = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
		String token = "";
		for(int i = 0; i<size;i++ ) {
			int index =(int) ( allWords.length() * Math.random());
			token = token + allWords.charAt(index);
		}
		return token;
	}
}
