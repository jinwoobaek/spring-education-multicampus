package com.clustering.project.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Handles requests for the application home page.
 */
@Repository
public class MemberDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	public Object getList(String sqlMapId, Object dataMap) {

		Object result = sqlSession.selectList(sqlMapId, dataMap);
		return result;
	}

	public Object getMap(String sqlMapId, Object dataMap) {

		Object result = sqlSession.selectOne(sqlMapId, dataMap);
		return result;
	}

	public Object saveObject(String sqlMapId, Object dataMap) {

		Integer result = sqlSession.insert((String) sqlMapId, dataMap);
		return result;
	}

	public Object deleteObject(String sqlMapId, Object dataMap) {
		Integer result = sqlSession.delete(sqlMapId, dataMap);
		return result;
	}

	public Object updateObject(String sqlMapId, Object dataMap) {

		Integer result = sqlSession.update(sqlMapId, dataMap);
		return result;
	}

}
