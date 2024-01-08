package Page_Utility;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.BaseUtility;

public class Select_Vehicle_Page extends BaseUtility {

	public Select_Vehicle_Page() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//android.widget.TextView[contains(@resource-id,'com.customerapp.harley:id/lbl')]")
	private List<WebElement> Vehicle_prime_detail;
	@FindBy(xpath = "//android.widget.ImageView[@resource-id ='com.customerapp.harley:id/ic_edit']")
	private WebElement edit_nickname_button;
	@FindBy(xpath = "//android.widget.TextView[@resource-id ='com.customerapp.harley:id/btn_lbl']")
	private WebElement save_button;
	@FindBy(xpath = "//android.widget.EditText[@resource-id = 'com.customerapp.harley:id/textInputEditext']")
	private WebElement edit_nickame_text;
	@FindBy(xpath = "//android.widget.ImageView[@resource-id ='com.customerapp.harley:id/cross_btn']")
	private WebElement nick_name_close_button;
	@FindBy(xpath = "//android.widget.TextView[@resource-id ='com.customerapp.harley:id/give_a_nickname_lbl']")
	private WebElement Nick_name;
	@FindBy(xpath = "//android.widget.TextView[@resource-id ='com.customerapp.harley:id/btn_lbl']")
	private WebElement continue_button;
	@FindBy(xpath = "(//android.widget.TextView[@resource-id ='com.customerapp.harley:id/primary_veh_lbl'])[1]")
	private WebElement click_first_vehicle;
	@FindBy(xpath = "//android.widget.Button[@resource-id ='com.android.permissioncontroller:id/permission_allow_foreground_only_button']")
	private WebElement while_using_the_app;
	@FindBy(xpath = "//android.widget.Button[@resource-id ='com.android.permissioncontroller:id/permission_allow_button']")
	private WebElement Allow;

	public void Vehicle_prime_detail() {
		for (int i = 0; i < Vehicle_prime_detail.size(); i++) {
			msg(Vehicle_prime_detail.get(i), Vehicle_prime_detail.get(i).getText());
		}
	}

	public WebElement edit_nickname_button() {
		return edit_nickname_button;
	}

	public WebElement save_button() {
		return save_button;
	}

	public WebElement edit_nickame_text() {
		return edit_nickame_text;
	}

	public WebElement nick_name_close_button() {
		return nick_name_close_button;
	}

	public WebElement Nick_name() {
		return Nick_name;
	}

	public WebElement continue_button() {
		return continue_button;
	}

	public WebElement click_first_vehicle() {
		return click_first_vehicle;
	}

	public WebElement while_using_the_app() {
		return while_using_the_app;
	}

	public WebElement Allow() {
		return Allow;
	}
}
