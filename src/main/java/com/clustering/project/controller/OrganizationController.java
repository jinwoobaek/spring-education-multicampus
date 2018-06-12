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
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.clustering.project.javabean.OrganizationBean;
import com.clustering.project.service.OrganizationService;

/**
 * @author ohsanghun get it Mapping classlevel (JavaBean, HttpServletRequest,
 *         Model, View, ModelAndView)
 */

// ? delete @Controller,
// @Controller
@Component
@RequestMapping(value = "/organization")
public class OrganizationController {

	@Autowired
	OrganizationService service;

	// ? add View class
	@RequestMapping(value = "/edit", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView edit(@RequestParam Map<String, Object> paramMap, ModelAndView modelandView) {
		String viewName = "/organization/edit";
		Map<String, Object> resultMap = new HashMap<String, Object>();

		if (service.isNew(paramMap)) {
			resultMap = (Map<String, Object>) service.getUuid(paramMap);
		} else {
			resultMap = (Map<String, Object>) service.getMap(paramMap);
		}

		modelandView.setViewName(viewName);
		modelandView.addObject("paramMap", resultMap);

		return modelandView;
	}

	// ? delete only method = RequestMethod.GET in @RequestMapping
	@RequestMapping(value = "/read", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView read(OrganizationBean paramMap, ModelAndView modelandView) {

		String viewName = "/organization/read";

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = (Map<String, Object>) service.getMap(paramMap);

		modelandView.setViewName(viewName);

		modelandView.addObject("paramMap", resultMap);
		return modelandView;

	}

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView list(@RequestParam Map<String, Object> paramMap, ModelAndView modelandView) {

		String viewName = "/organization/list";

		List<Object> resultList = new ArrayList<Object>();
		resultList = (List<Object>) service.getList(paramMap);

		modelandView.setViewName(viewName);

		modelandView.addObject("resultList", resultList);

		return modelandView;

	}

	@RequestMapping(value = "/insert", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView insert(@RequestParam Map<String, Object> paramMap, ModelAndView modelandView) {

		int count = (int) service.saveObject(paramMap);

		String viewName = "/organization/list";

		List<Object> resultList = new ArrayList<Object>();
		resultList = (List<Object>) service.getList(paramMap);

		modelandView.setViewName(viewName);
		modelandView.addObject("resultList", resultList);

		return modelandView;
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView delete(@RequestParam Map<String, Object> paramMap, ModelAndView modelandView) {

		int count = (int) service.deleteObject(paramMap);

		// String viewName = "/organization/list";
		//
		// List<Object> resultList = new ArrayList<Object>();
		// resultList = (List<Object>) service.getList(paramMap);
		//
		// modelandView.setViewName(viewName);
		// modelandView.addObject("resultList", resultList);

		this.list(paramMap, modelandView);

		return modelandView;
	}

//	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
//	public ModelAndView update(@RequestParam Map<String, Object> paramMap, ModelAndView modelandView) {
//
//		String viewName = "/organization/update";
//
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		resultMap = (Map<String, Object>) service.getMap(paramMap);
//
//		modelandView.setViewName(viewName);
//		modelandView.addObject("paramMap", resultMap);
//
//		return modelandView;
//	}
//
//	@RequestMapping(value = "/updateAfter", method = { RequestMethod.GET, RequestMethod.POST })
//	public ModelAndView updateAfter(@RequestParam Map<String, Object> paramMap, ModelAndView modelandView) {
//
//		String viewName = "/organization/updateAfter";
//		int count = (int) service.updateObject(paramMap);
//
//		this.list(paramMap, modelandView);
//
//		return modelandView;
//	}
}
