package tpido.pagina.sisnara;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tpido.pagina.base.PaginaBase;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class PaginaBandeja extends PaginaBase {

    @FindBy(xpath = "//*[@class='p-fieldset-legend-text ng-tns-c4234210556-187 ng-star-inserted']")
    WebElement txtTituloPagina;

    // id Nombre
    @FindBy(id = "nna_name")
    WebElement txtNombreNNA;

    @FindBy(css = "table.p-datatable-table")
    WebElement tblTablaBandeja;

    @FindBy(css = "table.p-datatable-table tbody tr")
    List<WebElement> filasBandeja;

    @FindBy(xpath = "//button[@label='Ficha NNA']")
    WebElement btnFichaNNA;

    public PaginaBandeja(WebDriver driver) {
        super(driver);
    }

    public String getNombreSeleccionado() {
        return txtNombreNNA.getAttribute("value").trim();
    }

    public boolean tieneElementosEnBandeja() {
        // Basta con validar que haya al menos una fila con celdas
        for (WebElement fila : filasBandeja) {
            List<WebElement> celdas = fila.findElements(By.tagName("td"));
            if (!celdas.isEmpty()) {
                return true; // ✅ hay datos
            }
        }
        return false;
    }

    public boolean existeBotonFichaNNA() {
        try {
            return btnFichaNNA.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean validarOpcionesPorFila() {
        for (WebElement fila : filasBandeja) {
            List<WebElement> celdas = fila.findElements(By.tagName("td"));
            WebElement celdaOpciones = celdas.get(celdas.size() - 1);

            boolean tieneEditar = !celdaOpciones.findElements(By.xpath(".//p-button[@icon='pi pi-pencil']")).isEmpty();
            boolean tieneDescargar = !celdaOpciones.findElements(By.xpath(".//p-button[@icon='pi pi-file-pdf']")).isEmpty();
            boolean tieneVer = !celdaOpciones.findElements(By.xpath(".//p-button[@icon='pi pi-eye']")).isEmpty();
            boolean tieneEliminar = !celdaOpciones.findElements(By.xpath(".//p-button[@icon='pi pi-trash']")).isEmpty();

            if (!(tieneEditar && tieneDescargar && tieneVer && tieneEliminar)) {
                return false;
            }
        }
        return true;
    }

}
