package com.clustering.project.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clustering.project.dao.MemberDao;
import com.clustering.project.util.CommonDate;
import com.clustering.project.util.CommonUtil;

/**
 * Handles requests for the application home page.
 */
@Service
public class MemberService {

	@Autowired
	private MemberDao dao;

	@Autowired
	private CommonUtil uuid;

	@Autowired
	private CommonDate date;

	public Object getList(Object dataMap) {

		Object resultObject = dao.getList("member.list", dataMap);
		return resultObject;

	}

	public Object getMap(Object dataMap) {

		Object resultObject = dao.getMap("member.read", dataMap);
		return resultObject;

	}

	public Object getUuid(Object dataMap) {

		((Map) dataMap).put("MEMBER_SEQ", uuid.getUniqueSequence());
		((Map) dataMap).put("IS_NEW", "new!");
		return dataMap;
	}

	// public Object getDate(Object dataMap) {
	//
	// ((Map) dataMap).put("REGISTRY_DATE", date.getDate());
	// ((Map) dataMap).put("MODIFY_DATE", date.getDate());
	// ((Map) dataMap).put("IS_NEW", "new!");
	//
	// return dataMap;
	// }

	public Object saveObject(Object dataMap) {

		Object resultObject;
		String isNew = (String) ((Map) dataMap).get("IS_NEW");

		if ("new!".equals(isNew)) {
			((Map) dataMap).put("REGISTER_SEQ", "UUID-1111-1111111");
			((Map) dataMap).put("MODIFIER_SEQ", "UUID-1111-1111111");
			((Map) dataMap).put("ORGANIZATION_SEQ", "UUID-11-CIPI9M");
			resultObject = dao.saveObject("member.insert", dataMap);
		} else {
			resultObject = dao.updateObject("member.update", dataMap);
		}

		return resultObject;

	}

	public Object deleteObject(Object dataMap) {

		Object resultObject = dao.deleteObject("member.delete", dataMap);
		return resultObject;

	}

	// public Object updateObject(Object dataMap) {
	//
	// Object resultObject = dao.updateObject("organization.update", dataMap);
	// return resultObject;
	//
	// }

	public boolean isNew(Object dataMap) {
		if (((Map) dataMap).get("MEMBER_SEQ") == null)
			return true;
		else
			return false;
	}
}