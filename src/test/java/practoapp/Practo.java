package practoapp;

import java.io.IOException;
import java.text.ParseException;

import org.testng.annotations.Test;

import Pages.BookingDetails;
import Pages.HomePage;
import Pages.BookingSlot;

public class Practo  {

	HomePage home=new HomePage();
	BookingSlot specialist=new BookingSlot();
	BookingDetails details=new BookingDetails();
	@Test(priority=1)
	public void validatingSpeciality() throws IOException, InterruptedException {

		home.openUrl();
		home.selectLocation();
		home.selectSpecialist();
		specialist.validateCity();
		specialist.validateSpeciality();
		//home.closeBrowser();
		
		
		
	}
	
	@Test(priority=2)
	public void validateNameDateTime() throws Exception  {
		
		home.openUrl();
		home.selectLocation();
		home.selectSpecialist();
		specialist.selectDoctor();
	    specialist.bookSlot();
	    details.getDetails();
	    details.validateDetails();
	   // home.closeBrowser(); 
	}
	@Test(priority=3)
	public void validateFilters() throws Exception {
		home.openUrl();
		home.selectLocation();
		home.selectSpecialist();
		specialist.validateFilter();
		home.reportFlush();
		
		
		
	}
}
