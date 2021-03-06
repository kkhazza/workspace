package com.java.web.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GoodeeDao {
	
	@Resource(name="sqlSession")
	SqlSession session1;
	
	@Autowired
	SqlSession session2;
	
	public List<HashMap<String, Object>> getTestList(){
		return session2.selectList("test.test");
	}
	
	public HashMap<String, Object> getTestOne(){
		return session2.selectOne("test.test");
	}

}
