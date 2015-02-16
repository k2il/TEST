package koem.com.cmm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

public interface CommonService {

	int selectListTotCnt(String sqlId, Map map) throws Exception;
	int selectReturnInt(String sqlId, Map map) throws Exception;
	String selectReturnString(String sqlId, Map map) throws Exception;

	List selectCommon(String sqlId, Map map) throws Exception;
	void insertCommon(String sqlId, Map map) throws Exception;
	void deleteCommon(String sqlId, Map map) throws Exception;
	void updateCommon(String sqlId, Map map) throws Exception;

	List selectListCommon(String sqlId, Map map) throws Exception;
	void insertListCommon(String sqlId, Map map) throws Exception;
	void deleteListCommon(String sqlId, Map map) throws Exception;
	void updateListCommon(String sqlId, Map map) throws Exception;	
	
	String insertCommonReturnVal(String sqlId, Map map)throws Exception; 
	
	void execCommon(Map map) throws Exception;
	void execCommonAct(Map map) throws Exception;	
	
	void jsonExecNative(JSONArray jArray)throws Exception;
	
	
	void getProcedure(String sqlId, Map map)throws Exception;

}

