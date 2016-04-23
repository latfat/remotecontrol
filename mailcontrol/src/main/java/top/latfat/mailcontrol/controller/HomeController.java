package top.latfat.mailcontrol.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.latfat.mailcontrol.entity.Result;
import top.latfat.mailcontrol.service.SiteService;

/**
 * 网站首页
 * @author Administrator
 *
 */
@RestController
public class HomeController {
	
	/**
	 * 网站首页
	 */
	private static final String HOME = "/home";
	
	@Resource
	private SiteService service;

	@RequestMapping(HOME)
	public Result home() {
		return service.sayHello();
	}
	
}
