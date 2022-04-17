package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.Slidegeeks.pageHelpers.BusinessValuationPresentationPageHelper;
import com.Slidegeeks.pageHelpers.ContactUsPageHelper;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public Properties prop;
	public FileInputStream fis;
	static WebDriver driver;
	public ContactUsPageHelper contactUsPageHelper;
	public BusinessValuationPresentationPageHelper bvpPageHelper;
	public static ExtentReports extentReport;
	public static ExtentHtmlReporter extentHtmlReporter;
	public ExtentTest extentTest;
	public String filePath;
	public boolean fileGenerated = false;
	SimpleDateFormat simpleDateFormat;
	String methodName = "";
	
	@BeforeSuite
	public void setup() throws IOException {
		prop = new Properties();
		fis = new FileInputStream("C:\\Users\\DELL\\eclipse-workspace\\SlidegeekFormsReports\\src\\properties\\ApplicationProperties.properties");
		prop.load(fis);
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.get(prop.getProperty("ContactUsPageurl"));
		
		filePath = System.getProperty("user.dir")+File.separator+"ExecutionReports"+File.separator+"SlideGeeksReports.html";
		simpleDateFormat = new SimpleDateFormat("hh_mm_ss_dd_MM_yyyy");

		if(!fileGenerated)
		{
			clearDirectory();
			extentReport = new ExtentReports();
			extentHtmlReporter = new ExtentHtmlReporter(new File(filePath));
			extentHtmlReporter.config().setDocumentTitle("SlideGeeks Automation Execution Result for "+"Chrome");
			extentHtmlReporter.config().setTheme(Theme.DARK);
			extentHtmlReporter.config().setReportName("SlideGeeks Automation Execution Result for "+ "Chrome");
			extentHtmlReporter.config().setTimeStampFormat("dd/MM/YYYY");
			extentReport.attachReporter(extentHtmlReporter);
			fileGenerated=true;
		}
	}
	
	@BeforeClass 	
	public void initializeObjects() {
		contactUsPageHelper = new ContactUsPageHelper(driver);
		bvpPageHelper = new BusinessValuationPresentationPageHelper(driver);
	}
	
	@AfterSuite
	public void quitDriver() {
		extentReport.flush();
		//driver.quit();
	}

	private void clearDirectory()
	{
		try
		{
			FileUtils.cleanDirectory(new File("ExecutionReports"));
		}
		catch(Exception e)
		{
			System.out.println("UNABLE TO CREATE FOLDER IN THE DRIVE");
			e.printStackTrace();
		}
	}
	
	@BeforeMethod
	public void getMethodName(Method method)
	{
		methodName = method.getName();
		extentTest = extentReport.createTest(methodName);
		extentTest.info("Executing test for Chrome");
	}

	@AfterMethod
	public void setTestResult(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			extentTest.pass("Pass...");
			//extentTest.addScreenCaptureFromPath(captureScreenshot(methodName));
		}
		else
			if(result.getStatus()==ITestResult.FAILURE)
			{
				extentTest.fail(result.getThrowable());
				extentTest.addScreenCaptureFromPath(captureScreenshot(methodName));
				extentTest.fail("Failed...");
			}
			else
			{
				extentTest.skip(result.getThrowable());
				extentTest.addScreenCaptureFromPath(captureScreenshot(methodName));
				extentTest.skip("Failed...");
			}
	}
	
	public void getScreenshot(String result) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src,
				new File("D://SlideGeeks//Screenshots//" + result + "screenshot.png"));
	}

	public String captureScreenshot(String methodName) {
		File destFile = null;
		try {
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
			destFile = new File(System.getProperty("user.dir") + "/ExecutionReports/screenshots/" + methodName + "_"
					+ simpleDateFormat.format(new Date()) + ".png");
			FileUtils.copyFile(srcFile, destFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return destFile.getAbsolutePath();
	}
	
	

}
