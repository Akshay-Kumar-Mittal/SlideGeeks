package utilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class CaptchaText {
	
	public static String getcaptcha(String object, WebDriver driver) throws IOException, InterruptedException, TesseractException {
		Thread.sleep(5000);
		WebElement captchaImage = driver.findElement(By.xpath(object));
		//WebElement captchaImage = driver.findElement(By.id("captchaimage"));
		File src = captchaImage.getScreenshotAs(OutputType.FILE);
		String path = "C:\\Users\\DELL\\eclipse-workspace\\SlidegeekFormsReports\\captchaImages\\captcha.png";
		FileHandler.copy(src, new File(path));
//		Thread.sleep(3000);
		ITesseract image = new Tesseract();
		String captchaCodeText = image.doOCR(new File(path));
		String finalcaptchaCodeText = captchaCodeText.split(" ")[0];
		return finalcaptchaCodeText;
		// Add refresh button xpath as well
//		driver.findElement(By.id(ContactUsPageObjects.typeCaptch)).sendKeys(finalcaptchaCodeText);
		
	}

}
