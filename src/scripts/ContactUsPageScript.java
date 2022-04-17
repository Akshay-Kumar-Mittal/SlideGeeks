package scripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import net.sourceforge.tess4j.TesseractException;
import utilities.BaseClass;

public class ContactUsPageScript extends BaseClass {

	@Test
	public void inputEmailAddress() {
		boolean actual = contactUsPageHelper.EnterEmailAddress(prop.getProperty("email"));
		Assert.assertEquals(actual, "true");
		extentTest.info("User is able to enter email address");
	}

	@Test
	public void inputName() {
		boolean actual = contactUsPageHelper.EnterName(prop.getProperty("name"));
		Assert.assertEquals(actual, true);
		extentTest.info("User is able to enter name");
	}

	@Test
	public void inputPhoneNumber() {
		boolean actual = contactUsPageHelper.EnterPhoneNumber(prop.getProperty("phoneNumber"));
		Assert.assertEquals(actual, true);
		extentTest.info("User is able to enter phone number");
	}

	@Test
	public void inputComment() {
		boolean actual = contactUsPageHelper.EnterComment(prop.getProperty("comment"));
		Assert.assertEquals(actual, true);
		extentTest.info("User is able to enter comment");
	}

	@Test
	public void inputCaptchaCode() throws IOException, InterruptedException, TesseractException {
		contactUsPageHelper.EnterCaptchCode();
	}

	@AfterClass
	public void ClickonSubmitButton() {
		//contactUsPageHelper.SubmitButton();
	}

}
