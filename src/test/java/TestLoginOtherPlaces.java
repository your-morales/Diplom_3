import api.UserAccount;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import site.stellarburgers.LoginPage;
import site.stellarburgers.MainPage;
import site.stellarburgers.PasswordPage;
import site.stellarburgers.RegisterPage;

import static com.codeborne.selenide.Selenide.*;

public class TestLoginOtherPlaces {

  LoginPage loginPage;
  MainPage mainPage;
  RegisterPage registerPage;
  PasswordPage forgotPasswordPage;

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

  @DisplayName("Проверка успешного перехода с главное страницы по кнопке Войти и успешный логин")
  @Test
  public void testLoginFromMainPage() {
    UserAccount.CreateUser(TestRegistrations.name, TestRegistrations.email, TestRegistrations.password);
    mainPage = open(MainPage.MAINPAGE_URL, MainPage.class);
    mainPage.clickButtonSighIn();
    loginPage = page(LoginPage.class);
    loginPage.enterLoginEmailField(TestRegistrations.email);
    loginPage.enterLoginPasswordField(TestRegistrations.password);
    loginPage.clickButtonLogin();
    mainPage.clickLinkToAccount();
    Assert.assertEquals("https://stellarburgers.nomoreparties.site/account", webdriver().driver().getCurrentFrameUrl());
  }

  @DisplayName("Проверка успешного перехода по линку Личный кабинет в верхнем меню и успешный вход в личный кабинет")
  @Test
  public void testLoginFromLinkToAccount() {
    UserAccount.CreateUser(TestRegistrations.name, TestRegistrations.email, TestRegistrations.password);
    mainPage = open(MainPage.MAINPAGE_URL, MainPage.class);
    mainPage.clickLinkToAccount();
    loginPage = page(LoginPage.class);
    loginPage.enterLoginEmailField(TestRegistrations.email);
    loginPage.enterLoginPasswordField(TestRegistrations.password);
    loginPage.clickButtonLogin();
    mainPage.clickLinkToAccount();
    Assert.assertEquals("https://stellarburgers.nomoreparties.site/account", webdriver().driver().getCurrentFrameUrl());
  }

  @DisplayName("Проверка успешного перехода по кнопке Войти со страницы регистрации и успешный вход в личный кабинет")
  @Test
  public void testLoginFromRegisterPage() {
    UserAccount.CreateUser(TestRegistrations.name, TestRegistrations.email, TestRegistrations.password);
    registerPage = open(RegisterPage.REGISTERPAGE_URL, RegisterPage.class);
    registerPage.clickOnLinkToLoginPage();
    loginPage = page(LoginPage.class);
    loginPage.enterLoginEmailField(TestRegistrations.email);
    loginPage.enterLoginPasswordField(TestRegistrations.password);
    loginPage.clickButtonLogin();
    mainPage = page(MainPage.class);
    mainPage.clickLinkToAccount();
    Assert.assertEquals("https://stellarburgers.nomoreparties.site/account", webdriver().driver().getCurrentFrameUrl());
  }

  @DisplayName("Проверка успешного перехода по кнопке Войти со страницы восстановить пароль и успешный вход в личный кабинет")
  @Test
  public void testLoginFromForgotPasswordPage() {
    UserAccount.CreateUser(TestRegistrations.name, TestRegistrations.email, TestRegistrations.password);
    forgotPasswordPage = open(PasswordPage.FORGOTPASSWORDPAGE_URL, PasswordPage.class);
    forgotPasswordPage.clickOnLinkToLoginPage();
    loginPage = page(LoginPage.class);
    loginPage.enterLoginEmailField(TestRegistrations.email);
    loginPage.enterLoginPasswordField(TestRegistrations.password);
    loginPage.clickButtonLogin();
    mainPage = page(MainPage.class);
    mainPage.clickLinkToAccount();
    Assert.assertEquals("https://stellarburgers.nomoreparties.site/account", webdriver().driver().getCurrentFrameUrl());
  }
}
