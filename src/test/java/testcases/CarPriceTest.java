package testcases;

import org.testng.annotations.Test;

import base.BasePage;
import base.BaseTest;
import pages.HomePage;
import utilities.DataUtil;

public class CarPriceTest extends BaseTest {

	@Test(dataProviderClass = DataUtil.class,dataProvider = "dp")
	public void findCar(String brand,String browserName) {
		setUp(browserName);
		if (brand.equalsIgnoreCase("Maruti")) {
			HomePage home = new HomePage(driver);
			home.findNewCar().selectMarutiCar();
			System.out.println(BasePage.car.carTileMtd());
			BasePage.car.getCarNamesandPrices();
			BasePage.car.updateCarPriceInFile(brand);
			
		}else if (brand.equalsIgnoreCase("Mahindra")) {
			HomePage home = new HomePage(driver);
			home.findNewCar().selectMahindraCar();
			System.out.println(BasePage.car.carTileMtd());
			BasePage.car.getCarNamesandPrices();
			BasePage.car.updateCarPriceInFile(brand);
			
		}else if (brand.equalsIgnoreCase("Tata")) {
			HomePage home = new HomePage(driver);
			home.findNewCar().selectTataCar();
			System.out.println(BasePage.car.carTileMtd());
			BasePage.car.getCarNamesandPrices();
			BasePage.car.updateCarPriceInFile(brand);
			
		}else if (brand.equalsIgnoreCase("Hyundai")) {
			HomePage home = new HomePage(driver);
			home.findNewCar().selectHyundaiCar();
			System.out.println(BasePage.car.carTileMtd());
			BasePage.car.getCarNamesandPrices();
			BasePage.car.updateCarPriceInFile(brand);
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
