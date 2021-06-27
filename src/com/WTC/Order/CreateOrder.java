package com.WTC.Order;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.opencsv.CSVReader;

public class CreateOrder {

  public static WebDriver driver;
  public static Properties prop;
  public static String[] csvCell;
  public static CSVReader csvReader;
  public static WebDriverWait wait = null;

  // webelements declaration
  static WebElement cookiesButton = null;
  static WebElement welcomePageCloseButton = null;
  static WebElement searchBar = null;
  static WebElement inputQuantity = null;
  static WebElement addProductButton = null;
  static WebElement viewCart = null;
  static WebElement viewCartdropdown = null;
  static WebElement checkoutButton1 = null;
  static WebElement continueAsGuestButton = null;
  static WebElement continueCheckoutButton2 = null;
  static WebElement userEmail = null;
  static WebElement UserName = null;
  static WebElement PhoneNumber = null;
  static WebElement shipping_CountryCode = null;
  static WebElement shipping_FirstName = null;
  static WebElement shipping_LastName = null;
  static WebElement shipping_Address1 = null;
  static WebElement shipping_PostalCode = null;
  static WebElement shipping_City = null;
  static WebElement continueCheckout = null;
  static WebElement cardNumber = null;
  static WebElement expiryDate = null;
  static WebElement securityCode = null;
  static WebElement NameOnCard = null;
  static WebElement agreementCheckbox = null;
  static WebElement submitOrderButton = null;
  // static WebElement cardNumber = null;
  static WebElement inVoice = null;

