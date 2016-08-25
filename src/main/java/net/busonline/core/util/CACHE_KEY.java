package net.busonline.core.util;

public enum CACHE_KEY {
	SESSION("APPCLIENT.SESSION"),
	TOKEN("APPCLIENT.TOKEN");
	String val;
	CACHE_KEY(String val){
		this.val=val;
	}

	public String getVal(){
		return this.val;
	}

}
