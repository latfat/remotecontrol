package top.latfat.mailcontrol.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON工具
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
 * 2016-4-21 下午11:11:43
 */
public class JSON {
	
	/**
	 * Jackson mapper对象
	 */
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * 将一个对象转换为JSON字符串
	 * @param object
	 * @return
	 */
	public static String toJsonString(Object object) {
		String result = "";
		try {
			result =  mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {}
		return result;
	}
}
