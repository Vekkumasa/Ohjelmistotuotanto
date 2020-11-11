package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Random;

public class Tester {

    public static void main(String[] args) {
        
        
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        sleep(2);
    //    wrongCredentials(driver);
    //    newUser(driver);
        newUserAndLogout(driver);
    }
    
    private static void newUserAndLogout(WebDriver driver) {
        Random random = new Random();
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        
        sleep(2);
        element = driver.findElement(By.name("username"));
        element.sendKeys("Jorma" + random.nextInt(10000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("Jorma");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("Jorma");
        
        element = driver.findElement(By.name("signup"));
        
        sleep(2);
        element.submit();
        
        sleep(1);
        
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        
        sleep(1);
        
        element = driver.findElement(By.linkText("logout"));
        element.click();
    }
    
    private static void newUser(WebDriver driver) {
        Random random = new Random();
        
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        
        sleep(2);
        element = driver.findElement(By.name("username"));
        element.sendKeys("Jorma" + random.nextInt(10000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("Jorma");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("Jorma");
        
        element = driver.findElement(By.name("signup"));
        
        sleep(2);
        element.submit();
    }
    
    private static void wrongCredentials(WebDriver driver) {
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

        sleep(3);
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
