package Page_Object_Test;

import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.annotations.Test;

import Page_Utility.My_Profile_Page;
import Utility.BaseUtility;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
public class My_Profile_Page_Test extends BaseUtility{
	public My_Profile_Page ob;
	public Login_Page_Test login;
	public Map<String, String> map;
	@Test(priority = 0)
	public void TC028_Verify_My_Profile() throws InterruptedException {
		Message("************************My_Profile_Page_Test**************************");
		ob = new My_Profile_Page();
		login = new Login_Page_Test();
		login.Login();
		Thread.sleep(5000);
			Custom_click(ob.home(), "Home");
			Thread.sleep(2000);
			Custom_click(ob.side_menu_button(), "Side menu button");
			Custom_click(ob.my_profile(), "My profile");
			Thread.sleep(2000);
			msg(ob.user_name(),"User name = " + ob.user_name().getText());
			msg(ob.user_mobile_no(),"User mobile number =" + ob.user_mobile_no().getText());
			msg(ob.user_email(),"User email =" + ob.user_email().getText());
		}

	@Test(priority = 1)
	public void TC029_verify_more_detais_button() {
			Custom_click(ob.More_details(), "More details arrow");
			ob.user_info();
	}

	@Test(priority = 2)
	public void TC030_edit_profile_button() throws InterruptedException {
			Custom_click(ob.edit_profile_details_button(), "Edit profile details button");
			Custom_click(ob.profile_pic_edit_btn(), "profile pic edit button");
			try {
				Custom_click(ob.while_using_the_app(), "while_using_the_app");
				Custom_click(ob.allow(), "Allow");
				Custom_click(ob.profile_pic_edit_btn(), "profile pic edit button");
				Custom_click(ob.Choose_from_library(), " Chose profile pic from liberary ");
			}catch(Exception e) {
				Message("while_using_the_app popup is not given");
				Custom_click(ob.Choose_from_library(), " Chose profile pic from liberary ");
			}
			Thread.sleep(2000);
			driver.navigate().back();
			Custom_click(ob.profile_pic_edit_btn(), "profile pic edit button");
			Custom_click(ob.Take_a_Photo(), " Take a pic for profile ");
			Thread.sleep(2000);
			driver.navigate().back();
			Custom_click(ob.profile_pic_edit_btn(), "profile pic edit button");
			Custom_click(ob.upload_close_button(), " Upload close button ");
		
	}

	@Test(priority = 3)
	public void TC031_edit_Personal_Details() throws InterruptedException {
		map = new LinkedHashMap<>();
			map.put("Name", ob.edit_full_name().getText());
			map.put("Email ID", ob.edit_email_id().getText());
			map.put("Registered mobile number", ob.edit_registered_mobile_number().getText());
			custom_sendkeys(ob.edit_full_name(), config_getdata("edit_name_in_personal_detail"), " Change Name");
			((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.TAB)); 
			ob.gender_selection();
			driver.navigate().back();
			custom_sendkeys(ob.edit_email_id(), config_getdata("edit_email_id"), " Change Email id");
//			if (device.equalsIgnoreCase("pcloudy")) {
//			driver.navigate().back(); 				//for pCloudy 
//			}
			((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.TAB)); 		//for emulator
			Custom_click(ob.edit_date_of_birth(), " Date of Birth");
			Custom_click(ob.DOB_edit_button(), "Edit DOB");
			ob.send_dob().clear();
			custom_sendkeys(ob.send_dob(), config_getdata("edit_DOB"), "change dob in mm/dd/yyyy");
			if (ob.confirm_dob().isEnabled()) {
				Custom_click(ob.confirm_dob(), " Confirm DOB");
			} else {
				Message("DOB is not accepted " + config_getdata("edit_DOB"));
				Custom_click(ob.Cancel_dob(), " Cancel DOB");
			}
//			if (device.equalsIgnoreCase("pcloudy")) {
//			driver.navigate().back(); 								//for pCloudy
//			}
			Custom_click(ob.blood_group(), " Blood group");
			Thread.sleep(2000);
			Custom_click(ob.blood_group(), " Blood group");
			
			custom_sendkeys(ob.Street_name(), "Jindal colony", "Street name");
			driver.navigate().back();			
			Thread.sleep(2000);
			Custom_click(ob.pin_code(), "Pin code");
			ob.pin_code().clear();
			custom_sendkeys(ob.pin_code(), "110027", "Pin code");
			((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
			Custom_click(ob.Save_button(), "Save button");
			try {
				if (ob.More_details().isDisplayed()) {
					Message("Personal Details editd sucessfully");
				}
			} catch(Exception e) {
				Custom_click(ob.Back(), " Back from profile Details");
			}
		
	}

	@Test(dependsOnMethods = "TC031_edit_Personal_Details()", priority = 4)
	public void TC032_change_original_Personal_Details() throws InterruptedException {
			Custom_click(ob.More_details(), "More detail arrow ");
			Custom_click(ob.edit_profile_details_button(), "Edit profile details button");
			custom_sendkeys(ob.edit_full_name(), map.get("Name"), " Original Name");
			((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
			driver.navigate().back();
			custom_sendkeys(ob.edit_email_id(), map.get("Email ID"), "Original Email id");
			((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
			driver.navigate().back();
			Custom_click(ob.Save_button(), "Save button");
			Thread.sleep(3000);
			Custom_click(ob.More_details(), "More detail arrow ");
		
	}

	@Test(priority = 5)
	public void TC033_Manage_License() throws InterruptedException {
			Custom_click(ob.manage_license(), " Manage License");
			Thread.sleep(2000);
			try {
				if(ob.License_verify().isDisplayed()) {
				msg(ob.License_verify(),ob.License_verify().getText());
				Custom_click(ob.add_Liense_now(), "  Add License now");
				Custom_click(ob.Choose_License_from_library(), ob.Choose_License_from_library().getText());
				Thread.sleep(1000);
				driver.navigate().back();
				Custom_click(ob.add_Liense_now(), "  Add License now");
				Custom_click(ob.Take_License_Photo(), ob.Take_License_Photo().getText());
				Thread.sleep(1000);
				driver.navigate().back();
				Custom_click(ob.add_Liense_now(), "  Add License now");
				Custom_click(ob.close_button_for_license_upload(), " Close license upload page");
				}
			} catch(Exception e) {
				Message("Manage License is already given");
			}
			Custom_click(ob.Back(), " Back from Driver License");
	
	}

	@Test(priority = 6)
	public void TC034_Verify_emergency_contacts() throws InterruptedException {
			Custom_click(ob.emergency_contacts(), "Emergency contacts");
			Thread.sleep(2000);
			try {
				if(ob.License_verify().isDisplayed()) {
				msg(ob.License_verify(),"Emergency contacts" + ob.License_verify().getText());
				Custom_click(ob.add_Liense_now(), "  Add Emergency contacts");
				Custom_click(ob.Back(), " Back from Select Contacts page");
				}
			} catch (Exception e) {
				Message("Emergency contacts is already given");
			}
			Custom_click(ob.Back(), " Back from Emergency Contacts page");
			Custom_click(ob.Back(), " Back from profile Details");	
	}


}
