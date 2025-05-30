package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    public WebDriver driver;
    public LandingPage landingPage;
    public LoginPage loginPage;
    public RegistrationPage registrationPage;
    public BookPage bookPage;
    public CheckoutPage checkoutPage;
    public CartPage cartPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public LandingPage getLandingPage() {
        landingPage = new LandingPage(driver);
        return landingPage;
    }

    public LoginPage getLoginPage() {
        loginPage = new LoginPage(driver);
        return loginPage;
    }

    public RegistrationPage getRegistrationPage() {
        registrationPage = new RegistrationPage(driver);
        return registrationPage;
    }

    public BookPage getBookPage() {
        bookPage = new BookPage(driver);
        return bookPage;
    }

    public CheckoutPage getCheckoutPage() {
        checkoutPage = new CheckoutPage(driver);
        return checkoutPage;
    }

    public CartPage getCartPage(){
        cartPage = new CartPage(driver);
        return cartPage;
    }
}
