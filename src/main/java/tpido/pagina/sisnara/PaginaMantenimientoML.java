package tpido.pagina.sisnara;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tpido.helper.TpidoHelper;
import tpido.pagina.base.PaginaBase;

import java.sql.DriverManager;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class PaginaMantenimientoML extends PaginaBase {


    @FindBy(xpath = "//*[@class='p-ripple p-element ng-tns-c821862991-17 ng-star-inserted']")
    WebElement btnMenuModuloEvaluacion;

    @FindBy(xpath = "//*[@class='layout-menuitem-text ng-tns-c821862991-18']")
    WebElement btnSubModuloEvaluacionNNA;

    // Localizamos directamente la tabla
    @FindBy(xpath  = "//table[substring(@id, string-length(@id) - string-length('-table') +1) = '-table']")
    WebElement tblListaEvaluacionNNA;

    // Localiza todas las filas del body de la tabla
    @FindBy(xpath = "//table[substring(@id, string-length(@id) - string-length('-table') +1) = '-table']//tbody/tr")
    List<WebElement> filasTabla;

    // Variable para almacenar el nombre del expediente seleccionado
    private String nombreSeleccionado;

    public PaginaMantenimientoML(WebDriver driver) {
        super(driver);
    }


    // Método para obtener todas las cabeceras de la tabla
    public List<String> obtenerCabeceras() {
        List<WebElement> cabeceras = tblListaEvaluacionNNA.findElements(
                org.openqa.selenium.By.cssSelector("thead th")
        );

        return cabeceras.stream()
                .map(e -> e.getText().trim())
                .collect(Collectors.toList());
    }

    public void ingresarSubModuloEvaluacionNNA() throws InterruptedException{
        TpidoHelper.pausar();
        click(btnMenuModuloEvaluacion);
        Thread.sleep(1000);
        click(btnSubModuloEvaluacionNNA);
        Thread.sleep(2000);
    }

    public boolean validarOpcionesPorFila() {
        for (WebElement fila : filasTabla) {
            WebElement celdaOpciones = fila.findElement(By.cssSelector("td:last-child"));
            // Desplaza hasta la celda para que salga en el screenshot
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", celdaOpciones);

            boolean tieneBandeja = !celdaOpciones.findElements(By.cssSelector(".pi.pi-file")).isEmpty();
            boolean tieneDerivar = !celdaOpciones.findElements(By.cssSelector(".pi.pi-reply")).isEmpty();

            if (!tieneBandeja || !tieneDerivar) {
                return false; // si alguna fila no cumple, cortamos
            }
        }
        return true; // todas las filas cumplen
    }

    /**
     * Busca el expediente en la tabla, guarda el nombre y hace clic en Bandeja de documentos.
     */

    public void seleccionarBandejaDeDocumentos(String nroExpediente) throws InterruptedException {
        for (WebElement fila : filasTabla) {
            List<WebElement> celdas = fila.findElements(By.tagName("td"));
            String expediente = celdas.get(2).getText().trim(); // Columna Nro Expediente (0-based index)

            if (expediente.equalsIgnoreCase(nroExpediente)) {
                // Guardar Nombres y Apellidos
                String nombre = celdas.get(3).getText().trim();
                nombreSeleccionado = nombre; // o guárdalo en una variable de tu clase

                // Click en el botón "Bandeja de documentos"
                WebElement celdaOpciones = celdas.get(9); // Última columna
                WebElement botonBandeja = celdaOpciones.findElement(By.cssSelector("button .pi.pi-file"));
                botonBandeja.click();
                Thread.sleep(1000);
                break;
            }
        }
    }

    /**
     * Devuelve el nombre almacenado de la fila seleccionada
     */
    public String getNombreSeleccionado() {
        return nombreSeleccionado;
    }

    public void seleccionarDerivarExpediente(String nroExpediente) throws InterruptedException {

        for (WebElement fila : filasTabla) {
            List<WebElement> celdas = fila.findElements(By.tagName("td"));
            String expediente = celdas.get(2).getText().trim(); // Columna Nro Expediente (0-based index)
            if (expediente.equalsIgnoreCase(nroExpediente)) {
                // Guardar Nombres y Apellidos
                String nombre = celdas.get(3).getText().trim();
                nombreSeleccionado = nombre; // o guárdalo en una variable de tu clase
                // Click en el botón "Bandeja de documentos"
                WebElement celdaOpciones = celdas.get(9); // Última columna
                System.out.println(celdaOpciones.toString());
                WebElement botonDerivar = celdaOpciones.findElement(By.xpath("//p-button[@ptooltip='Derivar Expediente']//button"));
                new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(botonDerivar));
                botonDerivar.click();

                Thread.sleep(2000);
                break;
            }
        }
    }

    public String obtenerResponsablePorExpediente(String nroExpediente) {
        for (WebElement fila : filasTabla) {
            List<WebElement> celdas = fila.findElements(By.tagName("td"));
            String expediente = celdas.get(2).getText().trim(); // Columna Nro Expediente (0-based index)

            if (expediente.equalsIgnoreCase(nroExpediente)) {
                // Guardar Nombres y Apellidos
                String nombre = celdas.get(7).getText().trim();

                nombreSeleccionado = nombre; // o guárdalo en una variable de tu clase
                return nombreSeleccionado;
            }
        }
        return "NA";
    }
}
