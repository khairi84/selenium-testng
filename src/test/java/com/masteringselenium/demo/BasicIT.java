package com.masteringselenium.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class BasicIT extends DriverBase{
	
	private ExpectedCondition<Boolean> pageTitleStartWith(final String searchString) {
		return driver -> driver.getTitle().toLowerCase().startsWith(searchString.toLowerCase());
	}
	
	private void googleExampleThatSearchesFor(final String searchString) {
		WebDriver driver = DriverBase.getDriver();
		
		driver.get("http://www.google.com");
		
		WebElement searchField = driver.findElement(By.name("q"));
		
		searchField.clear();
		
		searchField.sendKeys(searchString);
		
		System.out.println("Page title is : " + driver.getTitle() );
		
		searchField.submit();
		
		WebDriverWait wait = new WebDriverWait(driver, 90, 100);
		
		wait.until(pageTitleStartWith(searchString));
		
		System.out.println("Page title is : " + driver.getTitle());
	}
	
	@Test
	public void googleCheeseExample(){
		googleExampleThatSearchesFor("Cheese!");
	}
	

	@Test
	public void googleMilkExample(){
		googleExampleThatSearchesFor("milk!");
	}
}