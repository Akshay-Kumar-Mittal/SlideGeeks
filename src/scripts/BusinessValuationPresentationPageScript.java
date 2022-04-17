package scripts;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.sourceforge.tess4j.TesseractException;
import utilities.BaseClass;

public class BusinessValuationPresentationPageScript extends BaseClass {
	
	@BeforeClass
	public void navigateToUrl() {
		bvpPageHelper.navigatetoUrl();
	}
	
	@Test
	public void inputEmailAddress() {
		extentTest.info("User is able to click on women link");
		bvpPageHelper.EnterEmailAddress("akstst@testing.com");
	}
	
	@Test
	public void inputName() {
		extentTest.info("User is able to click on women link");
		bvpPageHelper.EnterName("Akshaytesting");
	}
	
	@Test
	public void inputPhoneNumber() {
		extentTest.info("User is able to click on women link");
		bvpPageHelper.EnterPhoneNumber("546545454");
	}
	
	@Test
	public void inputComment() {
		extentTest.info("User is able to click on women link");
		bvpPageHelper.EnterComment("testing");
	}
	
	@Test
	public void inputCaptchaCode() throws IOException, InterruptedException, TesseractException {
		bvpPageHelper.EnterCaptchCode();
	}
	
	@AfterClass
	public void ClickonSubmitButton() {
		bvpPageHelper.SubmitButton();
	}


}
