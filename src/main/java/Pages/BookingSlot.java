package Pages;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

import base.base;

public class BookingSlot extends base {
	public static String expectedDoctor;
	public static String time;
	public static LocalDate date;
	 //to book the clinic visit
	  By bookVisit=By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[2]/div[1]/div/div[2]/div/div/div[2]/div/button");
	  
	  //to get the doctor name for evaluation purpose
	  By getdoctor=By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[2]/div[1]/div/div[1]/div[2]/a/div/h2");
	  
	  //to get text in the today,tomorrow,day after tomorrow tabs
	  By todaySlotText=By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div[1]/div[2]/div[1]/div[2]");
	  By tomorrowSlotText=By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div[1]/div[2]/div[2]/div[2]");
	  By dayAfterTomorrowSlotText=By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div[1]/div[2]/div[3]/div[2]");
	  
	  //to click on the slots
	  By todaySlot=By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/div[2]/div[1]");
	  By tomorrowSlot=By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/div[2]/div[1]");
	  By dayAfterTomorrowSlot=By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/div[2]/div[3]");
	  
	  //To validate city name
	  By getCityName=By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[1]/div[1]");
	  
	  //to validate speciality
	  By speciality=By.xpath("//div[@class=\"uv2-spacer--jumbo-xl-hz\"]/child::span");
	// By speciality=By.xpath("//div[@class=\"u-d-flex\"]/child::span");
	 
	 //to validate filters
	 By filter=By.xpath("//*[@id=\"container\"]/div/div[3]/div/div/header/div[1]/div/div[3]/span/span");
	 By radioButton=By.xpath("//*[@id=\"container\"]/div/div[3]/div/div/header/div[2]/div/div[1]/div/label[1]/div");
	 By amount=By.xpath("//div[@class=\"uv2-spacer--jumbo-xl-hz\"]/child::span");
	   
	  //to validate city name in header text
	  public void  validateCity() {
		  String cityName=driver.findElement(getCityName).getText();
		  boolean isValid=true;
		  String location=prop.getProperty("location");
		  if(!cityName.contains(location)) {
			  isValid=false;
		  }
		  //generating report
		  if(isValid) {
			    System.out.println("location is matching.");
				test=report.createTest("Validate City");
	        	test.log(Status.PASS, "we got the desired result");
	        	
	        }
	        else
	        {	
	        	 System.out.println("location is not matching.we didnt get the desired result");
                test=report.createTest("Validate City");
	        	test.log(Status.FAIL, "we didnt get the desired result");
	        }
		  
		 
	  }
	  
	  public void validateSpeciality() throws InterruptedException {
		  List<WebElement> doctorlist=driver.findElements(speciality);
		  boolean isValid=true;
		  for(WebElement Speciality:doctorlist) {
	            String specialist=Speciality.getText().toLowerCase();

				if(!specialist.equals("Ayurveda")) {
				       isValid=false;
				       break;
			    }
		 }
		 
		  if(isValid) {
				System.out.println("All doctors are specialized in the specified speciality.we got the desired results");
				test=report.createTest("Validate specialization");
	        	test.log(Status.PASS, "we got the desired speciality");
	        	
	        }
	        else
	        {	
	        	test=report.createTest("Validate speciality");
	        	test.log(Status.FAIL, "we didnt get the desired result");
	        }
		  Thread.sleep(2000);
		  
		  
		  
	  }
	  //clicking on book clinic visit
	  public void selectDoctor() {
		  
		  driver.findElement(bookVisit).click();
		   expectedDoctor=driver.findElement(getdoctor).getText();
		  System.out.println("Doctor name:"+expectedDoctor);
		  
		  
	  }
	  //To book the slots
      public void bookSlot() {
    	  String todaySlot_Text=driver.findElement(todaySlotText).getText();
    	  String tomorrowSlot_Text=driver.findElement(tomorrowSlotText).getText();
    	  String dayAfterTomorrowSlot_Text=driver.findElement(dayAfterTomorrowSlotText).getText();
    	  
    	  
    	  if(todaySlot_Text.contains("No")) {
    		  if(!tomorrowSlot_Text.contains("No")) {
    			  driver.findElement(tomorrowSlotText).click();
    			  time=driver.findElement(tomorrowSlot).getText();
    			  driver.findElement(tomorrowSlot).click();
    			  date = LocalDate.now().plusDays(1); 
    			  
    		  }
    		  else {
    			  driver.findElement(dayAfterTomorrowSlotText).click();
    			  time=driver.findElement(dayAfterTomorrowSlot).getText();
    			  driver.findElement(dayAfterTomorrowSlot).click();
    			  date = LocalDate.now().plusDays(2); 
    			 
    		  }
    	  }
    	  else {
    		  driver.findElement(todaySlotText).click();
    		  time=driver.findElement(todaySlot).getText();
    		  driver.findElement(todaySlot).click();
    		  date=LocalDate.now();
    		  
    	  }
      }
      
      public void validateFilter() throws Exception {
    	  driver.findElement(filter).click();
    	  Thread.sleep(2000);
    	  driver.findElement(radioButton).click();
    	  Thread.sleep(5000);
    	  boolean isValid=true;
    	  List<WebElement> consultationFee=driver.findElements(amount);
    	  for(WebElement cash:consultationFee) {
    		  String Amount=cash.getText();
    		  if(Integer.parseInt(Amount)>500 && Integer.parseInt(Amount)<0) {
    			  isValid=false;
    			  break;
    		  }
    	  }
    	  if(isValid) {
				System.out.println("The consultation fee is with in the given range.So we got desired results");
				test=report.createTest("Validate filters");
	        	test.log(Status.PASS, "Consultation fee is with in the range");
	        	
	        }
	        else
	        {	
	        	System.out.println("The consultation fee is not with in the given range.So we didnt get desired results");
	        	test=report.createTest("Validate speciality");
	        	test.log(Status.FAIL, "Consultation fee is not with in the range");
	        }
      }
    	  
    	  
    	  
      
}
