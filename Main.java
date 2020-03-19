import java.util.Scanner;
import FileManager.Code;

public class Main {
  public static void main(String[] args) {
    
    Scanner s = new Scanner(System.in);
    int opt = 0;
    String fileName = "";
    do {
    System.out.println("Bienvenido al gestor de Txt\n¿Que quiere hacer?");
    System.out.println("1. Ver ficheros\n2. Leer fichero\n3. Modificar fichero\n4." +
                       "Crear fichero\n5. Mezlcar ficheros\n6. Borrar fichero\n7. Eliminar tildes\n8. Salir");
    opt = Integer.parseInt(s.nextLine());
    switch(opt) {
      
      case 1:
        System.out.println("Los ficheros son:\n" + Code.getFilesTxt());
        break;
      case 2:
        System.out.println("¿Que quieres leer?");
        fileName = s.nextLine();
        Code.readFile(fileName);
        break;
      case 3:
        System.out.println("¿Cual quieres modificar?");
        fileName = s.nextLine();
        Code.writeFile(fileName);
        break;
      case 4:
        System.out.println("¿Como se llama el fichero?");
        fileName = s.nextLine();
        Code.writeFile(fileName);
        break;
      case 5:
        System.out.println("Introduce el nombre del fichero 1:");
        String file1 = s.nextLine();
        System.out.println("Introduce el nombre del fichero 2:");
        String file2 = s.nextLine();
        System.out.println("Introduce el nombre del fichero resultante:");
        fileName = s.nextLine();
        Code.fuseFiles(file1, file2, fileName);
        break;
      case 6:
        System.out.println("Como se llama el fichero a borrar");
        fileName = s.nextLine();
        Code.deleteFile(fileName);
        break;
      case 7:
        System.out.println("Como se llama el fichero?");
        fileName = s.nextLine();
        Code.delTildes(fileName);
        break;
      case 8:
        break;
      default:
        System.out.println("Opción no valida!");
        break;
    }
    } while (opt != 8);
    s.close();
  }
}