  public CreateOrder() {
    try {
      prop = new Properties();
      FileInputStream ip = new FileInputStream(
          "C:\\Users\\nkumar\\eclipse-workspace\\Hackathon\\src\\com\\WTC\\Order\\config.properties");
      prop.load(ip);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // @SuppressWarnings("deprecation")
  public static void main(String[] args) {
    // System.setProperty("webdriver.gecko.driver", "D:\\javaPractice\\selenium\\geckodriver.exe");
    // System.setProperty("webdriver.chrome.driver",
    // "D:\\javaPractice\\chromedriver_win32_v83\\chromedriver.exe");

    System.setProperty("webdriver.gecko.driver",
        "D:\\\\javaPractice\\\\selenium\\\\geckodriver.exe");
    // FirefoxOptions options = new FirefoxOptions();
    // String strFFBinaryPath = "D:\\javaPractice\\selenium\\geckodriver.exe";
    // options.setBinary(strFFBinaryPath);
    driver = new FirefoxDriver();


    // driver = new RemoteWebDriver(new URL("http://10.x.x.x:4444/wd/hub"), options);

    // Create a new instance of the Firefox driver
    // driver = new FirefoxDriver();
    // driver = new ChromeDriver();
    // driver = new FirefoxDriver();

    // Wait For Page To Load
    // Put a Implicit wait, this means that any search for elements on the page
    // could take the time the implicit wait is set for before throwing exception
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    // Navigate to URL
    driver.get("https://www-uat-edit.staples.no/");
    // Maximize the window.
    driver.manage().window().maximize();
    wait = new WebDriverWait(driver, 20);
    // CreateOrder.acceptCookies();
    // CreateOrder.addProductFromCSV();
  }

  // accept cookies when we open url
  public static void acceptCookies() {
    cookiesButton = driver.findElement(By.cssSelector("button.s-accept-all-cookies"));
    welcomePageCloseButton = driver.findElement(By.cssSelector("span.close"));

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    String mainWindow = driver.getWindowHandle();
    Set<String> allWindows = driver.getWindowHandles();
    for (String s : allWindows) {
      if (s != mainWindow) {
        driver.switchTo().window(s);
        // alert.accept();
        cookiesButton.click();
        welcomePageCloseButton.click();

        // acceptCookiesButton.click();
      }
    }
    driver.switchTo().window(mainWindow);
  }



  public static void addProductFromCSV() {

    FileReader fr = null;
    try {
      fr = new FileReader("D:\\javaPractice\\selenium\\WTCOrderForm.csv");
    } catch (FileNotFoundException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    BufferedReader brCinemas = new BufferedReader(fr);
    String cinemaLine;
    try {
      brCinemas.readLine();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      while ((cinemaLine = brCinemas.readLine()) != null) {
        System.out.println(cinemaLine);
        String[] orderInputs = cinemaLine.split(",");
        String userName = orderInputs[1];

        int length = orderInputs[2].length();
        String skus[] = ((String) orderInputs[2]).split("\\|");

        length = orderInputs[3].length();
        String prices[] = ((String) orderInputs[3]).split("\\|");

        List<String> skuList = Arrays.asList(skus);
        List<String> prList = Arrays.asList(prices);
        List<Integer> priceList = new ArrayList();
        for (int i = 0; i < prices.length; i++) {
          Integer pr = Integer.parseInt(prList.get(i));
          priceList.add(pr);
        }
        String email = orderInputs[4];
        String firstName = orderInputs[5];
        String secondName = orderInputs[6];
        String country = orderInputs[7];
        String AddressLine = orderInputs[8];
        String zipcode = orderInputs[9];
        String city = orderInputs[10];
        String phone = orderInputs[11];

        System.out.println(userName);
        System.out.println(firstName);
        System.out.println(secondName);
        System.out.println(country);
        System.out.println(AddressLine);
        System.out.println(zipcode);
        System.out.println(city);
        System.out.println(phone);

        System.out.println(skuList);
        System.out.println(prList);
        System.out.println(priceList);

        for (int i = 0; i < length - 1; i++) {
          searchBar = wait.until(
              ExpectedConditions.visibilityOfElementLocated(By.id("spls-header-search-bar-id")));

          searchBar.clear();
          searchBar.sendKeys(skuList.get(i));
          searchBar.submit();
          // CreateOrder.acceptCookies();

          inputQuantity = wait.until(ExpectedConditions
              .visibilityOfElementLocated(By.cssSelector("input[type='number']")));

          // driver.findElement(By.cssSelector("input[type='number']"));
          inputQuantity.clear();
          inputQuantity.sendKeys(prList.get(i));
          try {
            Thread.sleep(3000);
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          addProductButton =
              wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("addProduct")));

          addProductButton.click();
          try {
            Thread.sleep(3000);
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        viewCart = wait.until(ExpectedConditions
            .visibilityOfElementLocated(By.xpath("//span[contains(text(),'produkter')]")));
        viewCart.click();

        viewCartdropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("a.view-cart.btn.btn-primary.btn-lg.btn-block")));


        viewCartdropdown.click();
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

        checkoutButton1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("button.btn.btn-lg.btn-block.btn-primary.sticky-checkout")));
        checkoutButton1.click();

        continueAsGuestButton = wait.until(ExpectedConditions
            .visibilityOfElementLocated(By.xpath("//a[contains(text(),'CONTINUE AS GUEST')]")));

        continueAsGuestButton.click();
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

        continueCheckoutButton2 = wait.until(ExpectedConditions
            .visibilityOfElementLocated(By.xpath("//button[contains(text(),'Fortsett')]")));

        continueCheckoutButton2.click();
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

        userEmail = wait
            .until(ExpectedConditions.visibilityOfElementLocated(By.id("EmailSignupForm_Email")));
        userEmail.sendKeys(email);

        UserName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserName")));
        UserName.sendKeys(userName);

        PhoneNumber =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PhoneNumber")));
        PhoneNumber.sendKeys(phone);

        // shipping_CountryCode =
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shipping_CountryCode")));
        Select shipping_CountryCode = new Select(wait
            .until(ExpectedConditions.visibilityOfElementLocated(By.id("shipping_CountryCode"))));
        shipping_CountryCode.selectByIndex(1);

        try {
          Thread.sleep(5000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

        shipping_FirstName =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shipping_FirstName")));
        shipping_FirstName.sendKeys(firstName);

        shipping_LastName =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shipping_LastName")));
        shipping_LastName.sendKeys(secondName);

        shipping_Address1 =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shipping_Address1")));
        shipping_Address1.sendKeys(AddressLine);

        shipping_PostalCode =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shipping_PostalCode")));
        shipping_PostalCode.sendKeys(zipcode);

        shipping_City =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shipping_City")));
        shipping_City.sendKeys(city);

        continueCheckout =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("continueCheckout")));
        continueCheckout.click();

        try {
          Thread.sleep(5000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

        int countIframesInPage = driver.findElements(By.tagName("iframe")).size();
        System.out.println(countIframesInPage);
        driver.switchTo().frame(1);

        cardNumber =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("encryptedCardNumber")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("encryptedCardNumber")));
        // System.out.println(prop.getProperty("cardNo"));
        cardNumber.sendKeys(prop.getProperty("cardNo"));
        cardNumber.sendKeys("4111111111111111");
        cardNumber.sendKeys(Keys.TAB);
        cardNumber.sendKeys("03/30");


        expiryDate =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("encryptedExpiryDate")));
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[id='encryptedExpiryDate']")));

        // expiryDate.sendKeys(prop.getProperty("expirydt"));
        expiryDate.sendKeys("03/30");
        driver.switchTo().frame(1);
        securityCode =
            //
            wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//input[id='encryptedSecurityCode']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("encryptedSecurityCode")));
        // securityCode.sendKeys(prop.getProperty("cvv"));
        securityCode.sendKeys("123");

        NameOnCard = wait.until(ExpectedConditions
            .visibilityOfElementLocated(By.cssSelector("label.adyen-checkout__label")));
        // NameOnCard.sendKeys(prop.getProperty("nameOnCard"));
        NameOnCard.sendKeys("nik");
        // inVoice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
        // "//body/div[3]/div[1]/div[4]/form[1]/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/ul[1]/li[2]/div[1]")));
        // inVoice.click();

        driver.switchTo().defaultContent();

        agreementCheckbox = wait.until(ExpectedConditions
            .visibilityOfElementLocated(By.cssSelector("#terms-conditions-agree")));
        agreementCheckbox.click();

        submitOrderButton = wait.until(ExpectedConditions
            .visibilityOfElementLocated(By.cssSelector("//*[contains(text(),'Submit Order')]")));
        submitOrderButton.click();
        // JavascriptExecutor js = new JavascriptExecutor();

        // document.getElementById

      }
    } catch (NumberFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
}

