import api.UserAccount;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import site.stellarburgers.AccountPage;
import site.stellarburgers.LoginPage;
import site.stellarburgers.MainPage;

import static com.codeborne.selenide.Selenide.*;

public class TestAccounts {
  LoginPage loginPage;
  MainPage mainPage;
  AccountPage accountPage;


  @Before
  public void setUpWebDriverAndRestAssured() {
//        чтобы запустить в Яндексе надо расскоментировать строку ниже
//        System.setProperty("webdriver.chrome.driver", "D://Yandex//bin//yandexdriver.exe");
    Configuration.startMaximized = true;
    RestAssured.baseURI = UserAccount.BASEURI;
  }

  @After
  public void deleteUser() {
    UserAccount.deleteUser(TestRegistrations.email, TestRegistrations.password);
    WebDriverRunner.closeWebDriver();
  }

  @DisplayName("Проверка успешного выхода из личного кабинета")
  @Test
  public void testLogOutFromAccount() {
    createUserLoginAndGotoAccount();
    accountPage = open(AccountPage.ACCOUNTPAGE_URL, AccountPage.class);
    accountPage.clickButtonLogOut();
    loginPage.waitButtonLoginIsVisible();
    Assert.assertEquals("https://stellarburgers.nomoreparties.site/login", webdriver().driver().getCurrentFrameUrl());
  }

  @DisplayName("Проверка перехода из личного кабинета по клику на иконку стеллар бургера в верхнем меню")
  @Test
  public void testLinkToMainPageFromLogoImageFromAccountPage() {
    createUserLoginAndGotoAccount();
    mainPage.clickLinkToMainPageFromLogoImage();
    Assert.assertEquals("https://stellarburgers.nomoreparties.site/", webdriver().driver().getCurrentFrameUrl());
  }

  @DisplayName("Проверка успешного перехода из личного кабинета по клику на Конструктор в верхнем меню")
  @Test
  public void testclickLinkToMainPageFromConstructorFromAccountPage() {
    createUserLoginAndGotoAccount();
    mainPage.clickLinkToMainPageFromConstructor();
    Assert.assertEquals("https://stellarburgers.nomoreparties.site/", webdriver().driver().getCurrentFrameUrl());
  }

  @Step("Создание юзера и вход под ним в Личный кабинет")
  public void createUserLoginAndGotoAccount() {
    UserAccount.CreateUser(TestRegistrations.name, TestRegistrations.email, TestRegistrations.password);
    mainPage = open(MainPage.MAINPAGE_URL, MainPage.class);
    mainPage.clickButtonSighIn();
    loginPage = page(LoginPage.class);
    loginPage.enterLoginEmailField(TestRegistrations.email);
    loginPage.enterLoginPasswordField(TestRegistrations.password);
    loginPage.clickButtonLogin();
    mainPage.clickLinkToAccount();
  }
}
