package com.learning.api.utils;

import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.learning.api.GlobalVariables;

public class LogUtils {
	GlobalVariables lgv = new GlobalVariables();

	public String LogFilePath() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyymmdd");
		String strDate = dateFormat.format(date);
		String logfilePath = GlobalVariables.logFilePath + strDate + ".txt";
		return logfilePath;
	}

	public void logs(String log) {
		// initialize a string
		String str = log;

		try {

			// attach a file to FileWriter
			FileWriter fw = new FileWriter(this.LogFilePath(), true);

			fw.write(str);

			// close the file
			fw.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public void logs(long log) {
		// initialize a string
		long str = log;

		try {

			// attach a file to FileWriter
			FileWriter fw = new FileWriter(this.LogFilePath(), true);

			fw.write((int) str);

			// close the file
			fw.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
