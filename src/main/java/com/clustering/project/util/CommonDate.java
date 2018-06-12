package com.clustering.project.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CommonDate {
	public String getDate() {
		Date date = new Date();

		SimpleDateFormat formatType = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatType.format(date);

		return dateString;
	}

}
