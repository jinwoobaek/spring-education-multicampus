/**
 * It's Designed For incubation Center
 * @author ohsanghun
 * @version     %I%, %G%
 * @since       1.0
 */
package com.clustering.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.clustering.project.service.MemberService;

@Controller
public class MemberController {
	private final static String MAPPING = "/member";

	@Autowired
	private MemberService service;

	@RequestMapping(value = MAPPING, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView actionMethod(@RequestParam Map<String, Object> paramMap, ModelAndView modelandView) {

		String viewName = MAPPING + "/";
		String action = (String) paramMap.get("action");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Object> resultList = new ArrayList<Object>();

		// divided depending on action value
		if ("edit".equalsIgnoreCase(action)) {
			viewName = viewName + action;
			if (service.isNew(paramMap)) {
				resultMap = (Map<String, Object>) service.getUuid(paramMap);
			} else {
				resultMap = (Map<String, Object>) service.getMap(paramMap);
			}
		} else if ("read".equalsIgnoreCase(action)) {
			viewName = viewName + action;
			resultMap = (Map<String, Object>) service.getMap(paramMap);

		} else if ("list".equalsIgnoreCase(action)) {
			viewName = viewName + action;
			resultList = (List<Object>) service.getList(paramMap);
		} else if ("insert".equalsIgnoreCase(action)) {
			viewName = viewName + "list";
			int count = (int) service.saveObject(paramMap);
			resultList = (List<Object>) service.getList(paramMap);
		} else if ("delete".equalsIgnoreCase(action)) {
			viewName = viewName + "list";
			int count = (int) service.deleteObject(paramMap);
			resultList = (List<Object>) service.getList(paramMap);
		} else {
			viewName = viewName + "list";
		}

		modelandView.setViewName(viewName);

		modelandView.addObject("resultMap", resultMap);
		modelandView.addObject("resultList", resultList);

		return modelandView;
	}
}