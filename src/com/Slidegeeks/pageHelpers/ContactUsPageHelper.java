package com.Slidegeeks.pageHelpers;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.Slidegeeks.Identifiers.ContactUsPageObjects;
import net.sourceforge.tess4j.TesseractException;
import utilities.CaptchaText;
import utilities.SeleniumMethods;

public class ContactUsPageHelper extends SeleniumMethods {

	WebDriver driver;

	public ContactUsPageHelper(WebDriver driver) {
		//super(driver);
		this.driver = driver;
	}

	public boolean EnterEmailAddress(String email) {
		driver.findElement(By.id(ContactUsPageObjects.emailAddress)).sendKeys(email);
		return driver.findElement(By.id(ContactUsPageObjects.emailAddress)).getAttribute("value").contains(email);
	}

	public boolean EnterName(String name) {
		driver.findElement(By.id(ContactUsPageObjects.name)).sendKeys(name);
		return driver.findElement(By.id(ContactUsPageObjects.name)).getAttribute("value").contains(name);
	}

	public boolean EnterPhoneNumber(String phoneNumber) {
		driver.findElement(By.id(ContactUsPageObjects.phoneNumber)).sendKeys(phoneNumber);
		return driver.findElement(By.id(ContactUsPageObjects.phoneNumber)).getAttribute("value").contains(phoneNumber);
	}

	public boolean EnterComment(String comment) {
		driver.findElement(By.id(ContactUsPageObjects.comment)).sendKeys(comment);
		return driver.findElement(By.id(ContactUsPageObjects.comment)).getAttribute("value").contains(comment);
	}

	public void EnterCaptchCode() throws IOException, InterruptedException, TesseractException {
		String fcaptchaCode = CaptchaText.getcaptcha(ContactUsPageObjects.captchaImage, driver);
		driver.findElement(By.id(ContactUsPageObjects.typeCaptch)).sendKeys(fcaptchaCode);
	}

	public void SubmitButton() {
		driver.findElement(By.id(ContactUsPageObjects.submitButton)).click();
	}

}
