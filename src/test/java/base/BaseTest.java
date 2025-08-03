package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.DbManager;
import utilities.ExcelReader;

public class BaseTest {

	/*
	 * WebDriver TestNG Screenshot Log4j Properties Keywords Database JavaMail
	 * Extent Listeners Excel wait
	 */

	public WebDriver driver;
	public Logger log = Logger.getLogger(BaseTest.class.getName());
	public Properties Config = new Properties();
	public FileInputStream fis;
	public ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public WebDriverWait wait;
	public WebElement dropdown;

	public static String srcFileName;

	public String captureScreenshot() {

		Date d = new Date();

		srcFileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot,
					new File(System.getProperty("user.dir") + "\\target\\reports\\" + srcFileName));
			FileUtils.copyFile(screenshot,
					new File(System.getProperty("user.dir") + "/target/surefire-reports/html/" + srcFileName));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return srcFileName;

	}

	public String captureElementScreenshot(WebElement element) {

		Date d = new Date();
		String srcFileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		File screenshot = ((TakesScreenshot) element).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "\\screenshot\\" + srcFileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return srcFileName;

	}

	public void setUp(String browserName) {
		// Initialize log4j
		PropertyConfigurator.configure(".\\src\\test\\resources\\properties\\log4j.properties");
		log.info("Starting test suite setup...");

		try {
			fis = new FileInputStream(".\\src\\test\\resources\\properties\\Config.properties");
			Config.load(fis);
			log.info("Config Properties Loaded!");
		} catch (Exception e) {
			log.error("Failed to load Config.properties", e);
			throw new RuntimeException("Failed to load Config.properties", e);
		}

		try {

			FirefoxProfile profile = new FirefoxProfile();

			// Disable web notification prompts
			profile.setPreference("dom.webnotifications.enabled", false);
			profile.setPreference("permissions.default.desktop-notification", 2); // 2 = Block

			// Optional: Disable location prompts too
			profile.setPreference("geo.enabled", false);
			profile.setPreference("geo.provider.use_corelocation", false);
			profile.setPreference("geo.prompt.testing", true);
			profile.setPreference("geo.prompt.testing.allow", false);

			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(profile);
			String browser = Config.getProperty("browser");
			log.info("Initializing browser: " + browser);

			if (browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions option = new ChromeOptions();

				option.addArguments("--disable-notifications");
				driver = new ChromeDriver(option);
				log.info("Chrome Browser Launched");
			} else if (browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver(options);
				log.info("Firefox Browser Launched");
			} else if (browserName.equalsIgnoreCase("ie")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				log.info("IE Browser Launched");
			} else {
				throw new RuntimeException("Unsupported browser: " + browser);
			}

			String url = Config.getProperty("url");
			log.info("Navigating to URL: " + url);
			driver.get(url);

			DbManager.setMysqlDbConnection();
			log.info("Database connection established!");

			driver.manage().window().maximize();
			driver.manage().timeouts()
					.implicitlyWait(Duration.ofSeconds(Long.parseLong(Config.getProperty("implicit.wait"))));
			wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(Config.getProperty("explicit.wait"))));

		} catch (Exception e) {
			log.error("Setup failed!", e);
			throw new RuntimeException("Test setup failed", e);
		}
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		log.info("Test Execution Completed !!! ");
	}

}
