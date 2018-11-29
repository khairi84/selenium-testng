package com.masteringselenium.config;

import java.util.HashMap;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public enum DriverType implements DriverSetup {
	
	
	FIREFOX{
		@Override
		public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			FirefoxOptions options = new FirefoxOptions();
			options.merge(capabilities);
			options.setHeadless(HEADLESS);
			return new FirefoxDriver(options);
		}
	},
	CHROME {
		
		@Override
		public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			HashMap<String, Object> chromePreferences = new HashMap<>();
			chromePreferences.put("profile.password_manager_enabled", false);
			
			ChromeOptions  options = new ChromeOptions();
			options.merge(capabilities);
			options.addArguments("--no-default-browser-check");
			options.setExperimentalOption("prefs", chromePreferences);
			options.setHeadless(HEADLESS);
			
			return new ChromeDriver(options);
		}
	},
	IE {
		@Override
		public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			InternetExplorerOptions options = new InternetExplorerOptions();
			options.merge(capabilities);
			options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
			options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
			
			return new InternetExplorerDriver(options);
		}
	},
	IDEGE {
		@Override
		public RemoteWebDriver getWebDriverObject(
				DesiredCapabilities capabilities) {
			EdgeOptions options = new EdgeOptions();
			options.merge(capabilities);
			
			return new EdgeDriver(options);
		}
		
	},
	SAFARI {

		@Override
		public RemoteWebDriver getWebDriverObject(
				DesiredCapabilities capabilities) {
			SafariOptions options = new SafariOptions();
			options.merge(capabilities);
			
			return new SafariDriver(options);
		}
	},
	OPERA{

		@Override
		public RemoteWebDriver getWebDriverObject(
				DesiredCapabilities capabilities) {
			OperaOptions options = new OperaOptions();
			options.merge(capabilities);
			
			return new OperaDriver(options);
		}
		
	};

	public static final boolean HEADLESS = Boolean.getBoolean("headless");

}
