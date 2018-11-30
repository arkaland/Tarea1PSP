package lenguaje;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		/*************************************************************************
		 *  NOTA RAPIDA: ARGUMENTOS VALIDOS ES 4
		 *  MAX ARGS = 4
		 *  
		 *  Se puede dar las siguientes combinaciones de argumentos:
		 *  
		 *  1 charnumber wordnumber path filename
		 *  2 charnumber wordnumber path
		 *  3 charnumber wordnumber
		 *  4 charnumber
		 *  5 -
		 *  
		 *  No se admitirá ninguna otra opción para los argumentos
		 *  
		 *  Syntax:
		 *  java -jar [numcaracteres] [numpalabras] (ruta) (fichero)]
		 * 
		 *************************************************************************/
				
			/*
		// COMPROBAMOS SI ESTAMOS RECIBIENDO ARGUMENTOS DE LA LINEA DE COMANDOS
		if (args.length > 0) {
			int orden = Integer.parseInt(args[0]);
			}
			*/
		
			System.out.printf("Generación de palabras\n\n");

			// VARIABLES

			int numcaracteres=-1; // NUMERO DE CARACTERES POR PALABRA QUE EL USUARIO QUIERE
			int numpalabras=-1; // EL NUMERO DE PALABRAS QUE EL USUARIO QUIERE
			String ruta = "" ;   // LA RUTA COMPLETA EN LA QUE SE GUARDARA EL ARCHIVO
			String fiche ="" ; // EL NOMBRE DEL FICHERO QUE VAMOS UTILIZAR
			int contador2 = 0; // UN CONTADOR PARA DETERMINAR NUM PALABRAS
			String cadena = ""; // VAR CONTENEDORA DE LA CADENA FINAL

			// BLOQUE COMPROBAR ARGUMENTOS NUMERICOS Y ASIGNAR VALORES

		      if (args.length > 0) {
		     numcaracteres = Integer.parseInt(args[0]);
		      }
		      
		      if (args.length > 1) {
		     numpalabras = Integer.parseInt(args[1]);
		      }
		      
		      if (args.length > 2) {
		    
		      ruta = args[2];
		      }
		      
		      if (args.length > 3) {
		     fiche = args[3];
		      }
	      
			//SCANNERS PARA CAPTURAR DATOS
			Scanner sc = new Scanner(System.in);
			Scanner fich = new Scanner(System.in);
			Scanner rut = new Scanner(System.in);
			
		    //INICIO DEL TRY
			try {

				// PEDIMOS AL USUARIO EL NUMERO DE CARACTERES POR PALABRA SI NO HAY ARGUMENTO
				if (numcaracteres == -1) {
				System.out.println("¿Cuantas LETRAS quieres por palabra?");				
				numcaracteres = sc.nextInt();
				}
				
				// PEDIMOS AL USUARIO EL NUMERO DE CARACTERES POR PALABRA
				if (numpalabras == -1) {
				System.out.println("¿Cuantas PALABRAS vamos a generar?");
				numpalabras = sc.nextInt();
				}
				
				// INICIO BUCLE PARA GENERAR LA PALABRA
				while (contador2 < numpalabras) {
					int contador = 0;
					String palabra = "";
					while (contador < numcaracteres) {
						Random r = new Random();
						char c = (char) (r.nextInt(26) + 'a');
						palabra = palabra + c;
						contador++;
					}
					/* System.out.println("Palabra generada: " + palabra); // MENSAJE PALABRA GENERADA */
					cadena = cadena + " " + palabra+"\n";

					// AGREGA SEGURIDAD, CIERRE DE SCANNER UNA VEZ LLEGAMOS AL NUMERO SOLICITADO
					if (contador2 == numpalabras) {
						sc.close();
					}
					contador2++;
				} //FIN BUCLE PALABRA
				

				// PRINTEO DE LA CADENA FINAL
				System.out.printf("\nListando palabras generadas:\n");
				System.out.println(cadena);

				// DETECCION DE SISTEMA OPERATIVO
				int SystemFlag = 0;
				
				String sistema = System.getProperty("os.name").toLowerCase();
				if (sistema.indexOf("win") >= 0) {
					System.out.println("\nSe ha Detectado SISTEMA OPERATIVO WINDOWS");
					SystemFlag = 0;
					
				} else if (sistema.indexOf("mac") >= 0) {
					System.out.println("\nSe ha Detectado SISTEMA OPERATIVO MAC/OS");
					SystemFlag = 1;
				} else if (sistema.indexOf("nix") >= 0 || sistema.indexOf("nux") >= 0) {
					System.out.println("\nSe ha Detectado SISTEMA OPERATIVO UNIX/LINUX");
					SystemFlag = 1;
				} else {
					System.out.println("\nADVERTENCIA: No se detecta un sistema operativo soportado!!");
				}

				
				// PEDIMOS RUTA SI NO HAY ARGUMENTO
			
				if (ruta.length() == 0) {
				System.out.println("\nPor favor, indica la ruta COMPLETA donde deseas guardar el fichero: ");
				ruta = rut.nextLine();
				
				// CONTROL DE NULOS (ENTER EN PETICION DE RUTA)
				if (ruta.length() == 0)
					ruta = "C:\\";
				}

				
				// PEDIMOS NOMBRE DE FICHERO SI NO HAY ARGUMENTO
				if (fiche.length() == 0) {
				System.out.println("Por favor, indique el nombre del fichero de cliente que se va a generar "
						+ "(CON LA EXTENSION)");
				
				fiche = fich.nextLine();
				
				// CONTROL DE NULOS (ENTER EN PETICION DE NOMBRE FICHERO)
				if (fiche.length() == 0)
					fiche = "texto.txt";
				}
				
				// CORRECCIONES DE RUTA (BARRAS DE WIN / LINUX o MAC)
				if (SystemFlag == 0) { 
					ruta = ruta.replace("/", "\\");					
				} else { 
				ruta = ruta.replace("\\", "/").replace("//", "/").concat(fiche);
				}

			
				// PRINTEADO Y GUARDADO DE FILE:
				PrintWriter out = new PrintWriter(new FileWriter((ruta + fiche),true));
				System.out.println("Archivo guardado en " + ruta + fiche + "\n");
				out.println(cadena);
				
				//CIERRE DE SCANNERS (MEMORY LEAK) Y EXIT
				out.close();
				rut.close();
				fich.close();
				System.exit(1);

				//CONTROL DE EXCEPCIONES
			} catch (InputMismatchException e) {
                System.err.println("La opción indicada no es de un tipo válido, por favor, escoge una opción numérica válida");    
                System.err.println(e.toString());
			} catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Se ha detectado un String de cliente malformado, no cumple el formato o no tiene todos los elementos");
                System.err.println(e.toString());
			} catch (IllegalArgumentException e) {
                System.err.println("Los dos primeros argumentos del programa deben ser números y el tercero una ruta");
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