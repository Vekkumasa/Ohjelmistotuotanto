package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();   
    }
    
    @Given("user with username {string} with password {string} is successfully created")
    public void createUser(String username, String password) {
        newUserIsSelected();
        newUser(username, password, password);
    }
    
    @Given ("user with username {string} and password {string} is tried to be created")
    public void createUserWithBadCredentials(String username, String password) {
        newUserIsSelected();
        newUser(username, password, password);
    }
    
    
    @Given("new user is selected")
    public void newUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
    
    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void createNewUserWithValidCredentials(String username, String password) {
        newUser(username, password, password);
    }
    
    @When("invalid username {string} and password {string} and matching password confirmation are entered")
    public void createNewUserWithInvalidUsername(String username, String password) {
        newUser(username, password, password);
    }
    
    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }
  
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }
    
    @When("incorrect username {string} and password {string} are given")
    public void nonExistantUser(String username, String password) {
        logInWith(username, password);
    }
    
    @When("valid username {string} and too short password {string} is given")
    public void validUsernameTooShortPassword(String username, String password) {
        newUser(username, password, password);
    }
    
    @When("valid username {string} and valid password {string} are given but confirmation password does not match")
    public void validCredentialsButPasswordConfirmationDoesNotMatch(String username, String password) {
        newUser(username, password, "salasana");
    }
    
    @Then("user is not created and error \"password should have at least 8 characters\" is reported")
    public void tooShortPassword() {
        pageHasContent("password should have at least 8 characters");
    }
    
    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }

    @Then("a new user is created")
    public void userCreated() {
        pageHasContent("Welcome to Ohtu Application!");
    }
    
    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }    
    
    @When("username {string} and password {string} are given")
    public void usernameAndPasswordAreGiven(String username, String password) throws Throwable {
        logInWith(username, password);
    }   
    
    @Then("system will respond {string}")
    public void systemWillRespond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }
    
    @Then("user is not created and error \"username should have at least 3 characters\"")
    public void incorrectUsernameErrorMessageIsGiven() {
        pageHasContent("username should have at least 3 characters");
    }
    
    @Then("user is not created and error \"password and password confirmation do not match\" is reported")
    public void passwordAndConfirmationPasswordDoesNotMatch() {
        pageHasContent("password and password confirmation do not match");
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 
    
    private void newUser(String username, String password, String confirmationPassword) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(confirmationPassword);
        element.submit();
    }
}
