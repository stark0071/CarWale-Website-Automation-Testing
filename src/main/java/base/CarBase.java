package base;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CarBase {
	WebDriver driver;
	
	public CarBase(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h1[@class='o-js o-jJ o-j5 o-jl o-j6 o-jm ']")
	public WebElement carTitle;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div[3]/div/div/div[3]/div[1]//div/a/h3")
	public List<WebElement> carNames;
	
	@FindBy(xpath = "//*[@id=\"root\"]//div[3]//ul/li/div/div/div/div/div[3]/div/span/span[1]")
	public List<WebElement> carPrice;
	
	public ArrayList<String> list = new ArrayList<String>();
	 
	public String carTileMtd() {
		return carTitle.getText();
		
	}
	
	
	public void getCarNamesandPrices() {
		
		for (int i = 0; i < carNames.size() && i < carPrice.size() ; i++) {
			String text = carNames.get(i).getText()+" price is +" + carPrice.get(i).getText();
			list.add(text);
		}
		
		for (String li : list) {
			System.out.println(li);
		}

	}
	
	public void updateCarPriceInFile(String fileName) {

		String info = fileName + " car brand name and price\n";
		File file = new File(fileName);
		try {

			FileWriter fw = new FileWriter(file, true);

			// if you want to write the linesperator ("\n) as they are in the txt you should
			// use the following Code:

			fw.write(info);
			String lineSeparator = System.getProperty("line.separator");

			// instead you could only use:

			for (int i = 0; i < list.size(); i++) {
				fw.write(list.get(i));
				fw.write(lineSeparator);
			}

			fw.flush();
			fw.close();
			FileUtils.copyFile(file, new File(".//carprice//" + fileName + ".txt"));
			FileUtils.deleteQuietly(file);
		} catch (IOException e) {
			e.printStackTrace();

		}
      
	}
	
}
