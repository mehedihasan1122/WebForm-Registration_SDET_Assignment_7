import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WebForm {
    WebDriver driver;

@BeforeAll

    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
@Test
@Order(1)
    @DisplayName("Visit Url and check if title is showing properly")
    public void visitUrl(){
    driver.get("https://www.digitalunite.com/practice-webform-learners");
    String ActualTitle = driver.getTitle();
    //  System.out.println(ActualTitle);
    String expectedTitle = "Practice webform for learners | Digital Unite";
    Assertions.assertEquals(ActualTitle,expectedTitle);
    Assertions.assertTrue(ActualTitle.contains(expectedTitle));


    }
@Test
@Order(2)
    public void formFillUpAutomation() throws InterruptedException {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        driver.findElement(By.className("onetrust-close-btn-handler")).click();


        List<WebElement> forms = driver.findElements(By.className("form-control"));
        forms.get(0).sendKeys("MEHEDI HASAN");
        forms.get(1).sendKeys("01644988756");
        forms.get(2).sendKeys("08/10/2024");
        forms.get(3).sendKeys("mehedirayhan1122@gmail.com");

        Utils.scroll(driver,500);
        forms.get(4).sendKeys("This Is Mehedi Hasan from Dhaka,Bangladesh.");

        String relativePath ="\\src\\test\\resources\\MEHEDI_HASAN_1.pdf";

        String absolutePath = System.getProperty("user.dir")+relativePath;
        driver.findElement(By.className("form-file")).sendKeys(absolutePath);
        Thread.sleep(6000);

        driver.findElement(By.className("form-checkbox")).click();
        Thread.sleep(2000);

 //   driver.findElement(By.id("edit-submit")).click();


    driver.findElement(By.xpath("//input[@id='edit-submit']")).click();
    String actualResult = driver.findElement(By.id("block-pagetitle-2")).getText();
    Assertions.assertTrue(actualResult.contains("Thank you for your submission!"));



    }

   @AfterAll
    public void FinishTest(){
        driver.quit();
   }

}


