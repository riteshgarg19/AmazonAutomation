package amazonTestScript;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonUserLogin extends BaseClass {

    public void loginToAmazon() throws Exception {
        this.init();
        driver.findElement(By.xpath(locators.getProperty("click_signIn"))).click();
        if(driver.getTitle().equalsIgnoreCase(amazon_signIn_title)) {
            driver.findElement(By.id(locators.getProperty("login_email"))).sendKeys(config.getProperty("login_mobileNumber"));
            driver.findElement(By.id(locators.getProperty("login_continue"))).click();
            driver.findElement(By.id(locators.getProperty("login_password"))).sendKeys(config.getProperty("password"));
            driver.findElement(By.id(locators.getProperty("submitSignIn"))).click();
        }
        else {
            System.out.println("Amazon Login Page is not launched");
        }

    }


}
