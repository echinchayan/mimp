package sisnara;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import tpido.pagina.sisnara.PaginaDerivar;
import tpido.pagina.sisnara.PaginaInicial;
import tpido.pagina.sisnara.PaginaMantenimientoML;
import tpido.pagina.sisnara.PaginaBandeja;

import java.util.Arrays;
import java.util.List;

public class PaginaInicialStep {
    @Managed
    WebDriver driver;

    PaginaInicial paginaInicial;
    PaginaMantenimientoML paginaMantenimientoML;
    PaginaBandeja paginaBandeja;
    PaginaDerivar paginaDerivar;

    @Before
    public void configuracionInicial() {
        //driver =  VisorDriver.getDriver(Navegador.CHROME);
        paginaInicial = new PaginaInicial(driver);
        //paginaRegistarUsuario = new PaginaRegistarUsuario(driver);
        paginaMantenimientoML = new PaginaMantenimientoML(driver);
        paginaBandeja = new PaginaBandeja(driver);
        paginaDerivar = new PaginaDerivar(driver);
    }

    @After
    public void cerrarDriver() {
        driver.quit();
    }

    @Given("cargamos la pagina SISNARA")
    public void cargarPaginaVisor() throws InterruptedException {
        paginaInicial.cargarPagina();
    }

/*
	@And("ingresamos al Menu")
	public void ingresamosMenu() {

		menuPrincipal.cargarPaginaMantenimientoPedido();

		}
*/

    @When("ingresamos al modulo EVALUACION, submodulo Evaluacion NNA")
    public void ingresarSubModuloEvaluacionNNA() throws InterruptedException {
         paginaMantenimientoML.ingresarSubModuloEvaluacionNNA();
    }


    @Then("debe validar {word} segun valor esperado {string}")
    public void debeValidarSegunValorEsperado(String elementos, String valor) throws InterruptedException{
        switch (elementos) {
            case "cabeceras":
                // split esperado
                List<String> esperado = Arrays.asList(valor.split(","));
                List<String> actual = paginaMantenimientoML.obtenerCabeceras();
                Assert.assertEquals(" Las cabeceras no coinciden", esperado, actual);
                break;

            default:
                throw new IllegalArgumentException("Elemento no reconocido: " + elementos);
        }
    }

    @Then("debe validar que cada fila tiene Bandeja de documentos y Derivar")
    public void debeValidarOpcionesGrilla() throws InterruptedException{
        Assert.assertTrue(" No todas las filas tienen ambas opciones",
                paginaMantenimientoML.validarOpcionesPorFila());
    }

    @When("busco el expediente {string} y abro su Bandeja de documentos")
    public void buscoExpedienteYAbroBandeja(String nroExpediente) throws InterruptedException {
        paginaMantenimientoML.seleccionarBandejaDeDocumentos(nroExpediente);
    }

    @Then("el nombre en la Bandeja debe coincidir con el de la lista")
    public void validarNombreEnBandeja() {
        String nombreLista = paginaMantenimientoML.getNombreSeleccionado();
        String textoBandeja = paginaBandeja.getNombreSeleccionado();

        Assert.assertEquals("El nombre no coincide entre lista y bandeja",
                nombreLista, textoBandeja);
    }

    @And("debe validar que liste documentos asociados")
    public void validarBandejaConElementos() {
        Assert.assertTrue("La bandeja no contiene elementos",
                paginaBandeja.tieneElementosEnBandeja());
    }

    @Then("validar que exista opcion Ficha NNA")
    public void validarBotonFichaNNA() {
        Assert.assertTrue("El botón Ficha NNA no está visible",
                paginaBandeja.existeBotonFichaNNA());
    }

    @And("cada elemento de la bandeja debe tener las opciones Editar, Descargar, Ver y Eliminar")
    public void validarOpcionesBandeja() {
        Assert.assertTrue("No todas las filas tienen las opciones requeridas",
                paginaBandeja.validarOpcionesPorFila());
    }

    @When("busco el expediente {string} y selecciono Derivar Expediente")
    public void buscoExpedienteYAbroDerivarExpediente(String nroExpediente) throws InterruptedException {
        System.out.println(nroExpediente);
        paginaMantenimientoML.seleccionarDerivarExpediente(nroExpediente);
    }

    @When("selecciono especialista {string} a derivar")
    public void seleccionoEspecialista(String especialista) throws InterruptedException {
        paginaDerivar.seleccionoEspecialista(especialista);
    }

    @When("selecciono derivar")
    public void seleccionoDerivar() throws InterruptedException {
        paginaDerivar.derivar();
    }

    @Then("validar que especialista seleccionado es igual al que lista del expediente {string}")
    public void validarEspecialistaDerivado(String nroExpediente){
        String responsable = paginaMantenimientoML.obtenerResponsablePorExpediente(nroExpediente);
        String especialista = paginaDerivar.getNombreSeleccionado().toUpperCase();;
        String[] partes = especialista.split("-");

        // Obtener la parte antes del guion y hacer trim
        String antesDelGuion = partes[0].trim();
        Assert.assertEquals("El nombre no coincide entre lista y bandeja",
                responsable, antesDelGuion);

    }
}
