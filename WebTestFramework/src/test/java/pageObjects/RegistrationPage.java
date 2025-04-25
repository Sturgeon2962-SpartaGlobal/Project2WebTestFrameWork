package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    public WebDriver driver;
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private By firstNameInput = By.xpath("//mat-form-field[1]/div[1]/div/div[2]/input");
    private By lastNameInput = By.xpath("//mat-form-field[2]/div[1]/div/div[2]/input");
    private By userNameInput = By.xpath("//mat-form-field[3]/div[1]/div/div[2]/input");
    private By passwordInput = By.xpath("//mat-form-field[4]/div[1]/div/div[2]/input");
    private By confirmPasswordInput = By.xpath("//mat-form-field[5]/div[1]/div/div[2]/input");
    private By maleRadioBtn = By.xpath("//mat-radio-group/mat-radio-button[1]/div/div/input");
    private By femaleRadioBtn = By.xpath("//mat-radio-group/mat-radio-button[2]/div/div/input");
    private By submitForm = By.xpath("//form/mat-card-actions/button");
    private By userNameErrorMsg = By.xpath("//mat-form-field[3]/div[2]/div/mat-error");

    public void CompleteForm(String firstName, String lastName, String userName, String password1, String password2, String gender) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(userNameInput).sendKeys(userName);
        driver.findElement(passwordInput).sendKeys(password1);
        driver.findElement(confirmPasswordInput).sendKeys(password2);
        if (gender.equalsIgnoreCase("Male")) {
            driver.findElement(maleRadioBtn).click();
        } else if (gender.equalsIgnoreCase("Female")) {
            driver.findElement(femaleRadioBtn).click();
        }
    }

    public void SubmitForm() {
        driver.findElement(submitForm).click();
    }

    public String GetUserNameErrorMsg(){
        return driver.findElement(userNameErrorMsg).getText();
    }
}
