package escritor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Main {
  
    public static void main(String[] args) {
   
    	System.out.printf("Lógica del proceso escritor a continuación\n\n");
    	
    	//VARIABLES
    	int cifranumeros;
    	int contador=0;
    	          
        System.out.println("¿Cuantos números vas a poner?");   
        
        //PEDIMOS AL USUARIO EL NUMERO DE VALORES QUE VA A INGRESAR
        Scanner sc = new Scanner(System.in);
        cifranumeros = sc.nextInt();      
               
        
        //VAMOS A GENERAR UN LISTADO PARA GUARDAR LOS NUMEROS
    	List<Integer> listado = new ArrayList<Integer>();            
    	
    	//BUCLE PARA PEDIR TANTOS NUMEROS COMO EL USUARIO HA PEDIDO
        while (contador < cifranumeros) {
        	int numero;           	
        	System.out.println("Dame un numero:");            	
            numero = sc.nextInt();
        	listado.add(numero);
        	contador++;
        	//AGREGA SEGURIDAD, CIERRE DE SCANNER UNA VEZ LLEGAMOS AL NUMERO SOLICITADO
        	if (contador==cifranumeros) {
        		sc.close();
        	}        
        }

        //SORTING Y PRINTEO DEL LISTADO
        Collections.sort(listado);
        System.out.println("A continuación se listan los números ordenados de menor a mayor:");
        System.out.println(listado);

    }

}