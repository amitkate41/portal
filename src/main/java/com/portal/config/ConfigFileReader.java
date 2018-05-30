package com.portal.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ConfigFileReader {

	private Properties properties;
	private static final String PROPERTY_FILE_PATH = "Configuration.properties";
	private static ConfigFileReader appConfig;

	private ConfigFileReader() {
		BufferedReader reader;
		try {
			File file = new File(getClass().getClassLoader().getResource(ConfigFileReader.PROPERTY_FILE_PATH).getFile());
			reader = new BufferedReader(new FileReader(file));
			properties = new Properties();
			properties.load(reader);
			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + ConfigFileReader.PROPERTY_FILE_PATH);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
	
	public static synchronized ConfigFileReader getInstace() {
		if (appConfig == null) {
			appConfig = new ConfigFileReader();
		}
		return appConfig;
	}

	public WebDriver getDriver() {
		String driverType = properties.getProperty("driverType");
		String driverPath = properties.getProperty("driverPath");
		
			if(driverType.equals("Chrome")) {
				System.setProperty("webdriver.chrome.driver", driverPath);
			    return   (WebDriver)new ChromeDriver();  
			}else {
				System.setProperty("webdriver.gecko.driver", "C:\\development\\geckodriver-v0.20.1-win64\\geckodriver.exe");
				return (WebDriver)new FirefoxDriver();  
			}
		/*else
			throw new RuntimeException("driverPath not specified in the Configuration.properties file.");*/
	}

	public long getImplicitlyWait() {
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if (implicitlyWait != null)
			return Long.parseLong(implicitlyWait);
		else
			throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");
	}

	private String getApplicationUrl(final String moduleType) {
		String baseUrl = properties.getProperty("applicationbaseUrl");
		String port = properties.getProperty("applicationPort");

		String moduleName = moduleType.equals("Portal") ? properties.getProperty("portalModuleName")
				: properties.getProperty("adminModuleName");
		final String isHttps = properties.getProperty("portalModuleName").equals("true") ? "https" : "http";

		StringBuilder applicationUrl = new StringBuilder(isHttps);
		applicationUrl.append("://");
		applicationUrl.append(baseUrl);
		applicationUrl.append(Constants.COLLON);
		applicationUrl.append(port);
		applicationUrl.append(Constants.URL_SEPARATOR);
		applicationUrl.append(moduleName);

		return applicationUrl.toString();
		// else throw new RuntimeException("url not specified in the
		// Configuration.properties file.");
	}

	public String getPortalUrl() {
		final StringBuilder applicationUrl = new StringBuilder(this.getApplicationUrl("Portal"));
		String landingUrl = properties.getProperty("portalLandingPageUrl");
		applicationUrl.append(landingUrl);
		return applicationUrl.toString();
	}

	public String getAdminUrl() {
		final StringBuilder applicationUrl = new StringBuilder(this.getApplicationUrl("Admin"));
		String landingUrl = properties.getProperty("adminLandingPageUrl");
		applicationUrl.append(landingUrl);
		return applicationUrl.toString();
	}
	
	public String getTestSuiteBaseDir() {
		return properties.getProperty("testSuiteBaseDir");
	}

}