package tpido.pagina.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PaginaBase {
	
protected WebDriver driver;
	
	//protected MenuPrincipal menuPrincipal;
	
	public  PaginaBase(WebDriver driver) {
		this.driver = driver;
		//this.menuPrincipal = new MenuPrincipal(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	protected void escribir(WebElement elemento, String texto) {
		elemento.clear();
		elemento.sendKeys(texto);
	}
	
	
	protected void click(WebElement elemento) {
		elemento.click();
	}
	
    public void selectFromDropdownByIndex(WebElement locator, Integer index){
        Select dropdown = new Select(locator);
        dropdown.selectByIndex(index);
    }


    public void selectFromDropdownByValue(WebElement locator, String index){
        Select dropdown = new Select(locator);
        dropdown.selectByValue(index);
    }


}
