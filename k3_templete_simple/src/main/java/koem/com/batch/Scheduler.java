package koem.com.batch;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import koem.com.batch.service.HaguVO;
import koem.com.batch.service.YeonanVO;
import koem.com.cmm.service.CommonService;

import java.io.InputStreamReader;
import java.net.HttpURLConnection; 
import java.net.URL;
// import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Scheduler {
	
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
	 * 금강하구 : (오전01:00)
	 * @throws Exception
	 */
//	@Scheduled(cron = "0 15 16 * * ?")
//	public void cronHagu() throws Exception{
//		LOGGER.debug("금강하구 시작 [" + 0 + "]");
//		haguInsert();
//	}
	
	
	
	/**
	 * 전주포연안 : (매월 1일 오전01:30)
	 * @throws Exception
	 */
	@Scheduled(cron = "0 21 10 * * ?")
	public void cronYeonan() throws Exception{
		
		for(int i=1; i<9; i++){
			LOGGER.debug("전주포연안 [" + i + "] 시작 ");
			yeonanInsert(i);
		}
	}
	
	/**
	 * 어제 날짜 가져오기
	 * @return 'YYYY-MM-DD'
	 * @throws Exception
	 */
	public String yesterDayDate() throws Exception{
		
		Calendar cal = new GregorianCalendar();
	    cal.add(Calendar.DATE, -1);
		
	    String mm = (String) ((cal.get(Calendar.MONTH) + 1) < 10 ? "0" + (cal.get(Calendar.MONTH) + 1) : (cal.get(Calendar.MONTH) + 1));
	    String dd = (String) (cal.get(Calendar.DAY_OF_MONTH) < 10 ? "0" + cal.get(Calendar.DAY_OF_MONTH) : cal.get(Calendar.DAY_OF_MONTH));
	    
	    String yesterDay = cal.get(Calendar.YEAR) + "-" + mm + "-" + dd;
	    LOGGER.debug("어제날짜:[" + yesterDay + "]");
	    
	    
		return yesterDay;
	}
	
	
	/**
	 * 금강하구 insert
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void haguInsert() throws Exception{
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		String id = "WemoData";
//		String sdate = yesterDayDate();
		String sdate = "2014-05-01";
		String edate = sdate;
		String sta_cde = "107";
		
		List<HaguVO> haguList = new ArrayList<HaguVO>();
		
		try {
			haguList = new ArrayList<HaguVO>();
		    URL req = new URL("http://openapi.meis.go.kr/rest/datalist?id=" + id + "&sdate=" + sdate + "&edate=" + edate + "&sta_cde=" + sta_cde +  "");
		    HttpURLConnection con = (HttpURLConnection)req.openConnection();
		    Object obj = JSONValue.parse(new InputStreamReader(con.getInputStream()));
		       
		    JSONObject jObj = (JSONObject) obj;
		    String status = (String) jObj.get("status");
		    if(status ==  "fail" || status.equals("fail")){
		    	LOGGER.debug(sdate +" 날짜의 " + sta_cde + " 지역 자료가 없습니다.");
		   	 	return; 
		    }
		    
		    haguList = (List<HaguVO>) jObj.get("data");
		    LOGGER.debug("haguList size:["+haguList.size()+"] 건");
		    
		    paramMap.put("haguList", haguList);
		    commonService.insertCommon("hagu.insertHagu", paramMap);
		       
		} catch (Exception e) {
		 e.printStackTrace();
		}
		
	}
	
	/**
	 * 전주포 연안 1~8 insert DEFAULT
	 * @param request
	 * @param i
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void yeonanInsertDEFAULT(int i) throws Exception{
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		String id = "NomoCoast";
//		String sdate = yesterDayDate();
		String sdate = "2014-05-01";
		String edate = sdate;
		String sta_cde = "03070"+i;
		
		List<YeonanVO> yeonanList = new ArrayList<YeonanVO>();
		
		try {
			yeonanList = new ArrayList<YeonanVO>();
			paramMap = new HashMap<String,Object>();
		    URL req = new URL("http://openapi.meis.go.kr/rest/datalist?id=" + id + "&sdate=" + sdate + "&edate=" + edate + "&sta_cde=" + sta_cde +  "");
		    HttpURLConnection con = (HttpURLConnection)req.openConnection();
		    Object obj = JSONValue.parse(new InputStreamReader(con.getInputStream()));
		    
		    JSONObject jObj = (JSONObject) obj;
		    
		    String status = (String) jObj.get("status");
		    if(status ==  "fail" || status.equals("fail")){
		    	LOGGER.debug(sdate +" 날짜의 " + sta_cde + " 지역 자료가 없습니다.");
		    	return; 
		    }
		    
		    yeonanList = (List<YeonanVO>) jObj.get("data");
		    LOGGER.debug("yeonanList size:["+yeonanList.size()+"] 건");
		    
		    paramMap.put("yeonanList", yeonanList);
		    commonService.insertCommon("yeonan.insertYeonanDEFAULT", paramMap);
		       
		} catch (Exception e) {
		 e.printStackTrace();
		}
		
		
	}
	
	
	
	/**
	 * 전주포 연안 1~8 insert DEFAULT
	 * @param request
	 * @param i
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void yeonanInsert(int i) throws Exception{
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		String id = "NomoCoast";
//		String sdate = yesterDayDate();
		String sdate = "2014-05-01";
		String edate = sdate;
		String sta_cde = "03070"+i;
		
		List<YeonanVO> yeonanList = new ArrayList<YeonanVO>();
		
		try {
			yeonanList = new ArrayList<YeonanVO>();
			paramMap = new HashMap<String,Object>();
		    URL req = new URL("http://openapi.meis.go.kr/rest/datalist?id=" + id + "&sdate=" + sdate + "&edate=" + edate + "&sta_cde=" + sta_cde +  "");
		    HttpURLConnection con = (HttpURLConnection)req.openConnection();
		    Object obj = JSONValue.parse(new InputStreamReader(con.getInputStream()));
		    
		    JSONObject jObj = (JSONObject) obj;
		    
		    String status = (String) jObj.get("status");
		    if(status ==  "fail" || status.equals("fail")){
		    	LOGGER.debug(sdate +" 날짜의 " + sta_cde + " 지역 자료가 없습니다.");
		    	return; 
		    }
		    
		    yeonanList = (List<YeonanVO>) jObj.get("data");
		    LOGGER.debug("yeonanList size:["+yeonanList.size()+"] 건");
		    
		    paramMap.put("yeonanList", yeonanList);
		    commonService.insertCommon("yeonan.insertYeonan", paramMap);
		       
		} catch (Exception e) {
		 e.printStackTrace();
		}
		
		
	}
	
}
