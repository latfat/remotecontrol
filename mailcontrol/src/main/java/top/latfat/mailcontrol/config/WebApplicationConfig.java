package top.latfat.mailcontrol.config;

import java.util.List;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import top.latfat.mailcontrol.interceptor.CustomizedExceptionResolver;
/**
 * 拦截器配置
// 				    _ooOoo_ 
// 		  	       o8888888o 
// 	  	  	       88" . "88 
//                 (| -_- |) 
//                  O\ = /O 
//              ____/`---*\____ 
//               . * \\| |// `. 
//             / \\||| : |||// \ 
//           / _||||| -:- |||||- \ 
//             | | \\\ - /// | | 
//            | \_| **\---/** | | 
//           \  .-\__ `-` ___/-. / 
//            ___`. .* /--.--\ `. . __ 
//        ."" *< `.___\_<|>_/___.* >*"". 
//      | | : `- \`.;`\ _ /`;.`/ - ` : | | 
//         \ \ `-. \_ __\ /__ _/ .-` / / 
//======`-.____`-.___\_____/___.-`____.-*====== 
// `=---=* 
// 
// ............................................. 
//              佛祖保佑 永无BUG 
 * @author seanwg
 * 2016-4-21 下午09:30:50
 */
@org.springframework.context.annotation.Configuration
public class WebApplicationConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 添加SESSION拦截器
		// 添加防SQL注入拦截器
	}

	@Override
	public void configureHandlerExceptionResolvers(
			List<HandlerExceptionResolver> exceptionResolvers) {
		// 添加全局异常拦截
		exceptionResolvers.add(new CustomizedExceptionResolver());
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
	}
}
