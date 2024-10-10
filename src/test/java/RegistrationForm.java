import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class RegistrationForm {

    WebDriver driver;

    @BeforeAll

    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test
    @DisplayName("Visit Url and check if title is showing properly")
    public void Registration(){
        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");



     List<WebElement>  forms =  driver.findElements(By.className("input-text"));
     //firstname
     forms.get(0).sendKeys("mehedi");
     //lastname
     forms.get(3).sendKeys("hasan");
     //email
     forms.get(1).sendKeys("mehedihasan4@gmail.com");
//gender
     List<WebElement> formsRadio = driver.findElements(By.className("input-radio"));
     formsRadio.get(0).click();
//password
     forms.get(2).sendKeys(("Z*123xc@/*-+#"));


//Date of birth

        String year = "2004";
     //   String month = "October";
        String day = "16";

       driver.findElement(By.xpath("//input[@class='ur-flatpickr-field regular-text without_icon flatpickr-input']")).click();
        //get year
        while (true){
            String currentYr = driver.findElement(By.className("cur-year")).getAttribute("value");
            if (currentYr.contains(year)){
                break;
            }
            driver.findElement(By.className("flatpickr-prev-month")).click();
        }
        //get month
        Select dropmonth = new Select(driver.findElement(By.className("flatpickr-monthDropdown-months")));
        dropmonth.selectByVisibleText("October");
        //get day
        List<WebElement> alldates = driver.findElements(By.xpath("//div[@class='dayContainer']//span"));
        for(WebElement dt:alldates){
            if (dt.getText().contains(day)){
                dt.click();
                break;
            }
        }


//Country
        forms.get(5).sendKeys("Bangladeshi");


//phoneNumber
      WebElement PhoneNumber = driver.findElement(By.cssSelector("[name=phone_1665627880]"));
      PhoneNumber.sendKeys("1644988757");

//country select

        WebElement countrySelector = driver.findElement(By.id("country_1665629257"));
        countrySelector.click();
        countrySelector.sendKeys("Bangladesh");
        countrySelector.sendKeys(Keys.ENTER);


        driver.findElement(By.cssSelector("[name=phone_1665627865]")).sendKeys("9652873524");



        //Date of Arrival
        Utils.scroll(driver, 500);
        List<WebElement> formsDate = driver.findElements(By.className("ur-flatpickr-field"));
       formsDate.get(1).click();

       List<WebElement> dateChart = driver.findElements(By.className("flatpickr-day"));
       dateChart.get(68).click();


//Intended Length of Stay
        forms.get(7).sendKeys("7 days");
 //Room and Bed Number
        forms.get(8).sendKeys("888, 1");
  //Occupation & Place of Employment
        forms.get(9).sendKeys("IT, Dhaka");
//Do you require parking ?
        formsRadio.get(3).click();

 //What is your room preference ?
        formsRadio.get(5).click();
 //Do you have dietary restriction ?
        formsRadio.get(8).click();
//What activities you will attend ?
        WebElement activitySelector = driver.findElement(By.id("select_1665628361"));
        activitySelector.click();
        activitySelector.sendKeys("A");
        activitySelector.sendKeys(Keys.ENTER);
//Terms and Conditions *
        driver.findElement(By.cssSelector("[name=privacy_policy_1665633140]")).click();


//submi
// driver.findElement(By.className("ur-submit-button")).click();

//
        List <WebElement> submitbtns = driver.findElements(By.cssSelector("[type=submit]"));
        submitbtns.get(2).click();

       String actuallResult = driver.findElement(By.xpath("//div[@id='ur-submit-message-node']//ul")).getText();
        Assertions.assertTrue(actuallResult.contains("User successfully registered."));














    }

  //  @AfterAll
    public void closeBrowser(){
        driver.quit();
    }

}
