package net.busonline.core.model;

public class Response {
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	//private Meta meta;
	private String code;
	private String msg;
	private Object data;
	public Response success() {
		this.code = "200";
		this.msg = SUCCESS;
		//this.meta = new Meta(true, SUCCESS,"200");
		return this;
	}

	public Response success(Object data) {
		this.code = "200";
		this.msg = SUCCESS;
		//this.meta = new Meta(true, SUCCESS,"200");
		this.data = data;
		return this;
	}

	public Response failure() {
		//this.meta = new Meta(false, ERROR,"500");
		this.code = "500";
		this.msg = ERROR;
		return this;
	}

	public Response failure(String message,String code) {
		this.code = code;
		this.msg = message;
	    return this;
	}
	public String getCode() {
		return code;
	}
/*	public Meta getMeta() {
		return meta;
	}
*/
	public Object getData() {
		return data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
