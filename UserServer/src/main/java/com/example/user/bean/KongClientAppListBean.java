package com.example.user.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class KongClientAppListBean {

	@SerializedName("data")
	private List<KongClientAPP> apps;
	private int total;

	public List<KongClientAPP> getApps() {
		return apps;
	}

	public void setApps(List<KongClientAPP> apps) {
		this.apps = apps;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "KongClientAppListBean [apps=" + apps + ", total=" + total + "]";
	}

}
