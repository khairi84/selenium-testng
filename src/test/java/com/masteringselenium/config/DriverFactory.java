package com.masteringselenium.config;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

	private RemoteWebDriver webDriver;
	private DriverType selectedDrriverType;
	
	private final String OPERATION_SYSTEM = System.getProperty("os.name").toUpperCase();
	private final String SYSTEM_ARCHITECTURE = System.getProperty("os.arch");
	
	public DriverFactory(){
		DriverType driverType = DriverType.FIREFOX;
		String browser = System.getProperty("browser", driverType.toString()).toUpperCase();
		try{
			driverType = DriverType.valueOf(browser);
		}catch(IllegalArgumentException ignored){
			System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
		}catch(NullPointerException ignored){
			System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
		}
		selectedDrriverType = driverType;
	}
	
	private void instantiateWebDriver(DriverType driveType){
		System.out.println(" ");
		System.out.println("Local Operationg System :" + OPERATION_SYSTEM);
		System.out.println("Local Architecture : " + SYSTEM_ARCHITECTURE);
		System.out.println("Selected Browser : " + selectedDrriverType.toString());
		System.out.println(" ");
		
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		
		webDriver = driveType.getWebDriverObject(desiredCapabilities);
	}
	
	public RemoteWebDriver getDriver(){
		if(null == webDriver) {
			instantiateWebDriver(selectedDrriverType);
		}
		return webDriver;
	}
	
	public void quitDriver(){
		if(null != webDriver) {
			webDriver.quit();
			webDriver = null;
		}
	}
}
