package Page_Object_Test;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Page_Utility.Login_page;
import Page_Utility.Select_Vehicle_Page;
import Utility.BaseUtility;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Login_Page_Test extends BaseUtility {

	public Login_page ob;
	public Select_Vehicle_Page ob1;
	@Test(priority = 0)
	public void TC001_Verify_Tearms_Of_Use() throws InterruptedException {
		Message("***************************Login page***************************************");
		ob = new Login_page();
		Custom_click(ob.terms_of_use(), ob.terms_of_use().getText());
		Thread.sleep(7000);
		ob.Terms_of_use_info();
		Custom_click(ob.back_button(), "Back from terms of use button");
	}

	@Test(priority = 1)
	public void TC002_Verify_Privacy_Policy() throws InterruptedException {
		Custom_click(ob.privacy_Policy(), ob.privacy_Policy().getText());
		Thread.sleep(7000);
		ob.privacy_policy_info();
		Custom_click(ob.back_button(), "Back from Privacy Policy button");
	}

	@Test(priority = 2)
	public void TC003_Verify_Login_with_invalidmobileno() {
		custom_sendkeys(ob.Enter_mobile_no(), config_getdata("invalidmobileno"),
				"Login with unregisterd mobile number");
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		Custom_click(ob.continue_button(), "Continue button enter after unregistered mobile");
		try {
			msg(ob.Registered_message(), ob.Registered_message().getText());
		} catch (Exception e) {
			Message("Registered message popup not visible");
		}
	}

	@Test(priority = 3)
	public void TC004_Verify_Login_with_short_length_of_mobile_number() {
		ob.Enter_mobile_no().clear();
		custom_sendkeys(ob.Enter_mobile_no(), config_getdata("short_digit_mobile_number"),
				"Login with shorts digit mobile number");
		driver.navigate().back();
		if (ob.continue_button().isSelected()) {
			Message("Continue button enter after short length of mobile number is Visible & clickable");
		} else {
			Message("Continue button enter after short length of mobile number is not Visible & not clickable");
		}

	}

	@Test(priority = 4)
	public void TC005_Verify_Login_With_maxdigit_mobile_number() throws InterruptedException {
		ob.Enter_mobile_no().clear();
		custom_sendkeys(ob.Enter_mobile_no(), config_getdata("max_digit_mobile_number"),
				"Login with maximum digit mobile number");
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		Custom_click(ob.continue_button(), "Coninue button");
		if (config_getdata("max_digit_mobile_number").equalsIgnoreCase(ob.mobile_no().getText())) {
			Message("More then 10 digit mobile numbers are acceptable");
		} else {
			Message("More then 10 digits mobiles number are not acceptable");
		}
		Thread.sleep(3000);
		Custom_click(ob.back_arrow(), "Back from OTP page");
	}

	@Test(priority = 5)
	public void TC006_Verify_Login_with_valid_credential() {
		ob.Enter_mobile_no().clear();
		custom_sendkeys(ob.Enter_mobile_no(), config_getdata("Valid_Mobile_Number"), "Login with Registered mobile");
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		VerifyElementPresent(ob.continue_button(), "Coninue button is");
		Custom_click(ob.continue_button(), "Coninue button after enter Valid mobile number");
		Assert.assertEquals(config_getdata("Valid_Mobile_Number"), ob.mobile_no().getText());
	}

	@Test(priority = 6)
	public void TC007_Verify_Resend_Button_And_Invalid_OTP() throws InterruptedException, AWTException {
		msg(ob.enter_otp_message(), ob.enter_otp_message().getText());
		Message("Please wait for 30 Seconds if you did not receive OTP then you will tap on 'Resend' button");
		Thread.sleep(30000);
		Custom_click(ob.resend_OTP(), ob.resend_OTP().getText());
		Thread.sleep(15000);
		ob.invalid_OTP();
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.TAB));
		Custom_click(ob.Verify_button(), ob.Verify_button().getText());
		try {
			msg(ob.OTP_response_message(), ob.OTP_response_message().getText());
		} catch (Exception e) {
			Message("OTP response message popup not visible");
		}
	}

	@Test(priority = 7)
	public void TC008_Login_with_Valid_OTP() {
		msg(ob.enter_otp_message(), "Please " + ob.enter_otp_message().getText() + " again");
		ob.valid_OTP();
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.TAB));
		VerifyElementPresent(ob.Verify_button(), "Verify button is");
		Custom_click(ob.Verify_button(), ob.Verify_button().getText());
	}

	public void Login() throws InterruptedException {
		ob = new Login_page();
		ob1 = new Select_Vehicle_Page();
		Thread.sleep(2000);
		custom_sendkeys(ob.Enter_mobile_no(), config_getdata("Valid_Mobile_Number"), "Login with Registered mobile");
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		VerifyElementPresent(ob.continue_button(), "Coninue button is");
		Custom_click(ob.continue_button(), "Coninue button after enter Valid mobile number");
		msg(ob.enter_otp_message(), ob.enter_otp_message().getText());
		ob.valid_OTP();
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.TAB));
		VerifyElementPresent(ob.Verify_button(), "Verify button is");
		Custom_click(ob.Verify_button(), ob.Verify_button().getText());
		Thread.sleep(3000);
		ob1.Vehicle_prime_detail();
		Custom_click(ob1.click_first_vehicle(), " Select first vehicle as prime");
		Custom_click(ob1.continue_button(), "Continue Button after select vehicle");
		Thread.sleep(2000);
		Custom_click(ob1.while_using_the_app(), ob1.while_using_the_app().getText());
		Custom_click(ob1.Allow(), " Allow H-D Connect to access your phone call logs?");
		Custom_click(ob1.Allow(), " Allow H-D Connect to access your contacts?");
		Custom_click(ob1.Allow(), " Allow H-D Connect to make and manage phone calls?");
		Custom_click(ob1.Allow(), " Allow H-D Connect to send and view SMS messages?");
		Thread.sleep(2000);
		Custom_click(ob1.Allow(),
				"Allow H-D Connect to find, connect to, and determine the relative position of nearby devices?");
	}
}