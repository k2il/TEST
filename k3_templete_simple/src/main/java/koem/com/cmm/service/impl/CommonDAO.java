package koem.com.cmm.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("commonDAO")
public class CommonDAO extends EgovAbstractDAO {
	private SqlMapClient sc;
	
	@Override
	protected void initDao() throws Exception {
		sc = getSqlMapClient();
	}

	/*
	 * 공통 모듈 select, insert, update, delete
	 * */
	//기본적으로 리스트 전체 카운트시
	@SuppressWarnings("unchecked")
	public int selectListTotCnt(String sqlId, Map map) throws Exception {
		return (Integer)sc.queryForObject(sqlId, map);
	}
	
	//기본적으로 return 값 int
	@SuppressWarnings("unchecked")
	public int selectReturnInt(String sqlId, Map map) throws Exception {
		return (Integer)sc.queryForObject(sqlId, map);
	}	
	
	//기본적으로 return 값 string
	@SuppressWarnings("unchecked")
	public String selectReturnString(String sqlId, Map map) throws Exception {
		return (String)sc.queryForObject(sqlId, map);
	}	

	
	//목록 조회시
	@SuppressWarnings("unchecked")
	public List selectListCommon(String sqlId, Map map) throws Exception {
		return sc.queryForList(sqlId, map);
	}
	//단순 등록시
	@SuppressWarnings("unchecked")
	public void insertCommon(String sqlId, Map map) throws Exception {
		sc.insert(sqlId, map);
	}
	
	//등록후 리턴값 받고 싶은 경우
	/*
	 * insert시 리턴 값이 필요 한 경우
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
		return (String)sc.insert(sqlId, map);
	}
	//삭제시
	@SuppressWarnings("unchecked")
	public void deleteCommon(String sqlId, Map map) throws Exception {
		sc.delete(sqlId, map);
	}
	//업데이트시
	@SuppressWarnings("unchecked")
	public void updateCommon(String sqlId, Map map) throws Exception {
		sc.update(sqlId, map);
	}
    @SuppressWarnings("unchecked")
    public void insertJsonList(String sql, Map paramMap) {
        insert(sql, paramMap);
    }
    @SuppressWarnings("unchecked")
    public void updateJsonList(String sql, Map paramMap) {
        update(sql, paramMap);
    }
    @SuppressWarnings("unchecked")
    public void deleteJsonList(String sql, Map paramMap) {
        delete(sql, paramMap);
    }
    
    
    /**
	 * 프로시져
	 * @param sql
	 * @param paramMap
	 */
	public void getProcedure(String sqlId, Map map)throws Exception{
		sc.startTransaction();
		sc.queryForObject(sqlId, map);
		sc.getCurrentConnection().commit();
		sc.endTransaction();
	}

	

}