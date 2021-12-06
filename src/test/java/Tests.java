import Model.Pages.CustomerStartPage;
import framework.driver.BaseDriver;
import framework.driver.WebDrivers;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.net.URL;

public class Tests {

    private BaseDriver baseDriver;
    private CustomerStartPage testedPage;

    @BeforeSuite
    public void BeforeSuite() {

        File resourcesDirectory = new File("src/main/resources");
        String path = resourcesDirectory.getAbsolutePath();


        baseDriver = new BaseDriver(WebDrivers.CHROME, 5);


        // Тут найде чтобы с тянуло урл с ресорс
        testedPage = new CustomerStartPage(baseDriver,  path + "\\ui\\index.html");
        baseDriver.maximize();
    }


    @BeforeTest
    public void BeforeTest() {
        testedPage.openUrl();
    }


    @Test
    public void UserIsAbleToSearchByName() {
        //act
        String searchRequest = "Bon";
        testedPage.search().clearString().sendString(searchRequest);
        //arrange
        String searchName = testedPage.customerTable().rows().get(0).cells().get(1).visibleText();
        //assert
        Assert.assertTrue(searchName.contains(searchRequest));
    }

    @Test
    public void UserIsAbleToSearchByNameMatchCase() {
        //act
        String searchRequest = "bon";
        testedPage.search().clearString().sendString(searchRequest);
        testedPage.checkbox().check();
        //arrange
        String tableResult = testedPage.tableResume().visibleText();
        //assert
        Assert.assertTrue(tableResult.startsWith("Showing 0"));
    }

    @Test
    public void UserIsAbleToSearchCustomerByCity() {
        //act
        String searchCity = "Belfast";
        testedPage.search().clearString().sendString(searchCity);
        testedPage.dropDown().selectByText("City");
        //arrange
        String tableCityResult = testedPage.customerTable().rows().get(0).cells().get(3).visibleText();
        //assert
        Assert.assertTrue(tableCityResult.contains(searchCity));
    }



    @AfterTest
    public void AfterTest() {

    }

    @AfterSuite
    public void AfterSuite() {
        baseDriver.getWebDriver().close();
    }
}
