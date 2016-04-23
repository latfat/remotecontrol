package top.latfat.mailcontrol.domain;

import java.io.Serializable;

/**
 * 前端Ajax交互结果封装
 * @author Administrator
 *
 */
public class Result implements Serializable {

	/**
	 * class id
	 */
	private static final long serialVersionUID = -5091849099726334461L;
	
	/**
	 * 状态码：成功
	 */
	public static final int SUCCESS = 1;
	
	/**
	 * 状态码：失败
	 */
	public static final int ERROR = 0;
	
	public Result() {
		
	}
	
	public Result(int status, String msg, Object data) {
		setAll(status, msg, data);
	}
	
	/**
	 * Ajax状态
	 * 	0：失败
	 * 	！0：其他状态
	 */
	private int status;
	
	/**
	 * 返回信息
	 */
	private String msg;
	
	/**
	 * 返回数据
	 */
	private Object data;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public Result setAll(int status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
		return this;
	}

}
