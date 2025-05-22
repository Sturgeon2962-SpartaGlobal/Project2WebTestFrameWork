package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.image.Kernel;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LandingPage {
    public WebDriver driver;

    public LandingPage(WebDriver driver){
        this.driver = driver;
    }

    private By loginBtn = By.xpath("//button[@class='mat-mdc-tooltip-trigger mdc-button mdc-button--unelevated mat-mdc-unelevated-button mat-primary mat-mdc-button-base ng-star-inserted']");
    private By searchBar = By.cssSelector(".mat-mdc-autocomplete-trigger");
    private By bookCards = By.cssSelector(".p-1.ng-star-inserted");
    private By noBookFound = By.cssSelector(".display-4");
    private By bookCard = By.xpath("/html/body/app-root/div/app-home/div/div[2]/div/div[1]/app-book-card/mat-card");
    private By addToCartBtn = By.xpath("/html/body/app-root/div/app-home/div/div[2]/div/div[1]/app-book-card/mat-card/mat-card-content/app-addtocart/button");
    private By cartIcon = By.xpath("/html/body/app-root/app-nav-bar/mat-toolbar/mat-toolbar-row/div[3]/button[2]");


    public void selectLoginBtn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        driver.findElement(loginBtn).click();
    }

    public void searchTitle(String title) throws InterruptedException {
        driver.findElement(searchBar).sendKeys(title);
        Thread.sleep(500);
        driver.findElement(searchBar).sendKeys(Keys.ENTER);

    }

    public List<String> getAllBooks() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(bookCards));

        List<WebElement> books = driver.findElements(bookCards);
        List<String> bookTitles = new ArrayList<>();

        for (int i = 0; i < books.size(); i++) {
            try {
                WebElement book = driver.findElements(bookCards).get(i);
                bookTitles.add(book.getText().toLowerCase());
            } catch (StaleElementReferenceException e) {
                WebElement book = driver.findElements(bookCards).get(i);
                bookTitles.add(book.getText().toLowerCase());
            }
        }

        return bookTitles;
    }

    public String getErrorMsg() {
        return driver.findElement(noBookFound).getText();
    }

    public void clickOnBookCard() {
        driver.findElement(bookCard).click();
    }

    public void addBookToCart() throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(addToCartBtn).click();
    }

    public void clickOnCartIcon() {
        driver.findElement(cartIcon).click();
    }
}
