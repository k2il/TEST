package koem.com.cmm.web;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
 



import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import koem.com.cmm.util.CommonOpenAPIUtil;
import koem.com.cmm.service.CommonService;
import koem.com.cmm.util.CommonUtil;
import koem.com.cmm.util.PageUtil;

@Controller
public class CommonController {
	Logger LOGGER = Logger.getLogger(getClass());
	
	@Autowired
	@Resource(name = "commonService")
	private CommonService commonService;
	
	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	
	
	@RequestMapping("/common/jqGridList.do")
	public String jqGridList(ModelMap model, HttpServletRequest request) throws Exception{
		
		Map paramMap = CommonUtil.RequestParamMapPidTo(request, commonService);

		//페이징 처리 시작
		int records = commonService.selectListTotCnt((String)paramMap.get("sql_id")+"_cnt",paramMap);
		PageUtil.getJqGridJsonValPage(records, paramMap, model);
    	//페이징 끝
    	
		//jqGrid 행
		model.put("rows", commonService.selectListCommon((String)paramMap.get("sql_id"), paramMap));
		
		return "jsonView";
	}
	
	
	
	
	@RequestMapping(value="/getOpenAPIJsonView.do")
	public String jsonViewOpenAPI(ModelMap model, HttpServletRequest request, HttpServletResponse res) throws Exception
	{
		Map paramMap = CommonUtil.RequestParamMap(request);

		//model에 여러 데이타 받을때
		CommonOpenAPIUtil.getopenApi(paramMap, model);
/*		//목록만 바로 받을때
		List resultList = CommonOpenAPIUtil.openApiToList(paramMap);
		model.addAttribute("data2", resultList);*/
		
//		paramMap.get(key)
		
//		return "jsonpView";	
		return "jsonView";
	}
		

}

