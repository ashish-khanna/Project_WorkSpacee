package com.neushuttle.projectDAO;

import java.util.HashMap;

public class JsonResponse {

	private String status;
	private String msg;
	private HashMap<String, Object> data;
	

	/**
	 * @param status
	 * @param msg
	 * @param data
	 */
	public JsonResponse(String status, String msg, HashMap<String, Object> data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * @return the data
	 */
	public HashMap<String, Object> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(HashMap<String, Object> data) {
		this.data = data;
	}
		


	
}
