package koem.com.cmm.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import koem.com.cmm.service.CommonService;
import koem.com.cmm.service.impl.CommonServiceImpl;


public class CommonUtil {
	

	public static Logger logger = Logger.getRootLogger();
	
    @SuppressWarnings("unchecked")
    public static JSONArray paramList(List beList) throws Exception {
        JSONArray afList = new JSONArray();
        for (int i=0; i<beList.size(); i++) {
            Map dataMap = (Map)beList.get(i);
            Object[] keys =  dataMap.keySet().toArray();
            for (int j=0; j<keys.length; j++) {
                String key = keys[j].toString();
                
                if(!key.equals("file")) { //파일데이터는 암호화 안함.
                    String value = nullChk( (String)dataMap.get(keys[j].toString()) ) ;
                    dataMap.put(key, value);
                }
                if(j<keys.length){}else{break;}
            }
            System.out.println("dataMap :: " +dataMap);
            afList.add( JSONObject.fromObject(dataMap) );
            if(i<beList.size()){}else{break;}
        }
        return afList;
    }
    
	
	/**
	 * request값들을 map으로 담아줌 HashMap(String key, Vector values)  
	 **/
		@SuppressWarnings("unchecked")
		public static HashMap RequestParamMap(HttpServletRequest request) throws Exception {

		  HashMap map = new HashMap();
//		  request.setCharacterEncoding("UTF-8");
		  Enumeration Enumeration = request.getParameterNames();
		  
		  //int k=0;
			try {		  
				  while (Enumeration.hasMoreElements()) {
					  String key = (String) Enumeration.nextElement();
				      String[] value = request.getParameterValues(key);
				      if (value.length == 1) {
				          map.put(key, nullChk(request.getParameter(key)));
				          //request.removeAttribute(key);
				          logger.debug(key+" = "+nullChk(request.getParameter(key)));
				      } else if (value.length > 1) {
				          Vector vector = new Vector();
				          for (int i = 0; i < value.length; i++) {
				        	  vector.add(vector.size(), nullChk(value[i]));
				        	  //request.removeAttribute(key);
				        	  logger.debug(key+" = "+nullChk(value[i]));
				        	  if(i < value.length){}else{break;}
				          }
				          map.put(key, vector);	          
				      }
				      if(Enumeration.hasMoreElements()){}else{break;}
				      //if(k>1000)break;
				      //k++;
				  }

			} catch (ArrayIndexOutOfBoundsException e) {
				logger.error("RequestParamMap 에러발생 : " +e);			
	        } catch (NumberFormatException e) { 
	        	logger.error("RequestParamMap 에러발생 : " +e);			 
	        } catch (Exception e) { 
	        	logger.error("RequestParamMap 에러발생 : " +e);			
	        }		  
		  	//session 처리 필요시
//	    	HttpSession httpSession = request.getSession();
//	    	map.put("s_user_id"   , httpSession.getAttribute("user_id")    );
//	    	map.put("s_user_name"   , httpSession.getAttribute("user_name")    );
//	    	map.put("s_user_type"   , httpSession.getAttribute("user_type")    );
//	    	System.out.println("paramMap -- "+map);
		  return map;
		}

		/**
		 * request값들을 map으로 담아줌 HashMap(String key, Vector values)  
		 * p_id 를 통하여  sql_id를  map에 담음
		 * @param commonService2 
		 **/
			@SuppressWarnings("unchecked")
			public static HashMap RequestParamMapPidTo(HttpServletRequest request, CommonService commonService) throws Exception {

			  HashMap map = new HashMap();
//			  request.setCharacterEncoding("UTF-8");
			  Enumeration Enumeration = request.getParameterNames();
			  
			  //int k=0;
  
					  while (Enumeration.hasMoreElements()) {
						  String key = (String) Enumeration.nextElement();
					      String[] value = request.getParameterValues(key);
					      if (value.length == 1) {
					          map.put(key, nullChk(request.getParameter(key)));
					          //request.removeAttribute(key);
					          logger.debug(key+" = "+nullChk(request.getParameter(key)));
					      } else if (value.length > 1) {
					          Vector vector = new Vector();
					          for (int i = 0; i < value.length; i++) {
					        	  vector.add(vector.size(), nullChk(value[i]));
					        	  //request.removeAttribute(key);
					        	  logger.debug(key+" = "+nullChk(value[i]));
					        	  if(i < value.length){}else{break;}
					          }
					          map.put(key, vector);	          
					      }
					      if(Enumeration.hasMoreElements()){}else{break;}
					      //if(k>1000)break;
					      //k++;
					  }
					  //p_id 를 통하여  sql_id 제어
					  if(map.get("p_id")!=null && map.get("sql_id")==null){
						  map.put("sql_id", (String)commonService.selectReturnString("common.pidToSqlIdMapper", map))  ;
					  }
					  System.out.println(map.toString());
 			  return map;
			}
		
