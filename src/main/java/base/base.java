package base;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class base {
      public static EdgeDriver driver;
      public static ExtentSparkReporter htmlreport;
	   public static ExtentReports report;
	   public static ExtentTest test;
	   public static Properties prop;
      @BeforeSuite
	public void setup() {
    	  htmlreport=new ExtentSparkReporter(new File("C:\\Users\\JahnaviT\\Desktop\\Report1.html"));
	   		htmlreport.config().setReportName("Practo");
	   		htmlreport.config().setDocumentTitle("Practo Functional Test");
	   		htmlreport.config().setTheme(Theme.DARK);
	   		report=new ExtentReports();
	   		report.setSystemInfo("Environment", "TestEnv");
	   		report.setSystemInfo("TesterName", "jahnavi");
	   		report.attachReporter(htmlreport); 
	    	   
		driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
    public void openUrl() throws IOException {
    	prop=new Properties();
    	FileInputStream input=new FileInputStream("src/main/java/config/config.properties");
    	prop.load(input);
    	String url=prop.getProperty("url");
    	driver.get(url);
    		
    	
    }
    public void reportFlush() {
    	report.flush();
    }
    @AfterSuite
    public void closeBrowser() {
 	   driver.close();
    }
    
}


