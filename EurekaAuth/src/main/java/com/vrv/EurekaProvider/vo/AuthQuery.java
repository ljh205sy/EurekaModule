package com.vrv.EurekaProvider.vo;

public class AuthQuery {

	private String id;

	private String accessKey;

	private String secretKey;

	public AuthQuery() {

	}

	public AuthQuery(String accessKey, String secretKey) {
		this.accessKey = accessKey;
		this.secretKey = secretKey;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", accessKey=" + accessKey + ", secretKey=" + secretKey + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

}
