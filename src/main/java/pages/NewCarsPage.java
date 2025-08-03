package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class NewCarsPage extends BasePage {

	public NewCarsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//div[text() = 'Maruti Suzuki']")
	private WebElement maruti;
	@FindBy(xpath = "//div[text() = 'Mahindra']")
	private WebElement mahindra;
	@FindBy(xpath = "//div[text() = 'Tata']")
	private WebElement tata;
	@FindBy(xpath = "//div[text() = 'Hyundai']")
	private WebElement hyundai;
	@FindBy(xpath = "//div[text() = 'Toyota']")
	private WebElement toyota;
	@FindBy(xpath = "//div[text() = 'Kia']")
	private WebElement kia;
	@FindBy(xpath = "//div[text() = 'BMW']")
	private WebElement bmw;
	@FindBy(xpath = "//div[text() = 'Skoda']")
	private WebElement skoda;
	@FindBy(xpath = "//div[text() = 'Mercedes-Benz']")
	private WebElement mercedes;
	@FindBy(xpath = "//div[text() = 'MG']")
	private WebElement mg;
	@FindBy(xpath = "//div[text() = 'Land Rover']")
	private WebElement landrover;
	@FindBy(xpath = "//div[text() = 'Honda']")
	private WebElement honda;
	
	public void selectMarutiCar() {
		maruti.click();
	}
	
	public void selectMahindraCar() {
		mahindra.click();
	}
	
	public void selectTataCar() {
		tata.click();
	}
	
	public void selectToyotaCar() {
		toyota.click();
	}
	
	public void selectKiaCar() {
		kia.click();
	}
	
	public void selectBMWCar() {
		bmw.click();
	}
	
	public void selectSkodaCar() {
		skoda.click();
	}
	
	public void selectMercedesCar() {
		mercedes.click();
	}
	
	public void selectHyundaiCar() {
		hyundai.click();
	}
	
}
