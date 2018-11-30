package colaborar;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// VARIABLES PARA PASAR AL SEGUNDO PROGRAMA LOS ARGUMENTOS
		int numinstancias = 0;
		int chars;
		int words = 0;
		String paths = "";
		String files = "";

		// CREAMOS SCANNERS
		Scanner instanint = new Scanner(System.in);
		Scanner instanstr = new Scanner(System.in);

		// PEDIMOS DATOS
		System.out.println("¿Cuantos PROCESOS vamos a lanzar? (DEFAULT 5)");
		numinstancias = instanint.nextInt();

		System.out.println("¿Cuantos chars por palabra quieres?");
		chars = instanint.nextInt();

		System.out.println("¿Y cuantas palabras por proceso?");
		words = instanint.nextInt();

		System.out.println("¿En qué ruta (COMPLETA) quieres guardar el fichero?");
		paths = instanstr.nextLine();

		System.out.println("¿Cómo se va a llamar tu fichero?");
		files = instanstr.nextLine();

		// DETECCION DE SISTEMA OPERATIVO Y GUARDAMOS EN FLAG
		int Sys = 1;

		String sistema = System.getProperty("os.name").toLowerCase();
		if (sistema.indexOf("win") >= 0) {
			System.out.println("\nSe ha Detectado SISTEMA OPERATIVO WINDOWS");
			Sys = 1;
		} else if (sistema.indexOf("mac") >= 0) {
			System.out.println("\nSe ha Detectado SISTEMA OPERATIVO MAC/OS");
			Sys = 0;
		} else if (sistema.indexOf("nix") >= 0 || sistema.indexOf("nux") >= 0) {
			System.out.println("\nSe ha Detectado SISTEMA OPERATIVO UNIX/LINUX");
			Sys = 0;
		} else {
			System.out.println("\nADVERTENCIA: No se detecta un sistema operativo soportado!!");
		}

		// SEGUN EL SISTEMA SEA WIN O BIEN OTRO COMO LINUX O MAC
		try {

			if (args.length > 0) {
			     numinstancias = Integer.parseInt(args[0]);
		      }else {
		    	  numinstancias=5;
		      }
			
			// BUCLE QUE DETERMINA EL NUMERO DE PROCESOS QUE VAMOS A LANZAR
			while (numinstancias > 0 ) {
			
				if (Sys == 1) {
					System.out.println("java -jar lenguaje.jar " +chars+" "+words+" "+paths+" "+files );
					
				//ESTE COMANDO LO IMPRIME CORRECTAMENTE					
					String cmd = "cmd /c \"java -jar lenguaje.jar " +chars+" "+words+" "+paths+" "+files ; // AUTOPATH WINDOWS
					Runtime.getRuntime().exec(cmd);
					
					// NO SE PORQUE NO "COGE" ESTE COMANDO...
					
				} else {
					// TESTEAR Y CREAR PARTE LINUX/OSX
					String[] cmd = { "java -jar lenguaje.jar " + chars + " " + words + " " + paths+" "+files };
					Runtime.getRuntime().exec(cmd);
				}
				
				numinstancias--; 
				}

				// CERRAMOS SCANNERS
				instanint.close();
				instanstr.close();
				System.exit(1);

			
			// GESTION DE EXCEPCIONES
			
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Este jar debe estar al lado del jar llamado lenguaje...");
		} catch (IllegalArgumentException e) {
			System.err.println("Los argumentos del programa deben estar correctos");
			System.err.println(e.toString());
		} catch (IOException e) {
			System.err.println("Error de I/O");
			System.err.println(e.toString());
		} catch (NoSuchElementException e) {
			System.err.println("No existe la línea o el elemento");
			System.err.println(e.toString());
		} catch (Exception e) {
			System.err.println("Error genérico");
			System.err.println(e.toString());
		}

	}

}
