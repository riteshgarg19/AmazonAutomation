package amazonTestScript;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static WebDriver driver;
    Properties config = new Properties();
    Properties locators = new Properties();
    FileInputStream fis = null;
    ExcelReader excelReader = new ExcelReader();
    String amazon_signIn_title = "Amazon Sign In";
    String excelDataPath = System.getProperty("user.dir") + "\\src\\testData\\";
    String excelFileName = "AmazonProductDetails.xlsx";


    public WebDriver init() throws Exception {
        String configDataPath = System.getProperty("user.dir") + "\\src\\configurationData\\";

        if (driver == null) {
            // Load config.properties file
            fis = new FileInputStream(configDataPath + "config.properties");
            config.load(fis);

            // Load locators.properties file
            fis = new FileInputStream(configDataPath + "locators.properties");
            locators.load(fis);

            // Get browser details from config file and Initialize the browser driver
            if (config.getProperty("browser").equals("firefox")) {
                System.setProperty("webdriver.gecko.driver", configDataPath + "geckodriver.exe");
                driver = new FirefoxDriver();
                System.out.println("Initialized Firefox Browser");
            } else if (config.getProperty("browser").equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", configDataPath + "chromedriver.exe");
                driver = new ChromeDriver();
                System.out.println("Initialized Chrome Browser");
            }

            // Open Url
            driver.get(config.getProperty("amazonUrl"));
            driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
            // Maximize browser window
            driver.manage().window().maximize();
            if (driver.getTitle() != "") {
                System.out.println("Url is successfully launched");
            }
        }
        return driver;
    }
}
