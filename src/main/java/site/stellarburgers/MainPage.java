package site.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.commands.Click;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MainPage {
  public final static String MAINPAGE_URL = "https://stellarburgers.nomoreparties.site/";

  //локатор кнопки Войти в аккаунт, когда не авторизирован
  @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
  private SelenideElement buttonSighIn;
  //локатор  кнопки Личный кабинет
  @FindBy(how = How.XPATH, using = ".//p[text()='Личный Кабинет']")
  private SelenideElement linkToAccount;
  //локатор  иконки стелларбургер
  @FindBy(how = How.XPATH, using = ".//div/a[@href='/']")
  private SelenideElement linkToMainPageFromLogoImage;
  //локатор  иконки стелларбургер
  @FindBy(how = How.XPATH, using = ".//ul/li/a[@href='/']")
  private SelenideElement linkToMainPageFromConstructor;
  //локатор раздела Булки в конструкторе
  @FindBy(how = How.XPATH, using = ".//span[text()='Булки']")
  private SelenideElement sectionBunInConstructor;
  //локатор раздела Соусы в конструкторе
  @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
  private SelenideElement sectionSauceInConstructor;
  //локатор раздела Начинки в конструкторе
  @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']")
  private SelenideElement sectionFillingInConstructor;
  //Локатор выбранного раздела
  @FindBy(how = How.CLASS_NAME, using = "tab_tab_type_current__2BEPc")
  private SelenideElement selectedSectionInConstructor;



  @Step("Клик на кнопку Войти")
  public void clickButtonSighIn() {
    buttonSighIn.click();
  }

  @Step("Клик на линк Личный кабинет")
  public void clickLinkToAccount() {
    linkToAccount.click();
  }

  @Step("Клик на лого Стеллар Бургер")
  public void clickLinkToMainPageFromLogoImage() {
    linkToMainPageFromLogoImage.click();
  }

  @Step("Клик на Конструктор")
  public void clickLinkToMainPageFromConstructor() {
    linkToMainPageFromConstructor.click();
  }

  @Step("Клик на секцию Булки в Конструкторе")
  public void clickSectionBunInConstructor(){
    ;
    WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), 5);
    wait.until(ExpectedConditions.elementToBeClickable(sectionBunInConstructor));
    sectionBunInConstructor.click();
  }

  @Step("Клик на секцию Соусы в Конструкторе")
  public void clickSectionSauceInConstructor() {

    WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), 5);
    wait.until(ExpectedConditions.elementToBeClickable(sectionSauceInConstructor));
    sectionSauceInConstructor.click();
  }

  @Step("Клик на секцию Начинки в Конструкторе")
  public void clickSectionFillingInConstructor() {
    WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), 5);
    wait.until(ExpectedConditions.elementToBeClickable(sectionFillingInConstructor));
    sectionFillingInConstructor.click();
  }

  @Step("Проверка отображения хедера Начинки в списке ингридиентов")
  public boolean checkHeaderFillingSectionInConstructor() {
    return checkSelected("Начинки");
  }

  @Step("Проверка отображения хедера Булки в списке ингридиентов")
  public boolean checkHeaderBunSectionInConstructor(){
    return checkSelected("Булки");
  }

  @Step("Проверка отображения хедера Соусы в списке ингридиентов")
  public boolean checkHeaderSauceSectionInConstructor(){
    return checkSelected("Соусы");
  }

  private boolean checkSelected(String title) {
    selectedSectionInConstructor.shouldBe(Condition.exactText(title),Duration.ofSeconds(10));
    return selectedSectionInConstructor.getText().equals(title);
  }
}
