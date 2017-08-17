package com.example.user.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class KongClientAPP {

	private String id;

	@SerializedName("consumer_id")
	private String consumerId;

	@SerializedName("client_id")
	private String clientId;

	@SerializedName("redirect_uri")
	private List<String> redirectUri;

	private String name;

	@SerializedName("created_at")
	private Long createdAt;

	@SerializedName("client_secret")
	private String clientSecret;

	public String getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public List<String> getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(List<String> redirectUri) {
		this.redirectUri = redirectUri;
	}

	@Override
	public String toString() {
		return "KongClientAPP [id=" + id + ", consumerId=" + consumerId + ", clientId=" + clientId
				+ ", redirectUri=" + redirectUri + ", name=" + name + ", createdAt=" + createdAt
				+ ", clientSecret=" + clientSecret + "]";
	}

	

}