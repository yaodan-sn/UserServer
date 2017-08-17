package com.example.user.bean;

public class KongOauth2AuthorizeBean {

	private String clientId;
	private String hostname;
	private String responseType;
	private String scope;
	private String provisionKey;
	private String authenticatedUserid;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getProvisionKey() {
		return provisionKey;
	}

	public void setProvisionKey(String provisionKey) {
		this.provisionKey = provisionKey;
	}

	public String getAuthenticatedUserid() {
		return authenticatedUserid;
	}

	public void setAuthenticatedUserid(String authenticatedUserid) {
		this.authenticatedUserid = authenticatedUserid;
	}

	@Override
	public String toString() {
		return "KongOauth2AuthorizeBean [clientId=" + clientId + ", hostname=" + hostname
				+ ", responseType=" + responseType + ", scope=" + scope + ", provisionKey="
				+ provisionKey + ", authenticatedUserid=" + authenticatedUserid + "]";
	}



}