		/** 
		 * request를 담은 Map에서 특정 값에 대한 리스트를 받고자 할경우
		 * */
		@SuppressWarnings("static-access")
		public static ArrayList getRequestValuesToArray(Object param) {
			
			ArrayList alResult = new ArrayList();
			String strValue = "";
			
			if(param == null) {
				return null;
			}
			
			strValue = param.toString().replace("[", "");
			strValue = strValue.toString().replace("]", "");
			
			if(strValue.indexOf(",") > -1)
			{
				String[] arrValue = strValue.split(",");
				
				for(int i=0; i < arrValue.length; i++)
				{
					if(arrValue[i].toString().equals(""))
					{
						alResult.add("");
					}
					else
					{
						alResult.add(arrValue[i].trim());
					}
					if(i < arrValue.length){}else{break;}
				}
			}
			else
			{
				alResult.add(strValue);
			}
			
			if(StringUtil.right(strValue.trim(), 1).equals(","))
			{
				alResult.add("");
			}
			
			return alResult;
		}
		

		/**
		 * request Parameter를 Map으로 변경된 데이타를 다시 ArrayList로 변환 return
		 * */
		@SuppressWarnings("unchecked")
		public static ArrayList mapToArrayList(Map map) throws Exception {
			List mapList;
			List saveList = new ArrayList();
			String memid = (String) map.get("mem_id");
			int tblRow = Integer.parseInt((String) map.get("tbl_row")); // map에서 테이블 row 값을 변수로 복사
			map.remove("tbl_row");
			
			
			System.out.println(map);
			if(tblRow==1){ // 입력된 데이터가 1개일때
				saveList.add(map); // 직접 array리스트에 map을 담음.
			}else{
				map.remove("mem_id");
				for (int i = 0; i < tblRow; i++) { // 테이블 row 값 만큼 반복함.
					Map tmpMap = new HashMap(); // 스왑할 map 생성
					Set<String> set = map.keySet(); // map의 키셋을 가져와 set에 셋팅
					Iterator<String> iter = set.iterator(); // set을 iterator로 선언
					//int k=0;
					//int setSize=set.size();
					while(iter.hasNext()){ // set을 iterator로 반복함.
						String key = iter.next(); // iter.next로 set에 담긴 key 값을 변수에 한번씩 저장
						mapList = (List) map.get(key); // 해당하는 key 값의 value를 list에 저장
						tmpMap.put(key, mapList.get(i)); // 스왑할 map에 해당 파라미터의 key값과 일치하는 데이터를 순서대로 꺼내서 담음.
						if(iter.hasNext()){}else{break;}
						//if(k > setSize){break;}
						//iter.
						//k++;
					}
					tmpMap.put("mem_id", memid);
					saveList.add( tmpMap ); // map에 담긴 데이터를 arrayList에 담음.
					
					if(i < tblRow){}else{break;}
				}
			}
			return (ArrayList) saveList;
			
		}

		
		/**
		 * Redirect시 파라미터 셋팅
		 * */
		public static String redirectParam(Map paramMap, boolean prefix) throws Exception {

		    String param = ""; 
		    Object[] key =  paramMap.keySet().toArray();
		    for (int i = 0; i < key.length; i++) {
		    	if(prefix && i==0){
		    		param += "?";
		    	}else{
		    		param += "&";
		    	}
		    	param += key[i] + "=" + paramMap.get(key[i]);
		    	if(i < key.length){}else{break;}
			}
		    return param;
		}
		
