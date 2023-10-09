package Utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseUtility implements Config_Data_Provider, Excel_Data_Provider, Extent_Report_Generator, Library {
	public static Logger log;
	public static ExtentSparkReporter report;
	public static AppiumDriverLocalService service;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static Listner lis;
	public static WebDriverWait wait;
	String confipath = System.getProperty("user.dir") + "\\Config_Data\\Config.Properties";
	String excelpath = System.getProperty("user.dir") + "\\Data\\data1.xlsx";
	public static AndroidDriver driver;
    @BeforeTest
    public void install_And_Open_Mobile_App()
    {
    	String Device_name = config_getdata("Platform_name");
		if(Device_name.equalsIgnoreCase("emulator")) {
		try {
			UiAutomator2Options db = new UiAutomator2Options();
			db.setCapability("appium:automationName", "uiautomator2");
			db.setCapability("platformName", "Android");
			db.setCapability("appium:deviceName", "Pixel_6_API_31");
			db.setCapability("appium:udid", "emulator-5554");
			db.setCapability("appium:avdLaunchTimeout", 900000);
			db.setCapability("appium:app", (System.getProperty("user.dir") + "\\apk\\app-debug_33.apk"));
			driver = new AndroidDriver(new URL(config_getdata("IpAddress")), db);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			db.setCapability("appium:ensureWebviewsHavePages", true);
			db.setCapability("appium:nativeWebScreenshot", true);
			db.setCapability("appium:newCommandTimeout", 9600);
			log = LogManager.getLogger("Hero_App");
			lis = new Listner();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
		else if(Device_name.equalsIgnoreCase("pcloudy")) {
     try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("pCloudy_Username", "randhir.kumar@heromotocorp.com");
			capabilities.setCapability("pCloudy_ApiKey", "2gdc5pv55mh54mqtwmvj4xbr");
			capabilities.setCapability("pCloudy_DurationInMinutes", 90);
			capabilities.setCapability("newCommandTimeout", 600);
			capabilities.setCapability("launchTimeout", 90000);
			capabilities.setCapability("pCloudy_DeviceManufacturer", "GOOGLE");
			capabilities.setCapability("pCloudy_DeviceVersion", "11.0.0");
			capabilities.setCapability("platformVersion", "11.0.0");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("automationName", "uiautomator2");
			capabilities.setCapability("pCloudy_ApplicationName", "app-debug_33.apk");
			capabilities.setCapability("appPackage", "com.customerapp.hero");
			capabilities.setCapability("appActivity", "com.customerapp.hero.views.activity.HmcDashboard");
			capabilities.setCapability("pCloudy_WildNet", "false");
			capabilities.setCapability("pCloudy_EnableVideo", "true");
			capabilities.setCapability("pCloudy_EnablePerformanceData", "false");
			capabilities.setCapability("pCloudy_EnableDeviceLogs", "true");
			capabilities.setCapability("appiumVersion", "2.0.0");
			 driver = new AndroidDriver(new URL("https://device.pcloudy.com/appiumcloud/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			 log = LogManager.getLogger("Hero_App");
			 
		} catch (Exception e) {
			System.out.println(e);
		}
		}
	else if(Device_name.equalsIgnoreCase("realdevice")) {
		try {
			UiAutomator2Options db = new UiAutomator2Options();
			db.setCapability("appium:automationName", "uiautomator2");
			db.setCapability("platformName", "Android");
			db.setCapability("appium:deviceName", "realme C33 2023");
			db.setCapability("appium:udid", "192.168.1.2:5555"); //3323262910AA04DS //192.168.1.2:5555
			db.setCapability("appium:avdLaunchTimeout", 600000);
			db.setCapability("appPackage", "com.customerapp.hero");
			db.setCapability("appActivity", "com.customerapp.hero.views.activity.HmcDashboard");
			db.setCapability("appium:noReset", "false");
//			db.setCapability("appium:app", (System.getProperty("user.dir") + "\\apk\\app-debug-connected.apk"));
			driver = new AndroidDriver(new URL(config_getdata("IpAddress")), db);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			db.setCapability("appium:ensureWebviewsHavePages", true);
			db.setCapability("appium:nativeWebScreenshot", true);
			db.setCapability("appium:newCommandTimeout", 6600);
			log = LogManager.getLogger("Hero_App");
			lis = new Listner();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
    }
	public void custom_sendkeys(WebElement element, String value, String fieldname) {
		try {
			if (element.isEnabled() || element.isDisplayed() == true) {
				wait = new WebDriverWait(driver, Duration.ofSeconds(20));
				wait.until(ExpectedConditions.visibilityOf(element));
				element.click();
				element.sendKeys(value);
				test.log(Status.PASS, fieldname + " send successfully =" + value);
				log.info(fieldname + " send successfully =" + value);
			}
		} catch (Exception e) {
			test.log(Status.FAIL, fieldname + " is not able to send" + e);
			test.addScreenCaptureFromPath(lis.getcapcture(fieldname));
			log.error(fieldname + " is not able to send");
		}
	}

	public void Custom_click(WebElement element, String fieldname) {
		try {
			if (element.isDisplayed() || element.isEnabled() == true) {
				wait = new WebDriverWait(driver, Duration.ofSeconds(20));
				wait.until(ExpectedConditions.elementToBeClickable(element));
				element.click();
				test.log(Status.PASS, "Successfully click on = " + fieldname);
				log.info(fieldname + " is clickable");
			}
		} catch (Exception e) {
			test.log(Status.FAIL, fieldname + "=Unable To Click =" + e);
			test.addScreenCaptureFromPath(lis.getcapcture(fieldname));
			log.error(fieldname + " is not clickable");

		}

	}

	public void VerifyImagePresent(WebElement image, String fieldname) {
		try {
			Boolean ImagePresent = (Boolean) ((JavascriptExecutor) driver).executeScript(
					"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
					image);
			if (ImagePresent) {
				test.log(Status.PASS, "Image is present:  " + fieldname);
				log.info("Image is present " + fieldname);
			} else {
				test.log(Status.FAIL, fieldname + "==Image is not present ==");
				test.addScreenCaptureFromPath(lis.getcapcture(fieldname));
				log.error("Image is not present" + fieldname);
			}
		} catch (Exception e) {
			test.log(Status.FAIL, fieldname + "==Image is not present ==" + e);
			test.addScreenCaptureFromPath(lis.getcapcture(fieldname));
			log.error("Image is not present" + fieldname);

		}
	}

	@SuppressWarnings("deprecation")
	public void PageLoaded() {
		String Title = null;
		try {
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			Title = driver.getTitle();
			test.log(Status.PASS, "Page is loaded : " + Title);
			log.info("Page is loaded " + Title);
		} catch (Exception e) {
			test.log(Status.FAIL, "==page is not loaded :" + e);
			log.error("page is not able to loaded " + Title);

		}
	}

	public void VerifyElementPresent(WebElement ele, String fieldname) {
		try {
			if (ele.isDisplayed() && ele.isEnabled() == true) {
				String Text = ele.getText();
				test.log(Status.PASS, fieldname + " present:");
				log.info(fieldname + " present");
			} else {
				test.log(Status.PASS, fieldname + "  not present");
				log.info(fieldname + "  not present");
			}
		} catch (Exception e) {
			test.log(Status.FAIL, fieldname + " not present" + e);
			test.addScreenCaptureFromPath(lis.getcapcture(fieldname));
			log.error(fieldname + "  not present");
		}

	}

	public void msg(WebElement ele, String filedname) {

		try {
			if (ele.isDisplayed()) {
				wait = new WebDriverWait(driver, Duration.ofSeconds(20));
				wait.until(ExpectedConditions.visibilityOf(ele));
				test.log(Status.PASS, filedname);
				log.info(filedname);
			}
		} catch (Exception e) {
			test.log(Status.FAIL, filedname + " is not readable" + e);
			test.addScreenCaptureFromPath(lis.getcapcture(filedname));
			log.error(filedname + " is not readable" + e);
		}

	}

	public void Message(String message) {
		test.log(Status.PASS, message);
		log.info(message);
	}

	public ExtentReports getreports() {
		String path = System.getProperty("user.dir") + "\\Report\\index.html";
		report = new ExtentSparkReporter(path);
		report.config().setDocumentTitle("Harley Test Report");
		report.config().setReportName("Harley_App");
		report.config().setTheme(Theme.STANDARD);
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Project Name", "Harley App");
		extent.setSystemInfo("Laptop", "Dell intel core i7");
		extent.setSystemInfo("QA", "Nitesh Kumar");
		extent.setSystemInfo("Operating system", "Windows 10 pro");
		extent.setSystemInfo("BrowserName", "Android Studio");
		return extent;

	}

	public String excel_getdata(int sheetno, int row_No, int col_No) {
		String value = "";
		try {
			FileInputStream fis = new FileInputStream(excelpath);
			XSSFWorkbook wf = new XSSFWorkbook(fis);
			XSSFSheet sheet = wf.getSheetAt(sheetno);
			value = sheet.getRow(row_No).getCell(col_No).getStringCellValue();

		} catch (Exception e) {
			System.out.println("Problem in read data from excel file" + e);
		}
		return value;
	}

	public int getRowCount(int sheetno) {
		int count = 0;
		try {
			FileInputStream fis = new FileInputStream(excelpath);
			XSSFWorkbook wf = new XSSFWorkbook(fis);
			XSSFSheet sheet = wf.getSheetAt(sheetno);
			count = sheet.getLastRowNum();

		} catch (Exception e) {
			System.out.println("Problem in read data from excel file for Row_Count" + e);
		}
		return count;
	}

	public int getColCount(int sheetno) {
		int count = 0;
		try {
			FileInputStream fis = new FileInputStream(excelpath);
			XSSFWorkbook wf = new XSSFWorkbook(fis);
			XSSFSheet sheet = wf.getSheetAt(sheetno);
			count = sheet.getRow(0).getLastCellNum();
		} catch (Exception e) {
			System.out.println("Problem in read data from excel file for Row_Count" + e);
		}
		return count;
	}

	public void excel_writedata(int sheetno, int row_No, int col_No, String value) {
		try {
			FileInputStream fis = new FileInputStream(excelpath);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(sheetno);
			sheet.createRow(row_No).createCell(col_No).setCellValue(value);
			FileOutputStream fos = new FileOutputStream(excelpath);
			workbook.write(fos);
			workbook.close();
		} catch (Exception e) {
			System.out.println("Problem in Write data in excel file" + e);
		}

	}

	public String config_getdata(String key) {
		String value = "";
		try {
			FileInputStream fis = new FileInputStream(confipath);
			Properties prop = new Properties();
			prop.load(fis);
			value = prop.getProperty(key);

		} catch (Exception e) {
			System.out.println("Problem in read data from property file" + e);
		}
		return value;
	}

}
