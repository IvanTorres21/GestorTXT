package FileManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Code {

  /**
   * Función que lee un fichero de texto y lo imprime por pantalla
   * @param name Nombre del fichero de texto (sin extensión).
   */
  public static String readFile(String name) {

    name += ".txt";
    String text = "";
    try {

      BufferedReader br = new BufferedReader(new FileReader(name)); // Con esto accedemos al archivo

      String linea = br.readLine();

      while (linea != null) {

        System.out.println(linea);
        text += linea + "\n";
        linea = br.readLine();
      }

      br.close();
      return text;
    } catch (FileNotFoundException fnfe) {

      System.out.println("No se ha podido encontrar el archivo...");
      System.out.println("(Recuerde que no hace falta escribir .txt)");
      return "";
    } catch (IOException ioe) {

      System.out.println("No se ha podido acceder al archivo...");
      return "";
    }
  }
  
  /**
   * Función que calcula la media de los valores de un archivo
   * @param name Nombre del archivo
   * @return la media en caso de que no haya problemas o: 
   * (-1 si no hay ningún parametro, -2 si no se encuentra el archivo, -3 si no se puede leer)
   */
  public static double avgFile(String name) {
    
    name += ".txt";
    try {
      
      BufferedReader br = new BufferedReader(new FileReader(name));
      
      int cant = 0;
      String num = "0";
      double suma = 0;
      
      while (num != null) {
        
        suma += Double.parseDouble(num); // Importante el orden en el que esto se hace.
        num = br.readLine();
        cant++;
      }
      cant--;
      br.close();
      if (cant != 0) {
        
        return (suma / (double) cant);
      } else {
        
        System.out.println("No hay suficientes parametros...");
        return -1;
      }
    } catch(FileNotFoundException fnfe) {
      
      System.out.println("No se ha podido encontrar el archivo...");
      System.out.println("(Recuerde que no hace falta escribir .txt)");
      return -2;
    } catch(IOException ioe) {
      
      System.out.println("No se ha podido leer el archivo");
      return -3;
    }
  }
  
  /**
   * Función que escribe en un fichero de texto
   * @param name Nombre del fichero en el que se va a escribir
   */
  public static void writeFile(String name) {
    
    
    Scanner s = new Scanner(System.in);
    String text = "";
    System.out.println("Quieres conservar sus datos anteriores (si el archivo es nuevo di 'N')? (s/n)");
    char opt = s.nextLine().toLowerCase().charAt(0);
    if (opt == 's') {
        
      text += readFile(name);
    }  
    System.out.println("Escriba una linea vacía para terminar...");
    String linea = s.nextLine(); 
    text += linea + " \n";
    while (!linea.equals("")) {
      
      linea = s.nextLine();
      text += linea + " \n";
    }
    System.out.println("--------------------------------------------");
    System.out.println(text);
    System.out.print("Es este el texto que quiere guardar? (s/n): ");
    opt = s.nextLine().toLowerCase().charAt(0);
    if (opt == 's') {
      
      try {
        
        name += ".txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(name));
        bw.write(text);
        bw.close();
      } catch (FileNotFoundException fnfe) {
        
        System.out.println("No se ha podido encontrar el archivo...");
        System.out.println("(Recuerde que no hace falta escribir .txt)");
      } catch (IOException ioe) {
        
        System.out.println("No se ha podido escribir en el archivo...");
      }
    }  
  }
  
  public static String arrayToString(String[] array) {
    
    String res = "";
    for (String linea : array) {
      
      res += linea + "\n";
    }
    return res;
  }
  /**
   * Función que devuelve una lista de ficheros de text
   * @return Los ficheros de texto a devolver
   */
  public static String getFilesTxt() {
    
    FilenameFilter filter = new FilenameFilter() { //Creamos un filtro
      
      @Override
      public boolean accept(File f, String name) {
        return name.endsWith(".txt");
      }
    };
    String[] files;
    File f = new File("."); //La ruta del archivo, el . es el directorio actual
    files  = f.list(filter);
    return arrayToString(files);
  }
  /**
   * Función que mezcla linea a linea dos ficheros de texto
   * @param name1 fichero 1
   * @param name2 fichero 2
   * @param name3 Fichero resultante
   */
  public static void fuseFiles(String name1, String name2, String name3) {
    
    name1 += ".txt";
    name2 += ".txt";
    name3 += ".txt";
    try {
      
      BufferedReader br = new BufferedReader(new FileReader(name1));
      BufferedReader br2 = new BufferedReader(new FileReader(name2));
      BufferedWriter bw = new BufferedWriter(new FileWriter(name3));
      String text = ""; 
      String line1 = "";
      String line2 = "";
      while (line1 != null || line2 != null) {
        
        line1 = br.readLine();
        line2 = br2.readLine();
        if (line1 != null && line2 != null) {
           
          text += line1 + "\n" + line2 +"\n";  
        } else if (line1 == null && line2 != null) {
          
          text += line2 + "\n";
        } else if (line1 != null && line2 == null) {
          
          text += line1 + "\n";
        }
      }
      bw.write(text);
      br.close();
      br2.close();
      bw.close();
    } catch(FileNotFoundException fnfe) {
      
      System.out.println("No se ha podido encontrar el archivo...");
      System.out.println("(Recuerde que no hace falta escribir .txt)");
    } catch(IOException ioe) {
      
      System.out.println("No se ha podido acceder al archivo...");
    }
  }
  /**
   * Función que borra un fichero txt
   * @param name Nombre del fichero a borrar 
   */
  public static void deleteFile(String name) {
    
    name += ".txt";
    File file = new File(name);
    if (file.exists()) {
      file.delete();
    } else {
      System.out.println("No se ha encontrado ningun archivo con el nombre: " + name);
    }
  }
  
  public static void delTildes(String name) {
    
    name += ".txt";
    char[] tildes = {'á', 'é', 'í', 'ó', 'ú'};
    char[] normal = {'a', 'e', 'i', 'o', 'u'};
    try {
      
      BufferedReader bf = new BufferedReader(new FileReader(name));
      String linea = bf.readLine();
      String text = "";
      while (linea != null) {
        
        for (int i  = 0; i < 5; i++) {
          
          linea = linea.replace(tildes[i], normal[i]);
        }
        text += linea + "\n";
        linea = bf.readLine();
      }
      BufferedWriter br = new BufferedWriter(new FileWriter(name));
     
      br.write(text);
      bf.close();
      br.close();
    } catch (FileNotFoundException fnfe) {
      
      System.out.println("No se ha podido encontrar el archivo...");
      System.out.println("(Recuerde que no hace falta escribir .txt)");
    } catch(IOException ioe) {
      
      System.out.println("No se ha podido acceder al archivo...");
    }
  }
}
