package top.latfat.mailcontrol.service;

import top.latfat.mailcontrol.domain.Result;

/**
 * 网页相关服务
 * @author Administrator
 *
 */
public interface SiteBaseService {

	/**
	 * 为所有网页相关服务提供统一返回状态对象
	 * 默认成功
	 */
	Result result = new Result(Result.SUCCESS, "成功" ,null);
}