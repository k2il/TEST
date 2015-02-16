package koem.com.yeonan.web;

import javax.annotation.Resource;

import koem.com.cmm.service.CommonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class YeonanController {

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
	
	
	
	@RequestMapping("/hi.do")
	public String helloWorld() throws Exception{
		
		LOGGER.debug("연한 ㄱㄱ");
		
		return "koem/YEONAN/list";
	}
	
}
