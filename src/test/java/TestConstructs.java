import api.UserAccount;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import site.stellarburgers.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class TestConstructs {
  MainPage mainPage;


  @Before
  public void setUpWebDriverAndRestAssured() {
//        чтобы запустить в Яндексе надо расскоментировать строку ниже
//        System.setProperty("webdriver.chrome.driver", "D://Yandex//bin//yandexdriver.exe");

    RestAssured.baseURI = UserAccount.BASEURI;
    mainPage = open(MainPage.MAINPAGE_URL, MainPage.class);

  }

  @After
  public void deleteUser() {
    WebDriverRunner.closeWebDriver();
  }

  @DisplayName("Проверка успешного перехода на раздел Булки")
  @Test
  public void testSuccessMoveToBunSection() throws InterruptedException {
    mainPage.clickSectionSauceInConstructor();

    mainPage.clickSectionBunInConstructor();
    boolean checkIsDisplayed = mainPage.checkHeaderBunSectionInConstructor();
    Assert.assertEquals(true, checkIsDisplayed);
  }

  @DisplayName("Проверка успешного перехода на раздел Соусы")
  @Test
  public void testSuccessMoveToSauceSection() throws InterruptedException {
    mainPage.clickSectionFillingInConstructor();
    mainPage.clickSectionSauceInConstructor();
    boolean checkIsDisplayed = mainPage.checkHeaderSauceSectionInConstructor();
    Assert.assertEquals(true, checkIsDisplayed);
  }

  @DisplayName("Проверка успешного перехода на раздел Начинки")
  @Test
  public void testSuccessMoveToFillingSection() throws InterruptedException {
    mainPage.clickSectionFillingInConstructor();
    boolean checkIsDisplayed = mainPage.checkHeaderFillingSectionInConstructor();
    Assert.assertEquals(true, checkIsDisplayed);
  }
}
