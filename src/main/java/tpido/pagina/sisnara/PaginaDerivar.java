package tpido.pagina.sisnara;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tpido.pagina.base.PaginaBase;

import java.time.Duration;
import java.util.List;

public class PaginaDerivar extends PaginaBase {

    // id Nombre
    @FindBy(xpath = "//input[@id='specialist']/ancestor::div[contains(@class, 'p-dropdown')]//div[@role='button' and contains(@class, 'p-dropdown-trigger')]")
    WebElement txtEspecialista;

    @FindBy(xpath = "//input[contains(@class, 'p-dropdown-filter')]")
    WebElement txtFiltro;

    @FindBy(xpath = "//ul[contains(@class, 'p-dropdown-items')]//p-dropdownitem[1]/li']")
    WebElement opcionesDropdown;

    @FindBy(xpath = "//p-button//button[.//span[text()='Derivar']]")
    WebElement btnDerivar;

    private String nombreSeleccionado;

    public PaginaDerivar(WebDriver driver) {
        super(driver);
    }

    public void seleccionoEspecialista(String especialista) throws InterruptedException {
        nombreSeleccionado=especialista;
        click(txtEspecialista);
        // Paso 2: Esperar que aparezcan las opciones (puede tardar un poco)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'p-dropdown-items-wrapper')]")));
        Thread.sleep(1000);
        escribir(txtFiltro,especialista);
        Thread.sleep(1000);

        WebElement primerElemento = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//ul[contains(@class, 'p-dropdown-items')]//li[1]")
        ));
        primerElemento.click();
        Thread.sleep(1000);


    }

    public void derivar() throws InterruptedException {

        click(btnDerivar);
        Thread.sleep(2000);
    }

    public String getNombreSeleccionado() {
        return nombreSeleccionado;
    }
}
