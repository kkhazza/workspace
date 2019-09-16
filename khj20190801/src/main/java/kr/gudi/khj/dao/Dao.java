package kr.gudi.khj.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Dao implements DaoInterface {
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public HashMap<String,Object> getData() {
		return sqlSession.selectOne("sql.getData");
	}
	
	//@Override
	//public int delData(HashMap<String, Object> param) {
	//	return sqlSession.delete("sql.delete", param);
	//}

	@Override
	public int setData(HashMap<String, Object> paramList) {
		return sqlSession.insert("sql.insert", paramList);
	}
	
	//@Override
	//public void clear() {
	//	sqlSession.update("sql.truncate");
	//}
}
