package Page_Utility;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import Utility.BaseUtility;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
@Listeners(Utility.Listner.class)
public class Login_page extends BaseUtility {
	public Actions act;
	public Login_page()
	{
		PageFactory.initElements(driver, this);
		act = new Actions(driver);
	}
	
	@FindBy(xpath ="//android.widget.TextView[@resource-id ='com.customerapp.harley:id/term_nd_cond_lbl']")
	private WebElement terms_of_use;
	@FindBy(xpath ="//android.widget.TextView[@resource-id ='com.customerapp.harley:id/pp_lbl']")
	private WebElement privacy_Policy;
	@FindBy(xpath ="//android.view.View[@resource-id='h-d.com']/android.view.View")
	private List<WebElement> Terms_of_use_info;
	@FindBy(xpath ="//android.widget.ImageView[@resource-id ='com.customerapp.harley:id/back_btn']")
	private WebElement back_button;
	@FindBy(xpath="//android.view.View[starts-with(@text,'Harley')]")
	private List<WebElement> privacy_policy;
	public WebElement terms_of_use()
	{
		return terms_of_use;
	}
	public WebElement privacy_Policy()
	{
		return privacy_Policy;
	}
	public void Terms_of_use_info()
	{
		for(int i=0;i<2;i++)
		{
		msg(Terms_of_use_info.get(i), Terms_of_use_info.get(i).getText());	
		}
	}
	public WebElement back_button()
	{
		return back_button;
	}
	public void privacy_policy_info()
	{
	for(int i=0;i<privacy_policy.size();i++)
	{
		msg(privacy_policy.get(i), privacy_policy.get(i).getText());
	}
	}
	//****************************************Login******************************************
	@FindBy(xpath="//android.widget.EditText[@resource-id='com.customerapp.harley:id/text_input_editext']")
	private WebElement Enter_mobile_no;
	@FindBy(xpath ="//android.widget.TextView[@resource-id ='com.customerapp.harley:id/btn_lbl']")
	private WebElement continue_button;
	@FindBy(xpath ="//android.widget.TextView[@resource-id='com.customerapp.harley:id/snackbar_text']")
	private WebElement Registered_message;
	@FindBy(xpath ="//android.widget.TextView[@resource-id ='com.customerapp.harley:id/user_no_lbl']")
	private WebElement mobile_no;
	@FindBy(xpath ="//android.widget.ImageView[@resource-id ='com.customerapp.harley:id/back_arrow']")
	private WebElement back_arrow;
		public WebElement Enter_mobile_no()
		{
			return Enter_mobile_no;
			
		}
		public WebElement continue_button()
		{
		return continue_button;
		}
		public WebElement Registered_message()
		{
			return Registered_message;
		}
		public WebElement mobile_no()
		{
			return mobile_no;
		}
		public WebElement back_arrow()
		{
			return back_arrow;
		}
//****************************************OTP page******************************************
	@FindBy(xpath ="//android.widget.TextView[@resource-id='com.customerapp.harley:id/lbl']")
	private WebElement enter_otp_message;
	@FindBy(xpath ="//android.widget.EditText[@resource-id ='com.customerapp.harley:id/otp_one_edtxt']")
	private WebElement otp_one;
	@FindBy(xpath ="//android.widget.EditText[@resource-id ='com.customerapp.harley:id/otp_two_edtxt']")
	private WebElement otp_two;

	@FindBy(xpath ="//android.widget.EditText[@resource-id ='com.customerapp.harley:id/otp_three_edtxt']")
	private WebElement otp_three;

	@FindBy(xpath ="//android.widget.EditText[@resource-id ='com.customerapp.harley:id/otp_four_edtxt']")
	private WebElement otp_four;

	@FindBy(xpath ="//android.widget.EditText[@resource-id ='com.customerapp.harley:id/otp_5_edtxt']")
	private WebElement otp_five;

	@FindBy(xpath ="//android.widget.EditText[@resource-id ='com.customerapp.harley:id/otp_6_edtxt']")
	private WebElement otp_six;

	@FindBy(className = "android.widget.EditText")
	private List<WebElement> OTP_field;
	@FindBy(xpath ="//android.widget.TextView[@resource-id ='com.customerapp.harley:id/resend_btn_lbl']")
	private WebElement resend_OTP;
	@FindBy(xpath ="//android.widget.TextView[@resource-id ='com.customerapp.harley:id/btn_lbl']")
	private WebElement Verify_button;
	@FindBy(xpath ="//android.widget.TextView[@resource-id='com.customerapp.harley:id/snackbar_text']")
	private WebElement OTP_response_message;
	public WebElement enter_otp_message()
	{
		return enter_otp_message;
	}
	public void invalid_OTP() throws AWTException
	{
			act.sendKeys(otp_one, ""+4).perform();
			act.sendKeys(otp_two, ""+4).perform();
			Message("OTP send successfully =4");
			act.sendKeys(otp_three, ""+6).perform();
			Message("OTP send successfully =6");
			act.sendKeys(otp_four, ""+7).perform();
			Message("OTP send successfully =7");
			act.sendKeys(otp_five, ""+8).perform();
			Message("OTP send successfully =8");
			act.sendKeys(otp_six, ""+9).perform();
			Message("OTP send successfully =9");
			act.sendKeys(otp_six, ""+5).perform();
			Message("OTP send successfully =5");
	}
	public void valid_OTP()
	{
		act.sendKeys(otp_one, ""+4).perform();
		act.sendKeys(otp_two, ""+4).perform();
		Message("OTP send successfully =4");
		act.sendKeys(otp_three, ""+2).perform();
		Message("OTP send successfully =2");
		act.sendKeys(otp_four, ""+4).perform();
		Message("OTP send successfully =4");
		act.sendKeys(otp_five, ""+2).perform();
		Message("OTP send successfully =2");
		act.sendKeys(otp_six, ""+4).perform();
		Message("OTP send successfully =4");
		act.sendKeys(otp_six, ""+3).perform();
		Message("OTP send successfully =3");
	}
	public WebElement resend_OTP()
	{
		return resend_OTP;
	}
	public WebElement Verify_button()
	{
		return Verify_button;
	}
	public WebElement OTP_response_message()
	{
		return OTP_response_message;
	}
}