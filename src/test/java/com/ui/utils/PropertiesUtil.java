package com.ui.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	
	public static String readPropertyFile(String url) {
		File propFile = new File(System.getProperty("user.dir") + "\\config\\QA.properties");

		if (!propFile.exists()) {
			throw new IllegalArgumentException("Property file not found: " + propFile.getAbsolutePath());
		}

		FileReader fileReader = null;
		Properties properties = null;
		try {
			fileReader = new FileReader(propFile);
			properties = new Properties();
			properties.load(fileReader);

		} catch (IOException e) {
			e.printStackTrace();
		}

		if (properties.isEmpty()) {
			throw new IllegalStateException("Failed to load properties for environment: " + url);
		}
		return properties.getProperty(url);
	}

}
