package com.juaracoding.traveltest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class AppTest {

    private WebDriver driver;
    //private String registeredUsername = "BudiSoesanto";

    @BeforeMethod
    public void beforeMethod() {
    driver = new FirefoxDriver();
    driver.get("https://demo.guru99.com/test/newtours/register.php");
  }

   @Test
    public void registerUserTest() {

        String firstName = "Budi";
        String lastName = "Soesanto";
        String fullName = "Budi Soesanto";
        String username = "budisoe@test.com";

        driver.findElement(By.name("firstName")).sendKeys(firstName);
        driver.findElement(By.name("lastName")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("1234567890");
        driver.findElement(By.name("userName")).sendKeys(username);
        driver.findElement(By.name("address1")).sendKeys("Jl. Testing Address");
        driver.findElement(By.name("city")).sendKeys("JAKARTA");
        driver.findElement(By.name("state")).sendKeys("INDONESIA");
        driver.findElement(By.name("postalCode")).sendKeys("12250");

        WebElement countryDropdownElement = driver.findElement(By.name("country"));
        Select countryDropdown = new Select(countryDropdownElement);
        countryDropdown.selectByVisibleText("INDONESIA");

        driver.findElement(By.name("email")).sendKeys("Budi Soesanto");
        driver.findElement(By.name("password")).sendKeys("password123");
        driver.findElement(By.name("confirmPassword")).sendKeys("password123");

        driver.findElement(By.name("submit")).click();

        // Verifikasi URL untuk memastikan registrasi berhasil
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://demo.guru99.com/test/newtours/register_sucess.php";
        Assert.assertEquals(actualUrl, expectedUrl, "Registrasi gagal, tidak diarahkan ke halaman sukses.");

        WebElement actual2 = driver.findElement(By.xpath("//b[contains(text(),'Dear ')]"));
        String actualDearMessage = actual2.getText();

        WebElement actual3 = driver.findElement(By.xpath("//b[contains(text(),'Note: Your user name is ')]"));
        String actualNoteMesssage = actual3.getText();

        // Bangun pesan yang diharapkan (expected) 
        String expectedDearMessage = "Dear " + firstName + " " + lastName + ",";
        String expectedNoteMessage = "Note: Your user name is " + fullName + ".";

        Assert.assertEquals(actualDearMessage, expectedDearMessage, "Pesan 'Dear' tidak sesuai.");
        Assert.assertEquals(actualNoteMesssage, expectedNoteMessage, "Pesan 'Note' tidak sesuai.");

    }

  @AfterMethod
    public void closebrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}

