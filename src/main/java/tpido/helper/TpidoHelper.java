package tpido.helper;

public class TpidoHelper {
	
	public static void pausar() {
		try {
			Thread.sleep(2000);
		}catch(InterruptedException exception) {
			throw new RuntimeException(exception);
		}
	}

}
