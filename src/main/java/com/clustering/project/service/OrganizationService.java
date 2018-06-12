package com.clustering.project.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clustering.project.dao.OrganizationDao;
import com.clustering.project.util.CommonUtil;

/**
 * Handles requests for the application home page.
 */
@Service
public class OrganizationService {

	@Autowired
	private OrganizationDao dao;

	@Autowired
	private CommonUtil uuid;

	public Object getList(Object dataMap) {

		Object resultObject = dao.getList("organization.list", dataMap);
		return resultObject;

	}

	public Object getMap(Object dataMap) {

		Object resultObject = dao.getMap("organization.read", dataMap);
		return resultObject;

	}

	public Object getUuid(Object dataMap) {

		((Map) dataMap).put("ORGANIZATION_SEQ", uuid.getUniqueSequence());
		((Map) dataMap).put("IS_NEW", "new!");
		return dataMap;
	}

	public Object saveObject(Object dataMap) {

		Object resultObject;
		String isNew = (String) ((Map) dataMap).get("IS_NEW");

		if ("new!".equals(isNew)) {
			resultObject = dao.saveObject("organization.insert", dataMap);
		} else {
			resultObject = dao.updateObject("organization.update", dataMap);
		}

		return resultObject;

	}

	public Object deleteObject(Object dataMap) {

		Object resultObject = dao.deleteObject("organization.delete", dataMap);
		return resultObject;

	}

	// public Object updateObject(Object dataMap) {
	//
	// Object resultObject = dao.updateObject("organization.update", dataMap);
	// return resultObject;
	//
	// }

	public boolean isNew(Object dataMap) {
		if (((Map) dataMap).get("ORGANIZATION_SEQ") == null)
			return true;
		else
			return false;
	}
}