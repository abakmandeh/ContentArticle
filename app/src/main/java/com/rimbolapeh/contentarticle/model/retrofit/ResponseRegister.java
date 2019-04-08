package com.rimbolapeh.contentarticle.model.retrofit;


import com.google.gson.annotations.SerializedName;

// dibuat dengan generate pojo from json
// kalo yg merah hapus aja
public class ResponseRegister {

	@SerializedName("response")
	private String response;

	public void setResponse(String response){
		this.response = response;
	}

	public String getResponse(){
		return response;
	}
}