		@SuppressWarnings("static-access")
		public static String nullChk(String str) throws Exception {
			boolean su = new StringUtils().isEmpty(str);
			String result;
			
			if(su) {
				result = ""; 
			} else { 
	        	result = str; 
			}
			
	        return result; 
		}
		
		@SuppressWarnings("static-access")
		public static Object nullChk(Object obj) throws Exception {
			Object objRst = null;
			if(obj==null) {
				objRst = ""; 
			} else {
				objRst = obj;
			}
	        return objRst; 
		}
		
		@SuppressWarnings("static-access")
		public static String encode( String s){
			StringBuffer uni_s = new StringBuffer();
			String temp_s = null;
			for( int i=0 ; i < s.length() ; i++){
				temp_s = Integer.toHexString( s.charAt(i) );
				uni_s.append( "\\u"+(temp_s.length()==4 ? temp_s : "00" + temp_s ) );
				if(i < s.length()){}else{break;}
			}
			return uni_s.toString();
		}
		
		@SuppressWarnings("static-access")
		public static String decode( String uni){
			StringBuffer str = new StringBuffer();
			for( int i= uni.indexOf("\\u") ; i > -1 ; i = uni.indexOf("\\u") ){
				str.append( uni.substring( 0, i ) );
				str.append( String.valueOf( (char)Integer.parseInt( uni.substring( i + 2, i + 6 ) ,16) ) );
				uni = uni.substring( i +6);
				if(i > -1){}else{break;}
			}
			str.append( uni );
			return str.toString();
		}
		
		@SuppressWarnings("static-access")
		public static String getCookdata(String cookname, HttpServletRequest req) {
			String sretVal = "";
			Cookie[] cookies = req.getCookies();

			if (cookies != null) {
				for (int icnt = 0; icnt < cookies.length; icnt++) {
					cookies[icnt].setPath("/");
					if (cookname.equals(cookies[icnt].getName())) {
						sretVal = cookies[icnt].getValue().trim();
						break;
					}
				}
			}
			return sretVal;
		}
		

		public static String comma(String s)throws Exception{
			if (s == null || "".equals(s) ) s="0";
		 
			DecimalFormat df =  null;
			if(s.indexOf(".")>= 1) {
				Double doubleObj = new Double(s);
				df = new DecimalFormat("#,##0.0000");
				return df.format(doubleObj);
			} else {
				Long longObj = new Long(s);
				df = new DecimalFormat("#,##0");
				return df.format(longObj);
			}

		}
		
		
		
		// 파라메터를 데이터베이스에 입력시 한글변환 
		public static Object toDatabase(Object obj) throws UnsupportedEncodingException{
			Object returnObj = null;
			if(obj instanceof String){
				returnObj = new String((obj.toString()).getBytes("KSC5601"),"8859_1");
			}else if(obj instanceof JSON){
				returnObj = JSONObject.fromObject(new String((obj.toString()).getBytes("KSC5601"),"8859_1"));
			}else if(obj instanceof JSONObject){
				returnObj = JSONObject.fromObject(new String((obj.toString()).getBytes("KSC5601"),"8859_1"));
			}else if(obj instanceof JSONArray){
				returnObj = JSONArray.fromObject(new String((obj.toString()).getBytes("KSC5601"),"8859_1"));
			}else{
				returnObj = obj;
			}
			return returnObj;
		}
		
		// 데이터베이스를 화면에 출력시 한글변환 
		public static Object toScreen(Object obj) throws UnsupportedEncodingException{
			Object returnObj = null;
			if(obj instanceof String){
				returnObj = new String((obj.toString()).getBytes("8859_1"),"KSC5601");
			}else if(obj instanceof JSON){
				returnObj = JSONObject.fromObject(new String((obj.toString()).getBytes("8859_1"),"KSC5601"));
			}else if(obj instanceof JSONObject){
				returnObj = JSONObject.fromObject(new String((obj.toString()).getBytes("8859_1"),"KSC5601"));
			}else if(obj instanceof JSONArray){
				returnObj = JSONArray.fromObject(new String((obj.toString()).getBytes("8859_1"),"KSC5601"));
			}else{
				returnObj = obj;
			}
			return returnObj;
		}		
}
