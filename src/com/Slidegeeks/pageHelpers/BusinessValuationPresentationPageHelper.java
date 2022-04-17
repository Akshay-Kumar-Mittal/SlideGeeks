package com.Slidegeeks.pageHelpers;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.Slidegeeks.Identifiers.BusinessValuationPresentationPageObject;
import net.sourceforge.tess4j.TesseractException;
import utilities.CaptchaText;
import utilities.SeleniumMethods;

public class BusinessValuationPresentationPageHelper extends SeleniumMethods {

	WebDriver driver;

	public BusinessValuationPresentationPageHelper(WebDriver driver) {
		//super(driver);
		this.driver = driver;
	}

	public void navigatetoUrl() {
		driver.navigate().to(
				"https://www.slidegeeks.com/design-services/presentation-portfolio-designs/business-valuation-presentation");
	}

	public void EnterEmailAddress(String email) {
		driver.findElement(By.id(BusinessValuationPresentationPageObject.emailAddress)).sendKeys(email);
	}

	public void EnterName(String name) {
		driver.findElement(By.id(BusinessValuationPresentationPageObject.name)).sendKeys(name);
	}

	public void EnterPhoneNumber(String phoneNumber) {
		driver.findElement(By.id(BusinessValuationPresentationPageObject.phoneNumber)).sendKeys(phoneNumber);
	}

	public void EnterComment(String comment) {
		driver.findElement(By.id(BusinessValuationPresentationPageObject.comment)).sendKeys(comment);
	}

	public void EnterCaptchCode() throws IOException, InterruptedException, TesseractException {
		String fcaptchaCode;
		fcaptchaCode = CaptchaText.getcaptcha(BusinessValuationPresentationPageObject.captchaImage, driver);
		driver.findElement(By.id(BusinessValuationPresentationPageObject.typeCaptch)).sendKeys(fcaptchaCode);
	}

	public void SubmitButton() {
		driver.findElement(By.id(BusinessValuationPresentationPageObject.submitButton)).click();
	}

}
