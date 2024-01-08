package Page_Object_Test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Page_Utility.Select_Vehicle_Page;
import Utility.BaseUtility;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Select_Vehicle_Page_Test extends BaseUtility {
	public Select_Vehicle_Page ob;
	public Login_Page_Test login;

	@Test(priority = 0)
	public void TC009_verify_Nick_Name() throws InterruptedException {
		Message("************************Select_Vehicle_Page_Test**************************");
		ob = new Select_Vehicle_Page();
//		login = new Login_Page_Test();
//		login.Login();
		Thread.sleep(1000);
		Custom_click(ob.edit_nickname_button(), "Tap on pencil for Nick name ");
		VerifyElementPresent(ob.save_button(), "Save button before updating the nick name is ");
		ob.edit_nickame_text().clear();
		custom_sendkeys(ob.edit_nickame_text(), config_getdata("nickname"), "Nick Name ");
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		Custom_click(ob.nick_name_close_button(), "Nick name close button ");
		Thread.sleep(2000);
		if (ob.Nick_name().getText().equalsIgnoreCase(config_getdata("nickname"))) {
			Message("Nick name saved with close button");
		} else {
			Message("Nick name is not saved");
		}

	}

	@Test(priority = 1)
	public void TC010_Verify_With_30_letter_nick_name() throws InterruptedException {
		Custom_click(ob.edit_nickname_button(), "Tap on pencil for Nick name ");
		ob.edit_nickame_text().clear();
		custom_sendkeys(ob.edit_nickame_text(), config_getdata("30_letter_nick_name"), " 30 letter Nick Name ");
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		Custom_click(ob.save_button(), "Save button after updating the 30 letter nick name is ");
		Thread.sleep(2000);
		String nick1 = ob.Nick_name().getText();
		System.out.println(nick1 + " =" + nick1.length());
		if (nick1.length() == 30) {
			Message(" = 30 letter nick name are acceptable");
		} else {
			Message(" = 30 letter nick name are not acceptable");
		}
	}

	@Test(priority = 2)
	public void TC011_Verify_With_31_letter_nick_name() throws InterruptedException {
		Custom_click(ob.edit_nickname_button(), "Tap on pencil for Nick name ");
		ob.edit_nickame_text().clear();
		custom_sendkeys(ob.edit_nickame_text(), config_getdata("31_letter_nick_name"), "31 letter Nick Name ");
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		Custom_click(ob.save_button(), "Save button after updating 31 letter nick name the nick name  ");
		Thread.sleep(2000);
		String nick2 = ob.Nick_name().getText();
		System.out.println(nick2 + " =" + nick2.length());
		if (nick2.length() == 31) {
			Message(" = 31 letter nick name are acceptable");
		} else {
			Message(" = 31 letter nick name are not acceptable");
		}

	}

	@Test(priority = 3)
	public void TC012_Verify_With_29_letter_nick_name() throws InterruptedException {
		Custom_click(ob.edit_nickname_button(), "Tap on pencil for Nick name ");
		ob.edit_nickame_text().clear();
		custom_sendkeys(ob.edit_nickame_text(), config_getdata("29_letter_nick_name"), "29 letter Nick Name ");
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		Custom_click(ob.save_button(), "Save button after updating the 29 letter nick name ");
		Thread.sleep(2000);
		String nick3 = ob.Nick_name().getText();
		System.out.println(nick3 + " =" + nick3.length());
		if (nick3.length() == 29) {
			Message(" = 29 letter nick name are acceptable");
		} else {
			Message(" = 29 letter nick name are not acceptable");
		}
		Custom_click(ob.edit_nickname_button(), "Tap on pencil for Nick name ");
		custom_sendkeys(ob.edit_nickame_text(), config_getdata("nickname"), "Nick Name ");
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		Custom_click(ob.save_button(), "Successfully save nick name ");
	}

	@Test(priority = 4)
	public void TC013_Select_Vehicle() throws InterruptedException {
		VerifyElementPresent(ob.continue_button(), "Continue Button before select vehicle is");
		ob.Vehicle_prime_detail();
		Custom_click(ob.click_first_vehicle(), " Select first vehicle as prime");
		VerifyElementPresent(ob.continue_button(), "Continue Button after select vehicle is");
		Custom_click(ob.continue_button(), "Continue Button after select vehicle");
		Thread.sleep(2000);
		Custom_click(ob.while_using_the_app(), ob.while_using_the_app().getText());
		Custom_click(ob.Allow(), " Allow H-D Connect to access your phone call logs?");
		Custom_click(ob.Allow(), " Allow H-D Connect to access your contacts?");
		Custom_click(ob.Allow(), " Allow H-D Connect to make and manage phone calls?");
		Custom_click(ob.Allow(), " Allow H-D Connect to send and view SMS messages?");
		Thread.sleep(2000);
		Custom_click(ob.Allow(),
				"Allow H-D Connect to find, connect to, and determine the relative position of nearby devices?");
	}
}
