package framework.elements;

import framework.driver.BaseDriver;
import framework.elements.tableElements.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Table extends BaseElement {

private static By rowsLocator = By.xpath(//table[@class = 'table table-hover']");

    public Table(BaseDriver baseDriver, WebElement webElement, By locator) {
        super(baseDriver, webElement, locator);
    }

    public List<Row> rows(){
        return findElements(Row.class, rowsLocator);
    }
}
