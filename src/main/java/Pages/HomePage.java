package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import base.base;

public class HomePage extends base {
	By locationInput=By.xpath("//*[@id=\"c-omni-container\"]/div/div[1]/div[1]/input");
	By clear=By.xpath("//*[@id=\"c-omni-container\"]/div/div[1]/div[1]/span[2]");
	By specialistInput=By.xpath("//*[@id=\"c-omni-container\"]/div/div[2]/div[1]/input");
	
	
	public  void selectLocation() throws InterruptedException {
		driver.findElement(locationInput).click();
		driver.findElement(clear).click();
		String cityname=prop.getProperty("city");
		driver.findElement(locationInput).sendKeys(cityname);
		Thread.sleep(1000);
		driver.findElement(locationInput).sendKeys(Keys.ARROW_DOWN);
	    driver.findElement(locationInput).sendKeys(Keys.ARROW_DOWN);
	    driver.findElement(locationInput).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(3000);
		driver.findElement(locationInput).sendKeys(Keys.ENTER);
		Thread.sleep(1000);

		

		
		
	}
	public void selectSpecialist() throws InterruptedException {
		String specialization=prop.getProperty("speciality");
		driver.findElement(specialistInput).sendKeys(specialization);
		
		driver.findElement(specialistInput).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(specialistInput).sendKeys(Keys.ARROW_DOWN);
		 Thread.sleep(2000);
		 driver.findElement(specialistInput).sendKeys(Keys.ENTER);
		Thread.sleep(2000);  
	} 
}
