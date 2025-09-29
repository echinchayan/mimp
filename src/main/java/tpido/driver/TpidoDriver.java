package tpido.driver;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TpidoDriver {
	
	public enum Navegador { CHROME , FIREFOX};

	
	public TpidoDriver() {
	}
	
	
	public static WebDriver getDriver(Navegador navegador) {
	if(navegador == Navegador.FIREFOX) {
		return getFirefoxDriver();
	}
	return getChromeDriver();
	}


	private static WebDriver getFirefoxDriver() {
		WebDriverManager.firefoxdriver().setup();
		return aplicarConfiguracion(new FirefoxDriver());
	}
	

	private static WebDriver getChromeDriver() {
		WebDriverManager.chromedriver().setup();
		return aplicarConfiguracion(new ChromeDriver());
	}

	private static WebDriver  aplicarConfiguracion(WebDriver driver) {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		return driver;
	}

}
