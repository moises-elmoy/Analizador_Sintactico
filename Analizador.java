/*
Centro Universsitario de los Altos
Ingenieria en Computacion 
Miguel Angel Sanabria
Analizador Lexico
 */

import static java.awt.SystemColor.info;
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;



public class Analizador {
  
    public static void main(String [] arg) throws IOException{
        
        ArrayList<String[]> AUX = new ArrayList<>();
        int fils = 0; //Para numero de filas de la matriz
        int cols = 0; //Para numero de columnas de la matriz
        String cadena = "";
        String[][] info = {}; 
        
        File comp = null; 
        FileReader leerArchivo = null;
        BufferedReader textoArchivo = null;

        try {
            //Ruta absoluta
            leerArchivo = new FileReader("F:\\6° Semestre\\Traductores de Lenguaje II\\Seminario\\AnalizadorLexico\\src\\main\\java\\comp_2.txt"); 
            textoArchivo = new BufferedReader(leerArchivo);

            //Quiza no sea la manera mas optima de hacer la lectura del archivo... 
            while (cadena!=null) {
              cadena = textoArchivo.readLine();

              if (cadena!=null) {
                //System.out.println(cadena);
                AUX.add(cadena.split("\t"));//Almacena cada array (lineas del archivo) en la lista
                fils++;
              }
            }

            if(AUX.size()>0) {
              cols = AUX.get(0).length;
              info = new String[fils][cols];
              for(int i=0; i<fils; i++) {
                for(int j=0; j<cols; j++) {
                  //Captura el elemento (array) de la lista y [j] trae el dato en esa posición del arreglo.
                  info[i][j] = AUX.get(i)[j];
                }
                System.out.println();
              }
            } else {
                System.out.println("No hay datos");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{                    
                if( null != leerArchivo ){   
                    leerArchivo.close();     
                }                  
            }catch (Exception e2){ 
                e2.printStackTrace();
            }
        }
        
        textoArchivo.close();
        leerArchivo.close();
        
        //Matriz de R's
        ArrayList<String[]> AUXR = new ArrayList<>();
        int filaR = 0; //Para numero de filas de la matriz
        int coluR = 0; //Para numero de columnas de la matriz
        String cadenaR = "";
        String[][] R = {}; 
        
        File comR = null; 
        FileReader leerArchivoR = null;
        BufferedReader textoArchivoR = null;

        try {
            //Ruta absoluta
            leerArchivoR = new FileReader("F:\\6° Semestre\\Traductores de Lenguaje II\\Seminario\\AnalizadorLexico\\src\\main\\java\\comp_5.txt"); 
            textoArchivoR = new BufferedReader(leerArchivoR);

            //Quiza no sea la manera mas optima de hacer la lectura del archivo... 
            while (cadenaR != null) {
              cadenaR = textoArchivoR.readLine();

              if (cadenaR!=null) {
                //System.out.println(cadena);
                AUXR.add(cadenaR.split("\t"));//Almacena cada array (lineas del archivo) en la lista
                filaR++;
              }
            }

            if(AUXR.size()>0) {
              coluR = AUXR.get(0).length;
              R = new String[filaR][coluR];
              for(int i=0; i<filaR; i++) {
                for(int j=0; j<coluR; j++) {
                  //Captura el elemento (array) de la lista y [j] trae el dato en esa posición del arreglo.
                  R[i][j] = AUXR.get(i)[j];
                }
                System.out.println();
              }
            } else {
                System.out.println("No hay datos");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{                    
                if( null != leerArchivoR ){   
                    leerArchivoR.close();     
                }                  
            }catch (Exception e2){ 
                e2.printStackTrace();
            }
        }
        
        textoArchivoR.close();
        leerArchivoR.close();
       
            
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
       
        //Variables
        char[] operador = {'+', '-', '/', '*', '='};
        char[] caracterEspecial = {'(', ')', ',', ';', '[', ']', '{', '}'};
        char[] palabra = new char[10];
        int j = 0;
        char[] numero = new char[10];
        int x = 0;
        int punto = 0;
        
        String signo = "";
        Queue<String> cola = new LinkedList();
        
        //Error
        char[] errores = {'#', '"', '!', '?', '¡', '¿', '@', '`', '%', '&', '$'}; 
        
        System.out.println("-----------------------------------------");
        System.out.println("-----.:|Identificación de Cadenas|:.-----");
        System.out.println("-----------------------------------------");

        try {
            //Ruta absoluta del archivo txt
            archivo = new File ("F:\\6° Semestre\\Traductores de Lenguaje II\\Seminario\\AnalizadorLexico\\src\\main\\java\\Archivo.txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String lineas;
            int linea;
            linea = fr.read();
            while(linea != -1) {
                for(int i=0; i<5;i++){
                    if(((char)linea == operador[i]) && (j == 0) && (x == 0)){
                        switch((char)linea){
                            case '+':
                                signo = "opSuma";
                                break;
                            case '-':
                                signo = "opIgualdad";
                                break;
                            case '/':
                                signo = "opRelac";
                                break;
                            case '*':
                                signo = "opMul";
                                break;
                            case '=':
                                signo = "=";
                                break;
                        }
                        
                        for (int a = 0; a < 1; a++) {
                            for (int k = 0; k < cols; k++) {
                                if (signo.equals(info[a][k])) {
                                    cola.add(String.valueOf(k));
                                }
                            }
                        }
                    }
                }
                
                for(int i=0; i<8;i++){
                    if(((char)linea == caracterEspecial[i]) && (j == 0) && (x == 0)){
                        signo = String.valueOf((char)linea);
                        for (int a = 0; a < 1; a++) {
                            for (int k = 0; k < cols; k++) {
                                if (signo.equals(info[a][k])) {
                                    cola.add(String.valueOf(k));
                                }
                            }
                        }
                    }
                }
               
                
                if(Character.isLetter((char)linea)){
                    palabra[j++] = (char)linea;
                }else if(((char)linea == ' ' || (char)linea == '\n' || (char)linea == '\t') && (j!=0)){
                    palabra[j++] = ' ';
                    j = 0;
                   
                    
                    if(palabraReservada(palabra)){
                        int aux = 0;
                        for(int y = 0; y < palabra.length; y++){
                            if(palabra[y] == ' '){
                                break;
                            }
                            aux++;
                        }

                        char[] palabraAux = new char[aux];

                        for(int z = 0; z < aux; z++){
                            palabraAux[z] = palabra[z];
                        }

                        String con = String.valueOf(palabraAux);
                        
                        switch(con){
                            case "if":
                                signo = "if";
                                break;
                            case "while":
                                signo = "while";
                                break;
                            case "return":
                                signo = "return";
                                break;
                            case "else":
                                signo = "else";
                                break;
                             case "$":
                                signo = "$";
                                break;
                             default:
                                signo = "tipo";
                                break;
                        }
                        
                        for (int i = 0; i < 1; i++) {
                            for (int k = 0; k < cols; k++) {
                                if (signo.equals(info[i][k])) {
                                    cola.add(String.valueOf(k));
                                }
                            }
                        }
                        
                    }else {
                        signo = "identificador";
                        for (int i = 0; i < 1; i++) {
                            for (int k = 0; k < cols; k++) {
                                if (signo.equals(info[i][k])) {
                                    cola.add(String.valueOf(k));
                                }
                            }
                        }
                    }
                       
                    for (int i = 0; i < palabra.length; i++) {
                        palabra[i] = '\0';
                    }
                   
                }else if(((char)linea == '#' || (char)linea == '?' || (char)linea == '¿' || (char)linea == '!' || (char)linea == '`' 
                        || (char)linea == '¡' 
                        || (char)linea == '@' || (char)linea == '&' || (char)linea == '$' || (char)linea == '%') && (j!=0)){
                        palabra[j++] = (char)linea;
                        System.out.print("ERROR ");
                        System.out.print((char)linea);
                        System.out.print(" -> Caracter invalido en esta situcion = ");
                        System.out.print(palabra);
                        break;
                }else if((Character.isDigit((char)linea)) && (j!=0)){
                        palabra[j++] = (char)linea;
                        System.out.print("ERROR ");
                        System.out.print((char)linea);
                        System.out.print(" -> Caracter invalido en esta situcion = ");
                        System.out.print(palabra);
                        break;
                }
                
                if(((Character.isDigit((char)linea)) || (char)linea == '.')){
                    numero[x++] = (char)linea;
                    if((char)linea == '.'){
                        punto++;
                    }else if(punto > 1){
                        System.out.print("ERROR ");
                        System.out.print((char)linea);
                        System.out.print(" -> el numero tiene mas de un punto = ");
                        System.out.println(numero);
                        break;
                    }
                    
                }else if(((char)linea == ' ' || (char)linea == '\n' || (char)linea == '\t') && (x!=0)){
                    numero[x]='\0';
                    x = 0;
                    
                    System.out.print(numero);
                    System.out.print(" -> Es un digito");
                    System.out.println();
     
                    for (int i = 0; i < numero.length; i++) {
                        numero[i] = '\0';
                    }
                    punto = 0;
                }else if(((char)linea == '#' || (char)linea == '?' || (char)linea == '¿' || (char)linea == '!' || (char)linea == '`' 
                        || (char)linea == '¡' 
                        || (char)linea == '@' || (char)linea == '&' || (char)linea == '$' || (char)linea == '%') && (x!=0)){
                        numero[x++] = (char)linea;
                        System.out.print("ERROR ");
                        System.out.print((char)linea);
                        System.out.print(" -> Caracter invalido en esta situacion = ");
                        System.out.print(numero);
                        break;
                }else if((Character.isLetter((char)linea)) && (x!=0)){
                        numero[x++] = (char)linea;
                        System.out.print("ERROR ");
                        System.out.print((char)linea);
                        System.out.print(" -> Caracter invalido en esta situacion = ");
                        System.out.print(numero);
                        break;
                }
                
                linea = fr.read();

            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{                    
                if( null != fr ){   
                    fr.close();     
                }                  
            }catch (Exception e2){ 
                e2.printStackTrace();
            }
        }
       
        Stack<String> pila = new Stack<String>();  // $0
        
        cola.add("23");
        //Llenar $0
        pila.push("23");
        pila.push("0");
        
        String TMP = "";
        String auxP = "";
        String auxC = "";
        
        String [] TMPaux = new String[2];
        
        for (int i = 0; i < 1; i++) {
            System.out.println("Pila -> " + pila);
        }
        System.out.println("");
        for (int i = 0; i < 1; i++) {
            System.out.println("Cola -> " + cola);
        }
        System.out.println("");
         
        auxC = cola.peek();
        auxP = pila.peek();
        
        TMP = info[Integer.parseInt(auxP)+1][Integer.parseInt(auxC)];
        System.out.println("[ " + Integer.parseInt(auxP)+ " ][ " + Integer.parseInt(auxC) + " ] -> " + TMP);
        System.out.println("");
            
        while(TMP != "$"){
            TMPaux = TMP.split("",0);
       
            if("d".equals(TMPaux[0])){
                auxC = cola.poll();
                pila.push(auxC);
                
                for (int i = 0; i < 1; i++) {
                    System.out.println("Pila -> " + pila);
                }
                System.out.println("");
                for (int i = 0; i < 1; i++) {
                    System.out.println("Cola -> " + cola);
                }
                System.out.println("");
                        
                if(TMPaux.length >= 3){
                    String tmpaux = "";
                    for (int i = 1; i < TMPaux.length; i++) {
                        tmpaux += TMPaux[i];
                    }
                    pila.push(tmpaux);
                }else{
                   pila.push(TMPaux[1]); 
                }
                
                auxC = cola.peek();
                auxP = pila.peek();
                //pila.push(auxC);
                
                for (int i = 0; i < 1; i++) {
                    System.out.println("Pila -> " + pila);
                }
                System.out.println("");
                for (int i = 0; i < 1; i++) {
                    System.out.println("Cola -> " + cola);
                }
                System.out.println("");
                
                //auxC = cola.poll();
                //pila.push(auxC);           
                
                TMP = info[Integer.parseInt(auxP)+1][Integer.parseInt(auxC)];
                System.out.println("[ " + Integer.parseInt(auxP)+ " ][ " + Integer.parseInt(auxC) + " ] -> " + TMP);
                System.out.println("");
                
                
            }else if("r".equals(TMPaux[0])){
                String epcilon = "";
                String Auxepcilon = "";
                
                switch(TMPaux[1]){
                    case "0":
                        for (int i = 0; i < 1; i++) {
                            System.out.println("Pila -> " + pila);
                        }
                        System.out.println("");
                        for (int i = 0; i < 1; i++) {
                            System.out.println("Cola -> " + cola);
                        }
                        System.out.println("");
                        System.out.println("Estado de aceptacion -> " + TMP);
                        return;
                    case "1":
                        for (int i = 0; i < 1; i++) {
                            for (int k = 0; k < coluR; k++) {
                                if (TMP.equals(R[i][k])) {
                                    epcilon = R[i+1][k];
                                }
                            }
                        }
                        
                        auxP = pila.peek();
                        for (int i = 0; i < 1; i++) {
                            for (int k = 0; k < cols; k++) {
                                if (epcilon.equals(info[i][k])) {
                                    auxC = String.valueOf(k);
                                    auxP = info[i+1][k];
                                }
                            }
                        }
                        
                        pila.push(auxC);
                        pila.push(auxP);
                        
                        auxC = cola.peek();
                        auxP = pila.peek();
                        
                        for (int i = 0; i < 1; i++) {
                            System.out.println("Pila -> " + pila);
                        }
                        System.out.println("");
                        for (int i = 0; i < 1; i++) {
                            System.out.println("Cola -> " + cola);
                        }
                        System.out.println("");
                        
                        TMP = info[Integer.parseInt(auxP)+1][Integer.parseInt(auxC)];
                        System.out.println("[ " + Integer.parseInt(auxP)+ " ][ " + Integer.parseInt(auxC) + " ] -> " + TMP);
                        System.out.println("");
                        break;
                    case "2":
                        for (int i = 0; i < 1; i++) {
                            for (int k = 0; k < coluR; k++) {
                                if (TMP.equals(R[i][k])) {
                                    epcilon = R[i+1][k];
                                }
                            }
                        }
                        
                        auxP = pila.peek();
                        for (int i = 0; i < 1; i++) {
                            for (int k = 0; k < cols; k++) {
                                if (epcilon.equals(info[i][k])) {
                                    auxC = String.valueOf(k);
                                }
                            }
                        }
                        
                        pila.push(auxC);
                        
                        for (int i = 0; i < 1; i++) {
                            System.out.println("Pila -> " + pila);
                        }
                        System.out.println("");
                        for (int i = 0; i < 1; i++) {
                            System.out.println("Cola -> " + cola);
                        }
                        System.out.println("");
                        
                        TMP = info[Integer.parseInt(auxP)+1][Integer.parseInt(auxC)];
                        System.out.println("[ " + Integer.parseInt(auxP)+ " ][ " + Integer.parseInt(auxC) + " ] -> " + TMP);
                        System.out.println("");
                        
                        pila.push(TMP);
                        
                        for (int i = 0; i < 1; i++) {
                            System.out.println("Pila -> " + pila);
                        }
                        System.out.println("");
                        for (int i = 0; i < 1; i++) {
                            System.out.println("Cola -> " + cola);
                        }
                        System.out.println("");
                        
                        auxC = cola.peek();
                        auxP = pila.peek();
                        
                        TMP = info[Integer.parseInt(auxP)+1][Integer.parseInt(auxC)];
                        System.out.println("[ " + Integer.parseInt(auxP)+ " ][ " + Integer.parseInt(auxC) + " ] -> " + TMP);
                        System.out.println("");
                        
                         for (int i = 0; i < 4; i++) {
                            System.out.println("Pila -> " + pila);
                            pila.pop();
                        }
                        
                        for (int i = 0; i < 1; i++) {
                            System.out.println("Pila -> " + pila);
                        }
                        System.out.println("");
                        for (int i = 0; i < 1; i++) {
                            System.out.println("Cola -> " + cola);
                        }
                        System.out.println("");
                        
                        
                        break;
                    case "3":
                        for (int i = 0; i < 1; i++) {
                            for (int k = 0; k < coluR; k++) {
                                if (TMP.equals(R[i][k])) {
                                    epcilon = R[i+1][k];
                                    Auxepcilon = R[i+2][k];
                                }
                            }
                        }
                        
                        
                        for (int i = 0; i < 1; i++) {
                            for (int k = 0; k < cols; k++) {
                                if (epcilon.equals(info[i][k])) {
                                    auxC = String.valueOf(k);
                                    auxP = info[i+1][k];
                                }
                            }
                        }
                        
                        pila.push(auxC);
                        pila.push(auxP);
                        
                        auxC = cola.peek();
                        auxP = pila.peek();
                        
                        for (int i = 0; i < 1; i++) {
                            System.out.println("Pila -> " + pila);
                        }
                        System.out.println("");
                        for (int i = 0; i < 1; i++) {
                            System.out.println("Cola -> " + cola);
                        }
                        System.out.println("");
                        
                        TMP = info[Integer.parseInt(auxP)+1][Integer.parseInt(auxC)];
                        System.out.println("[ " + Integer.parseInt(auxP)+ " ][ " + Integer.parseInt(auxC) + " ] -> " + TMP);
                        System.out.println("");
                        
                        for (int i = 0; i < 2; i++) {
                            System.out.println("Pila -> " + pila);
                            pila.pop();
                        }
                        
                        for (int i = 0; i < 1; i++) {
                            System.out.println("Pila -> " + pila);
                        }
                        System.out.println("");
                        for (int i = 0; i < 1; i++) {
                            System.out.println("Cola -> " + cola);
                        }
                        System.out.println("");
                        
                        break;
                    case "4":
                        for (int i = 0; i < 1; i++) {
                            for (int k = 0; k < coluR; k++) {
                                if (TMP.equals(R[i][k])) {
                                    epcilon = R[i+1][k];
                                    Auxepcilon = R[i+2][k];
                                }
                            }
                        }
                        
                        
                        for (int i = 0; i < 1; i++) {
                            for (int k = 0; k < cols; k++) {
                                if (epcilon.equals(info[i][k])) {
                                    auxC = String.valueOf(k);
                                    auxP = info[i+1][k];
                                }
                            }
                        }
                        
                        pila.push(auxC);
                        pila.push(auxP);
                        
                        auxC = cola.peek();
                        auxP = pila.peek();
                        
                        for (int i = 0; i < 1; i++) {
                            System.out.println("Pila -> " + pila);
                        }
                        System.out.println("");
                        for (int i = 0; i < 1; i++) {
                            System.out.println("Cola -> " + cola);
                        }
                        System.out.println("");
                        
                        TMP = info[Integer.parseInt(auxP)+1][Integer.parseInt(auxC)];
                        System.out.println("[ " + Integer.parseInt(auxP)+ " ][ " + Integer.parseInt(auxC) + " ] -> " + TMP);
                        System.out.println("");
                        
                        break;
                    case "6":
                        String cont = "";

                        for (int i = 0; i < 1; i++) {
                            for (int k = 0; k < coluR; k++) {
                                if (TMP.equals(R[i][k])) {
                                    epcilon = R[i+1][k];
                                    Auxepcilon = R[i+2][k];
                                }
                            }
                        }
                        
                        
                        for (int i = 0; i < 1; i++) {
                            for (int k = 0; k < cols; k++) {
                                if (epcilon.equals(info[i][k])) {
                                    auxC = Integer.toString(k);
                                }
                                if(Auxepcilon.equals(info[i][k])) {
                                    auxP = Integer.toString(k);
                                }
                            }
                        }
                        
                        //Eliminar de la pila
                        for (int i = 0; i < (Integer.parseInt(auxP)*2); i++) {
                            System.out.println("Pila -> " + pila);
                            pila.pop();
                        }
                        
                        for (int i = 0; i < 1; i++) {
                            System.out.println("Pila -> " + pila);
                        }
                        
                        System.out.println("");
                        for (int i = 0; i < 1; i++) {
                            System.out.println("Cola -> " + cola);
                        }
                        System.out.println("");
                        
                        pila.push(auxC);
                        pila.push(auxP);
                        
                        auxC = cola.peek();
                        auxP = pila.peek();
                        
                        for (int i = 0; i < 1; i++) {
                            System.out.println("Pila -> " + pila);
                        }
                        System.out.println("");
                        for (int i = 0; i < 1; i++) {
                            System.out.println("Cola -> " + cola);
                        }
                        System.out.println("");
                        
                        TMP = info[Integer.parseInt(auxP)+1][Integer.parseInt(auxC)];
                        System.out.println("[ " + Integer.parseInt(auxP)+ " ][ " + Integer.parseInt(auxC) + " ] -> " + TMP);
                        System.out.println("");
                        
                        for (int i = 0; i < 2; i++) {
                            System.out.println("Pila -> " + pila);
                            pila.pop();
                        }
                        
                        for (int i = 0; i < 1; i++) {
                            System.out.println("Pila -> " + pila);
                        }
                        System.out.println("");
                        for (int i = 0; i < 1; i++) {
                            System.out.println("Cola -> " + cola);
                        }
                        System.out.println("");
                        
                        break;
                    case "7":

                        for (int i = 0; i < 1; i++) {
                            for (int k = 0; k < coluR; k++) {
                                if (TMP.equals(R[i][k])) {
                                    epcilon = R[i+1][k];
                                }
                            }
                        }

                        for (int i = 0; i < 1; i++) {
                            for (int k = 0; k < cols; k++) {
                                if (epcilon.equals(info[i][k])) {
                                    auxC = Integer.toString(k);
                                    auxP = pila.peek();
                                }
                            }
                        }

                        pila.push(auxC);

                        for (int i = 0; i < 1; i++) {
                            System.out.println("Pila -> " + pila);
                        }
                        System.out.println("");
                        for (int i = 0; i < 1; i++) {
                            System.out.println("Cola -> " + cola);
                        }
                        System.out.println("");

                        TMP = info[Integer.parseInt(auxP)+1][Integer.parseInt(auxC)];
                        System.out.println("[ " + Integer.parseInt(auxP)+ " ][ " + Integer.parseInt(auxC) + " ] -> " + TMP);
                        System.out.println("");

                        pila.push(TMP);
                        auxC = cola.peek();
                        auxP = pila.peek();

                        for (int i = 0; i < 1; i++) {
                            System.out.println("Pila -> " + pila);
                        }
                        System.out.println("");
                        for (int i = 0; i < 1; i++) {
                            System.out.println("Cola -> " + cola);
                        }
                        System.out.println("");

                        TMP = info[Integer.parseInt(auxP)+1][Integer.parseInt(auxC)];
                        System.out.println("[ " + Integer.parseInt(auxP)+ " ][ " + Integer.parseInt(auxC) + " ] -> " + TMP);
                        System.out.println("");
                        break;
                    default:
                        return;
                }
                         
            } 
        }
        
    }
    
     //Funcion para ver si es palabra reservada
    static boolean palabraReservada(char[] palabra){
        String[] keywords = {"main","int","float","void","char","bool","double","short","enum",
                            "try","catch","if","else","do","while","for","switch","case","delete","return",
                            "static","public","private","continue","struct"};
 
        int aux = 0;
        for(int y = 0; y < palabra.length; y++){
            if(palabra[y] == ' '){
                break;
            }
            aux++;
        }
        
        char[] palabraAux = new char[aux];
        
        for(int z = 0; z < aux; z++){
            palabraAux[z] = palabra[z];
        }
        
        String cadena = String.valueOf(palabraAux);
                 
        
        for (int i = 0; i < 25; i++) {
            if (cadena.equalsIgnoreCase(keywords[i])) {
                return true;
            }
        }
        
        return false;
    }

    
}
