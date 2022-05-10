package registros;


import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Registros {

    private static Scanner sc1 = new Scanner(System.in);
    private static String nombreusuario;
    private static String direccionEnvio;
    private static String productoCompra;
    private static String tipoCompra;
    private static String respuesta1;
    private static String marca;
    private static FileReader fileReader1 = null;
    private static File file2 = null;
    private static BufferedReader bufferedReader1 = null;
    static StringTokenizer separarPalabras;


    public void crearfichero() {

        try {
            file2 = new File("inventario");
            if (file2.createNewFile()) {
                System.out.println("Has creado un fichero");
            } else
                System.out.println("El fichero ya existe");
        } catch (IOException e) {
            System.out.println("El fichero no se ha podido crear vale se mejor haciendoe sto ");
            e.printStackTrace();
        }
    }

    public static void vender() {

        try {
            fileReader1 = new FileReader("inventario");
            bufferedReader1 = new BufferedReader(fileReader1);
            String strCurrentLine = " ";
            String globalstr = "";
            while ((strCurrentLine = bufferedReader1.readLine()) != null) {
                globalstr += strCurrentLine + "\n";
            }
            System.out.println(globalstr);
            separarPalabras = new StringTokenizer(globalstr, "/");
            String tipo = separarPalabras.nextToken().trim();
            String marca = separarPalabras.nextToken().trim();
            String precio = separarPalabras.nextToken().trim();
            String unidades = separarPalabras.nextToken().trim();
            mostrartexto("Indique su nombre");
            nombreusuario = sc1.nextLine();
            mostrartexto("Bienvenido: " + nombreusuario);
            System.out.println("Indique lugar de envio");
            String lugarEnvio = sc1.nextLine();
            System.out.println("cuantos productos se van a comprar");
            int vendido = sc1.nextInt();

                if (vendido > Integer.parseInt(unidades)) {
                    System.out.println("no Hay unidades");
                } else {

                    int cantidad = Integer.parseInt(unidades) - vendido;
                    String nuevaUnidad = String.valueOf(cantidad);
                    String vendido1 = String.valueOf(vendido);


                    registroUsuario(vendido1, nombreusuario,lugarEnvio);

                    try {
                        //FileWrite; false sobre escribe los datos
                        //File Write true; los datos se añaden se acumulan
                        FileWriter fileWriter1 = new FileWriter("inventario", false);
                        fileWriter1.write("Camiseta/adidas/20/" + cantidad + "/"+"\n");
                        fileWriter1.close();


                    } catch (IOException e) {
                        System.out.println("No se ha podido escribir en el fichero ");

                    }

                }



        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("no se ha podido eler el fichero");
        } finally {
            try {
                if (null != fileReader1) {
                    fileReader1.close();
                }

            } catch (IOException e2) {
                e2.printStackTrace();

            }
        }

    }

    protected void inicio() {

        mostrartexto("Bienvenido a nuestra app de Ventas\nPor  favor: ");
        mostrartexto("Menu:");
        respuesta1 = menu("1._Realizar Compra.\n2.-Finalizar Compra");
        procesandoRespuesta(respuesta1);
        mostrartexto("Gracias Por Su compra");


    }

    public static void mostrartexto(String texto1) {
        System.out.println(texto1);
    }

    private static String obtenertexto() {
        return sc1.nextLine();
    }

    private static int obtenernumero() {
        return sc1.nextInt();
    }

    private static void ventaTipo() {
        mostrartexto("Seleccione el tipo de producto que va a comprar:\n1.Camiseta\n2.Zapatos\n3.Patalones");
        String tipoCompra = obtenertexto();


        if (tipoCompra.equals("1")) {
            ventaMarca();

        }
        if (tipoCompra.equals("2")) {
            ventaMarca();
        }
        if (tipoCompra.equals("3")) {
            ventaMarca();

        }


    }

    private static void ventaMarca() {
        mostrartexto("Elija marca:\n1.Adiddas,\n2.Nike\n3.Puma");

        marca = obtenertexto();
        if (marca.equals("1")) {

            vender();


        }
        if (marca.equals("2")) {

            vender();


        }
        if (marca.equals("3")) {

            vender();


        }



    }

    private static String menu(String text1) {
        do {
            mostrartexto(text1);
            respuesta1 = obtenertexto();

        } while (!respuesta1.equals("1") && !respuesta1.equals("2"));
        return respuesta1;
    }

    private static void procesandoRespuesta(String texto1) {

        if (texto1.equals("1")) {

            ventaTipo();

        }
        if (texto1.equals("2")) {


        }
    }

    protected static String registroUsuario(String vendido, String nombreusuario, String lugarEnvio) {


        try {
            //FileWrite; false sobre escribe los datos
            //File Write true; los datos se añaden se acumulan
            FileWriter fileWriter1 = new FileWriter("RegistroUsuario", true);
            fileWriter1.write(nombreusuario + "/camiseta/" + vendido + "/" + lugarEnvio+"/"+"\n");
            fileWriter1.close();


        } catch (IOException e) {
            System.out.println("No se ha podido escribir en el fichero ");

        }
        return nombreusuario;
    }

    protected static void finalizar(){
        mostrartexto("Vuelva cuanto antes");
    }
}
