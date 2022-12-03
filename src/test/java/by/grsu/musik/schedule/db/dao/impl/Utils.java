package by.grsu.musik.schedule.db.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	
	protected Date getDateFromString(String dateStr) {
		try {
			return new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(dateStr).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
