package com.dev.INVE_SVC.utils;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class TrackingFilter {


	@Autowired
	FilterUtils filterUtils;

	public String getAuthenticationName(String vToken, String colname){
		String authenticationName = "";
		if (vToken!=null){
			String authToken = vToken.replace("Bearer ","");
	        JSONObject jsonObj = decodeJWT(authToken);
	        authenticationName = jsonObj.getString(colname);
		}
		return authenticationName;
	}

	private JSONObject decodeJWT(String JWTToken) {
		String[] split_string = JWTToken.split("\\.");
		String base64EncodedBody = split_string[1];
		Base64 base64Url = new Base64(true);
		String body = new String(base64Url.decode(base64EncodedBody));
		JSONObject jsonObj = new JSONObject(body);
		return jsonObj;
	}
}