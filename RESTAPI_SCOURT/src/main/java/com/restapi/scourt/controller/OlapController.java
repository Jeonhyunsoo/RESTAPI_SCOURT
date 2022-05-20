package com.restapi.scourt.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.restapi.scourt.utils.SecureUtils;

@RestController
public class OlapController {
	
//	@RequestMapping("/olap/encode")
//    public String encode(@RequestParam("uid") OlapVO result) {
//		return result.getUid().toString() + " olap test";
//    }
	
	@RequestMapping("/jhs")
    //public String encode(OlapVO result) throws Exception {
	public String encode(HttpServletRequest request, HttpServletResponse reponse, Model model) throws Exception {
	//public ModelAndView encode(ModelAndView mav) throws Exception {
		String key = "VmxSS0BNRlV4YkZoVGJrNXFVbTFTVmxsVVRrTldWbXhYWVVaT2JHSkhlREZaYTFacllWWmFWV0pGVmxWV2JFcFVWbGQ0UzFOR1VsbGFSbFpPVmxaVk1WWlZXa1pQVmtKU1VGUXdQUT09";
//		String url = "jdbc:tibero:thin:@140.5.1.60:8629:dwvrf";
//		String uid = "wisebir4";
//		String pwd = "wisebir41012";
		
		String flag = request.getParameter("action");
		String url = request.getParameter("url");
		String uid = request.getParameter("uid");
		String pwd = request.getParameter("pwd");
		
		if (flag == "encode") {
			model.addAttribute("resultUrl", SecureUtils.encSeed(key, url));
			model.addAttribute("resultUid", SecureUtils.encSeed(key, uid));
			model.addAttribute("resultPwd", SecureUtils.encSeed(key, pwd));
		
		}else if (flag == "decode") {
			model.addAttribute("resultUrl", SecureUtils.decSeed(key, url));
			model.addAttribute("resultUid", SecureUtils.decSeed(key, uid));
			model.addAttribute("resultPwd", SecureUtils.decSeed(key, pwd));
		}
		
		return "jhs";
    }
	
	@RequestMapping("/main")
	public ModelAndView encode(HttpServletRequest request, HttpServletResponse response) throws Exception { response.setCharacterEncoding("utf-8");
		ModelAndView model = new ModelAndView("main");
		String seed = request.getParameter("seed");
		if ("".equals(seed) || seed == null) seed = "VmxSS0BNRlV4YkZoVGJrNXFVbTFTVmxsVVRrTldWbXhYWVVaT2JHSkhlREZaYTFacllWWmFWV0pGVmxWV2JFcFVWbGQ0UzFOR1VsbGFSbFpPVmxaVk1WWlZXa1pQVmtKU1VGUXdQUT09";
		String flag = request.getParameter("action");
		String url = request.getParameter("url");
		String uid = request.getParameter("uid");
		String pwd = request.getParameter("pwd");
		
		Map<String,String> result = new HashMap<String,String>();

		result.put("seed", seed);
		result.put("url", url);
		result.put("uid", uid);
		result.put("pwd", pwd);
		
		try {
			if ("encode".equals(flag)) {
				result.put("msg", "암호화 결과");
				result.put("rstUrl", SecureUtils.encSeed(seed, url));
				result.put("rstUid", SecureUtils.encSeed(seed, uid));
				result.put("rstPwd", SecureUtils.encSeed(seed, pwd));
			
			}else if ("decode".equals(flag)) {
				result.put("msg", "복호화 결과");
				if (!"".equals(url) && url.length() > 4) result.put("rstUrl", SecureUtils.decSeed(seed, url));
				if (!"".equals(uid) && uid.length() > 4) result.put("rstUid", SecureUtils.decSeed(seed, uid));
				if (!"".equals(pwd) && pwd.length() > 4) result.put("rstPwd", SecureUtils.decSeed(seed, pwd));
			}
		}catch(Exception e) {
			result.put("msg", "암/복호화 오류 발생");
			result.put("error", e.toString());
		}
		
		model.addObject("result", result);
		return model;
    }
	
	@RequestMapping("/test.do")
	public Map<String,Object> result(@RequestParam Map<String,Object> params) throws Exception {
	//public Map<String,Object> result(HttpServletRequest request, HttpServletResponse reponse, Model model) throws Exception {
		Map<String,Object> resultMap = new HashMap<String,Object>();
	    
		
	    return resultMap;
    }
}
