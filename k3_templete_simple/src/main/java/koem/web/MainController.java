package koem.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import koem.com.cmm.service.CommonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	private static final Logger LOGGER = LoggerFactory.getLogger("");
	
	// --------------------------- commonService ----------------------------------//

	/*@Autowired
	@Resource(name = "commonService")
	private CommonService commonService;
	
	public CommonService getCommonService(){
		return commonService;
	}
	
	public void setCommonService(CommonService commonService){
		this.commonService = commonService;
	}
	
	// --------------------------------------------------- //
	*/
	
	
	
	@RequestMapping("/helloWorld.do")
	public String helloWorld(HttpServletRequest request, ModelMap model) throws Exception{
		
		
		
		return "main/helloWorld";
	}
	
}
