package tpido.pagina.sisnara;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tpido.pagina.base.PaginaBase;

public class PaginaInicial extends PaginaBase {

    @FindBy(id = "username")
    WebElement txtUsername;

    @FindBy( id = "password")
    WebElement txtPassword;

    @FindBy( id = "kc-login")
    WebElement btnIniciarSesion;

    @FindBy(id = "j_idt16_header")
    WebElement lblMnesajeBienvenida;

    public PaginaInicial(WebDriver driver) {
        super(driver);
    }

    public void cargarPagina() throws InterruptedException {

        driver.get("https://dadopciones.mimp.gob.pe/");
        escribir(txtUsername,"SISNARA_ADMINISTRADOR_LOPEZ");
        Thread.sleep(1000);
        escribir(txtPassword,"MIMP_SISNARA_p1");
        Thread.sleep(1000);
        click(btnIniciarSesion);
        Thread.sleep(2000);
    }



    public String getMensajeBienvenida() {
        return lblMnesajeBienvenida.getText();
        //return driver.findElement(By.xpath("//div[@class='title-main']")).getText();
    }
}
