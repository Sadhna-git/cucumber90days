package steps;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;



public class Bigbasketsteps {
	public ChromeDriver driver;

@Given("User opens the Bigbasket application")
public void open_Bigbasket_application() throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	driver = new ChromeDriver();
	driver.get("https://www.bigbasket.com/");
	driver.manage().window().maximize();
	driver.findElement(By.xpath("//body")).sendKeys(Keys.chord(Keys.CONTROL, "r"));
	Thread.sleep(2000);
}

@And("mouse hover on  Shop by Category")
public void mousehoveronShopbyCategory() {
	Actions builder = new Actions(driver);
	WebElement shopbyCategory = driver.findElementByXPath("//a[text()=' Shop by Category ']");
	builder.moveToElement(shopbyCategory).perform();
}

@And ("Go to FOODGRAINS, OIL & MASALA and RICE & RICE PRODUCTS")
public void mousehoveronfoodGrainsandOil() throws InterruptedException {
	Actions builder1 = new Actions(driver);
	WebElement foodGrainsandOil = driver.findElementByXPath("(//a[text()='Foodgrains, Oil & Masala'])[2]");
	builder1.moveToElement(foodGrainsandOil).perform();
	Thread.sleep(2000);
	driver.findElementByXPath("(//a[text()='Rice & Rice Products'])[2]").click();
}

@And ("click on BOILED & STEAM RICE")
public void clickBoiledSteamrice() throws InterruptedException{
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,250)");
	Thread.sleep(2000);
	driver.findElementByXPath("(//span[text()='Boiled & Steam Rice'])[1]").click();
}

@And ("Choose the Brand as BB Royal")
public void ChooseBrandasbbRoyal() throws InterruptedException {
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	driver.findElementByXPath("(//input[@value='Search by Brand'])").sendKeys("bb Royal");
	driver.findElementByXPath("//span[text()='bb Royal']").click();
	Thread.sleep(5000);
}

@And  ("click add button of 10kg Ponni Boiled Rice")
public void clickaddbuttonof10kgPonniBoiledRice() throws InterruptedException {
	Thread.sleep(3000);
	driver.findElementByXPath("//div/a[text()='Ponni Boiled Rice - Super Premium']/../following-sibling::div[1]").click();
	List<WebElement> choosequantityofrice = driver.findElementsByXPath("(//ul[@class='dropdown-menu drop-select'])[3]/li");
	for (WebElement eachrice : choosequantityofrice) {
		if (eachrice.getText().contains("Ponni Boiled Rice")) {
			eachrice.click();
			System.out.println("choosing Ponni Boiled Rice");
			break;
		}
		
	}
	driver.findElementByXPath("(//button[text()='Add '])[4]").click();
	
}

@And ("Go to search box and type Dal")
public void GotosearchboxandtypeDal() throws InterruptedException {
	Thread.sleep(2000);
	driver.findElementById("input").sendKeys("Dal", Keys.ENTER);
	Thread.sleep(3000);
}

@And("Add ToorDal 2kg and set Qty2 from the list")
public void AddToorDal2kgandsetQty2fromthelist() throws InterruptedException {
	driver.findElementByXPath("//div/a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/../following-sibling::div[1]").click();
	List<WebElement> choosequantityofdal = driver.findElementsByXPath("(//ul[@class='dropdown-menu drop-select'])[1]/li");
	for (WebElement eachdal : choosequantityofdal) {
		System.out.println(eachdal.getText());
		if (eachdal.getText().contains("2 kg")) {
			eachdal.click();
			System.out.println("clicking 2kg");
			break;
		}
	}
	Thread.sleep(3000);
	WebElement quantitytextbox = driver.findElementByXPath("(//div[@class='input-group'])[2]/input");
	quantitytextbox.clear();
	quantitytextbox.sendKeys("2");
	driver.findElementByXPath("//button[@class='btn btn-add col-xs-9']").click();
}

@And("Select CHennai as City, Alandur-600016,Chennai and click Continue button")
public void clickAddress() throws InterruptedException { 
	Thread.sleep(5000);
	JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollTo(0, document.body.scrollTop)");
    Thread.sleep(4000);
	driver.findElementByXPath("//span[contains(@ng-bind, 'address_display_name')]").click();
	Thread.sleep(3000);
	driver.findElementByXPath("//span[@ng-bind='$select.selected']").click();
	driver.findElementByXPath("//input[@placeholder='Select your city']").sendKeys("Bangalore");
	driver.findElementByXPath("//input[@placeholder='Select your city']").clear();
	driver.findElementByXPath("//input[@placeholder='Select your city']").sendKeys("Coimbatore",Keys.ENTER);
	driver.findElementByXPath("//input[@id='areaselect']").sendKeys("641012 , Coimbatore",Keys.ENTER);
	Thread.sleep(3000);
	driver.findElementByXPath("//button[text()='Continue']").click();
	driver.findElementByXPath("//div[@id='city-drop-overlay']").click();
}

@And("Mouse hover on My Basket take a screen shot")
public void hoveroveronmybasket() throws IOException, InterruptedException {
	Actions builder = new Actions(driver);
	WebElement yourbasket = driver.findElementByXPath("//span[@title='Your Basket']");
	builder.moveToElement(yourbasket).perform();
	Thread.sleep(2000);
	File src = driver.getScreenshotAs(OutputType.FILE);
	File dst = new File("./src/screenshots/screenshot1.png");
	FileUtils.copyFile(src, dst);
}

@And("Click View Basket and Checkout")
public void clickViewBasketandCheckout() {
	driver.findElementByXPath("//button[text()='View Basket & Checkout']").click();
}

@And ("click close button and close browser")
public void clickclosebuttonandclosebrowser() {
	driver.findElementByXPath("//button[@class='close']").click();
	driver.close();
}
}