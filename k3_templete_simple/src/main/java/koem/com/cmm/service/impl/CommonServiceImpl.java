package koem.com.cmm.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import koem.com.cmm.service.CommonService;
import koem.com.cmm.service.impl.CommonDAO;
import koem.com.cmm.util.CommonUtil;


@Service("commonService")
public class CommonServiceImpl extends EgovAbstractServiceImpl implements CommonService {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonServiceImpl.class);
	
	@SuppressWarnings("unchecked")
	private Map paramMap;

	@Resource(name="commonDAO")
	private CommonDAO 		commonDAO;
	
	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
	
	
	
	
    @SuppressWarnings("unchecked")
    public void jsonExecNative(JSONArray paramMapVal)throws Exception {
        Map mapjson ; 
        
        for(int i=0;i<paramMapVal.size();i++){
            mapjson = (Map)JSONObject.fromObject(paramMapVal.get(i));
            
            String sql = (String) mapjson.get("sql_id");
            String exec = (String) mapjson.get("exec");
            
            if(exec.equals("insert")){
                commonDAO.insertJsonList(sql, mapjson);
            } else if(exec.equals("update")){
                commonDAO.updateJsonList(sql, mapjson);
            } else if(exec.equals("delete")){
                commonDAO.deleteJsonList(sql, mapjson);
            } else {
                break;
            }
        }
    }
	
	
	
	
	
	
	/*
	 * 공통 모듈 select, insert, update, delete
	 * */
	
	/*
	 *list totalCnt 값 int 필요할때 
	 * */
	@SuppressWarnings("unchecked")
	public int selectListTotCnt(String sqlId, Map map) throws Exception {
		return commonDAO.selectListTotCnt(sqlId, map);
	}
	
	/*
	 *return 값 int 필요할때 
	 * */
	@SuppressWarnings("unchecked")
	public int selectReturnInt(String sqlId, Map map) throws Exception {
		return commonDAO.selectReturnInt(sqlId, map);
	}

	/*
	 *return 값 string 필요할때 
	 * */
	@SuppressWarnings("unchecked")
	public String selectReturnString(String sqlId, Map map) throws Exception {
		return commonDAO.selectReturnString(sqlId, map);
	}
	
	/*
	 *기본적인 목록 조회용 
	 * */
	@SuppressWarnings("unchecked")
	public List selectListCommon(String sqlId, Map map) throws Exception {
		return commonDAO.selectListCommon(sqlId, map);
	}
	/*
	 *기본적인 목록 1건의 상세보기 조회용 
	 * */
	@SuppressWarnings("unchecked")
	public List selectCommon(String sqlId, Map map) throws Exception {
		return commonDAO.selectListCommon(sqlId, map);
	}
	
	/*
	 * 단순 등록시
	 * */
	@SuppressWarnings("unchecked")
	public void insertCommon(String sqlId, Map map) throws Exception {
		commonDAO.insertCommon(sqlId, map);
	}
	
	/*
	 * 등록시 리턴 값이 필요 한 경우
	 * 대신 ibatis에서 제공하는 selectkey 를 통하여 insert쿼리를 날려야 함	시퀀스 활용 혹은 내부 쿼리를 이용하여 처리
	 * 예)
	 * 	<insert id="board.insertBoard" parameterClass="paramMap">
			<selectKey resultClass="string" keyProperty="board_id" >
				select board_id.NEXTVAL as board_id from dual
			</selectKey>
		
			insert into table명( 컬럼들 )
				values(	변수들 )
		</insert>		
	 * */
	@SuppressWarnings("unchecked")
	public String insertCommonReturnVal(String sqlId, Map map) throws Exception {
		return (String)commonDAO.insertCommonReturnVal(sqlId, map);
	}
	
	/*
	 *기본적인 삭제시 
	 * */
	@SuppressWarnings("unchecked")
	public void deleteCommon(String sqlId, Map map) throws Exception {
		commonDAO.deleteCommon(sqlId, map);
	}
	/*
	 *기본적인 수정시 
	 * */
	@SuppressWarnings("unchecked")
	public void updateCommon(String sqlId, Map map) throws Exception {
		commonDAO.updateCommon(sqlId, map);
	}
	
	

	/*
	 *insert를 여러건 처리시 //query로도 처리 가능 
	 * */
	@SuppressWarnings("unchecked")
	public void insertListCommon(String sqlId, Map map) throws Exception {
		
		List saveList = CommonUtil.mapToArrayList(map); //  공통함수로 map을 던져서 ListArray를 구함
		Map sendMap;
		for(int j=0; j<saveList.size(); j++){ // 리스트 배열 수 만큼 반복문 돌림.
			sendMap = (Map) saveList.get(j); // list 배열에 담긴 데이터를 순차적으로 꺼내서 map에 복사.
			
			commonDAO.insertCommon(sqlId, sendMap);
			
			if(j<saveList.size()){}else{break;}
		}
	}
	@SuppressWarnings("unchecked")
	public void deleteListCommon(String sqlId, Map map) throws Exception {
		List saveList = CommonUtil.mapToArrayList(map); //  공통함수로 map을 던져서 ListArray를 구함
		Map sendMap;
		for(int j=0; j<saveList.size(); j++){ // 리스트 배열 수 만큼 반복문 돌림.
			sendMap = (Map) saveList.get(j); // list 배열에 담긴 데이터를 순차적으로 꺼내서 map에 복사.
			
			commonDAO.deleteCommon(sqlId, sendMap);
			
			if(j<saveList.size()){}else{ break;}
		}		
	}
	
	@SuppressWarnings("unchecked")
	public void updateListCommon(String sqlId, Map map) throws Exception {
		List saveList = CommonUtil.mapToArrayList(map); //  공통함수로 map을 던져서 ListArray를 구함
		Map sendMap;
		for(int j=0; j<saveList.size(); j++){ // 리스트 배열 수 만큼 반복문 돌림.
			sendMap = (Map) saveList.get(j); // list 배열에 담긴 데이터를 순차적으로 꺼내서 map에 복사.
			
			commonDAO.updateCommon(sqlId, map);
			
			if(j<saveList.size()){}else{break;}
		}			
	}
	
	
	
	//기타 예제
	/*	특성에 맞게 커스터마이징 필요한 부분 */
	//Map의 일부 값이 단일 파라마터인경우 별도 처리 필요	//공통 처리 불가 각 서비스단에 처리 필요
	@SuppressWarnings("unchecked")
	public void execCommonAct(Map map) throws Exception {
		String formid = (String) map.get("formid");  // map에서 폼id 값을 변수로 복사
		map.remove("formid");						// 단일 파라미터 이므로 map에서 제거함.
		
		List saveList = CommonUtil.mapToArrayList(map); //  공통함수로 map을 던져서 ListArray를 구함
		
		for(int j=0; j<saveList.size(); j++){ // 리스트 배열 수 만큼 반복문 돌림.
			Map sendMap = (Map) saveList.get(j); // list 배열에 담긴 데이터를 순차적으로 꺼내서 map에 복사.
			
			if(sendMap.get("pk_id").equals("")){ // pkid 값이 없으면 DB에 없는 데이터 이므로 insert로 간주하고 insert 쿼리 호출
				commonDAO.insertCommon("pc.insertWriteFormPC."+formid, sendMap);
			}else {
				commonDAO.updateCommon("pc.updateWriteFormPC."+formid, sendMap);
			}
			
			if(j<saveList.size()){}else{break;}
		}
	}

	/*	특성에 맞게 커스터마이징 필요한 부분 */
	//Map의 모든 값이 통일되게 arrayList형태로 처리된 경우
	@SuppressWarnings("unchecked")
	public void execCommon(Map map) throws Exception {
		List saveList = CommonUtil.mapToArrayList(map); //  공통함수로 map을 던져서 ListArray를 구함
		
		for(int j=0; j<saveList.size(); j++){ // 리스트 배열 수 만큼 반복문 돌림.
			Map sendMap = (Map) saveList.get(j); // list 배열에 담긴 데이터를 순차적으로 꺼내서 map에 복사.
			
			if(sendMap.get("exec").equals("insert")){		//form에서 넘어온 리스트의 exec변수에 맞게 처리
				commonDAO.insertCommon((String)sendMap.get("sql_id"), sendMap);
			}else if(sendMap.get("exec").equals("update")){
				commonDAO.updateCommon((String)sendMap.get("sql_id"), sendMap);
			}else if(sendMap.get("exec").equals("delete")){
				commonDAO.deleteCommon((String)sendMap.get("sql_id"), sendMap);
			}
			if(j<saveList.size()){}else{break;}
		}
	}
	
	
	
	/**
	 * 프로시져
	 */
	public void getProcedure(String sqlId, Map map)throws Exception{
		commonDAO.getProcedure(sqlId, map);
		
	}
	
	
	
}