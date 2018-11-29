package lector;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
   
    public static void main(String[] args) {
    
    	InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader (isr);
  
        
        String teclado = null;
        
        try{
        	
        	System.out.printf("INICIO DEL PROCESO LECTOR\n\n");
            
            //CUANDO NO HAYA NADA EN EL BUFFER ...
            while ((teclado = bf.readLine()) != null){
            	System.out.println(teclado);            	
            }
            
            //EXCEPTIONS
        }catch(IOException e){
            System.err.println("Se ha producido un error de E/S.");
            System.err.println(e.toString());
        }
    }
}
