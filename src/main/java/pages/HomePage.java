package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[text()='NEW CARS']")
    public WebElement newCars;

    public NewCarsPage findNewCar() {
        Actions actions = new Actions(driver);
        actions.moveToElement(newCars).perform(); // Hover

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement findNewCarsBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Find New Cars']")));

        findNewCarsBtn.click();
        return new NewCarsPage(driver);
    }


}
