package Pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;

import base.base;

public class BookingDetails extends base {
	BookingSlot special=new BookingSlot();
	String actualName;
	String actualTime;
	String actualDate;
    By getDoctorName=By.xpath("//*[@id=\"container\"]/div[2]/div/div[1]/div/div[1]/div[3]/div/div[2]/div[1]");
    By getDate=By.xpath("//*[@id=\"container\"]/div[2]/div/div[1]/div/div[1]/div[2]/div[1]/div[1]/span[2]");
    By getTime=By.xpath("//*[@id=\"container\"]/div[2]/div/div[1]/div/div[1]/div[2]/div[1]/div[2]/span[2]");
    //getting details after booking slot
	public void getDetails() {
	    actualName=driver.findElement(getDoctorName).getText();
		actualDate=driver.findElement(getDate).getText();
	    actualTime=driver.findElement(getTime).getText();
		
	}
	public void validateDetails() throws ParseException {
		String expectedName=special.expectedDoctor;
		String expectedTime=special.time;
		//Validating name
		if(expectedName.equals(actualName)) {
			System.out.println("name is matching");
			test=report.createTest("Validate Name");
        	test.log(Status.PASS, "The name is matching");
			
		}
		else {
			System.out.println("name is not matching.we didnt get the expected results");
			test=report.createTest("Validate Name");
        	test.log(Status.FAIL, "The name is not matching");
		}
		//validating time
		String convertedTime = null;
		if(expectedTime.charAt(0)=='0') {
			convertedTime=expectedTime.replaceFirst("0", "");
		}
		
		
		if(convertedTime.equals(actualTime)) {
			System.out.println("time is matching");
			test=report.createTest("Validate Time");
        	test.log(Status.PASS, "The time is matching");
		}
        else {
			
			System.out.println("time is not matching.we didnt get the expected results");
			test=report.createTest("Validate Time");
        	test.log(Status.FAIL, "The time is not matching");
		}
		//validating date
		LocalDate ExpectedDate=special.date;
		SimpleDateFormat inputFormat = new SimpleDateFormat("MMM dd, yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = inputFormat.parse(actualDate);
        String convertedDate = outputFormat.format(date1);
        
        if(ExpectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals(convertedDate)) {
			System.out.println("date is matching");
			test=report.createTest("Validate Date");
        	test.log(Status.PASS, "The date is matching");
		}
        else {
			
			System.out.println("date is not matching.we didnt get the expected results");
			test=report.createTest("Validate Date");
        	test.log(Status.FAIL, "The date is not matching");
		}
        
		
	}
	

}
