package com.lefu.user.bean;

import com.google.gson.annotations.SerializedName;
import com.lefu.common.BaseSerializableBean;

public class TokenBean extends BaseSerializableBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SerializedName("refresh_token")
	private String refreshToken;

	@SerializedName("token_type")
	private String tokenType;

	@SerializedName("access_token")
	private String accessToken;

	@SerializedName("expires_in")
	private String expiresIn;

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TokenBean [refreshToken=");
		builder.append(refreshToken);
		builder.append(", tokenType=");
		builder.append(tokenType);
		builder.append(", accessToken=");
		builder.append(accessToken);
		builder.append(", expiresIn=");
		builder.append(expiresIn);
		builder.append("]");
		return builder.toString();
	}


}
