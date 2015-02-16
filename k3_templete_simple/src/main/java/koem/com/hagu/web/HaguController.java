package koem.com.hagu.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import koem.com.batch.service.HaguVO;
import koem.com.cmm.service.CommonService;
import koem.com.cmm.util.CommonUtil;

import java.io.InputStreamReader;
import java.net.HttpURLConnection; 
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HaguController {
	
	//-----------------------	commonService	-------------------------//
	@Autowired
	@Resource(name = "commonService")
	private CommonService commonService;
	
	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	// ---------------------------------------------------------------- //
	
	private static final Logger LOGGER = LoggerFactory.getLogger("");

	/**
	 * 금강하구 조회
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/koem/HAGU/list.do")
	public String selectList(HttpServletRequest request, ModelMap model) throws Exception{
		
		LOGGER.debug("하구@@@@@");
		
		return "koem/HAGU/list";
	}
	
	
	/**
	 * jqGrid View
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/koem/HAGU/JsonViewList")
	public String JsonViewList(HttpServletRequest request, ModelMap model) throws Exception{
		
		Map paramMap = CommonUtil.RequestParamMap(request);
		
		String id = "WemoData";
		String sdate = "2014-02-03";
		String edate = "2014-02-03";
		String sta_cde = "107";
		
		List<HaguVO> haguList = new ArrayList<HaguVO>();
		HaguVO haguVO = new HaguVO();
		
		try {
		     URL req = new URL("http://openapi.meis.go.kr/rest/datalist?id=" + id + "&sdate=" + sdate + "&edate=" + edate + "&sta_cde=" + sta_cde +  "");
		     HttpURLConnection con = (HttpURLConnection)req.openConnection();
		     
		     Object obj = JSONValue.parse(new InputStreamReader(con.getInputStream()));
		     
		     JSONObject jObj = (JSONObject) obj;
		     
		     LOGGER.debug("STATUS:[" + (String) jObj.get("status") + "]");
		     String status = (String) jObj.get("status");
			    
		     if(status ==  "fail" || status.equals("fail")){
		    	 LOGGER.debug("값 없습니다");
		    	 return "error/error";
		     }
		     
		     
		     model.put("rows", jObj.get("data"));
		     haguList = (List<HaguVO>) jObj.get("data");
		     paramMap.put("haguList", haguList);
		     
			 commonService.insertCommon("hagu.insertHagu", paramMap);
		       
		} catch (Exception e) {
		 e.printStackTrace();
		}
		
		
		
		return "jsonView";
	}
	
}
