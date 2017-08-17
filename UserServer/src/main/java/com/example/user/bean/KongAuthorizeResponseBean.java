package com.example.user.bean;

import com.google.gson.annotations.SerializedName;

public class KongAuthorizeResponseBean {

	@SerializedName("redirect_uri")
	private String redirectUri;

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	@Override
	public String toString() {
		return "KongAuthorizeResponseBean [redirectUri=" + redirectUri + "]";
	}

}
