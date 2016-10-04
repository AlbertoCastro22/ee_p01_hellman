/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

/**
 *
 * @author Usersone
 */
import java.math.*;
import java.util.*;

public class MerkleHellman {

    Lista<BigInteger> lista01 = new Lista<BigInteger>();
    Lista<BigInteger> lista02 = new Lista<BigInteger>();
    BigInteger q, r, w, y;

    /**
     * metodo para hacer la sumatioria
     */
    public static BigInteger sumatoria(Nodo<BigInteger> aux) {
        BigInteger sumatoria = new BigInteger("0");
        while (aux != null) {
            sumatoria = sumatoria.add(aux.getDato());
            aux = aux.getSiguiente();
        }//fin del while
        return sumatoria;
    }//fin del metodo sumatoria

    public static BigInteger aleatorio() {
        Random rnd = new Random();
        return new BigInteger(String.valueOf(rnd.nextInt(1000000)));

    }//fin del metodo aleatorio

    /**
     * genera un numero aleatorio bigInteger entre 1 y Q
     */
    public static BigInteger aleatorios(BigInteger tope) {
        Random rnd = new Random();
        BigInteger numeroAleatorio;
        do {
            numeroAleatorio = new BigInteger(tope.bitLength(), rnd);
        } while (numeroAleatorio.compareTo(tope) >= 0);
        return numeroAleatorio;
    }//fin del metodo aleatorio

    /**
     * este metodo generara nuestra llave privada conformada por 8 numeros
     */
    public Lista LlavePrivada() {
        /**
         * el for genera los 8 numeros aleatorios insertando al final de nuestra
         * lista y los numeros seran nuestra llave privada
         */
        for (int x = 0; x < 8; x++) {
            lista01.InsertarFinal(this.aleatorio().add(this.sumatoria(lista01.getInicio())));
        }//fin del ciclo for
        System.out.println("w=" + this.lista01);
        return lista01;
    }

    /**
     * este metodo servira para generar la sumatoria de toda la llave privada
     * que es W
     */
    public void GenerarSumatoriaW() {
        System.out.println("Sum=" + sumatoria(lista01.getInicio()));
    }

    /**
     * este metodo sirve para generar a Q el cual debe ser mas grande que la
     * sumatoria de W
     */
    public BigInteger GenearQ() {
        q = this.aleatorio().add(sumatoria(lista01.getInicio()));
        //System.out.println("q=");
        return q;
    }

    /**
     * este metodo servira para generar a R
     */
    public BigInteger GenerarR() {
        /**
         * do while para que siempre sea un numero coprimo de q
         */
        do {
            r = aleatorios(this.GenearQ());
        } while (r.gcd(this.GenearQ()).compareTo(new BigInteger("1")) != 0);

        return r;
    }

    /**
     * este metodo generara nuestra llave publica
     */
    public Lista LlavePublica() {

        Lista<BigInteger> llavePublica = new Lista<BigInteger>();
        Nodo<BigInteger> aux = lista01.getInicio();
        while (aux != null) {
            llavePublica.InsertarFinal(aux.getDato().multiply(this.GenerarR()).mod(this.GenearQ()));
            aux = aux.getSiguiente();
        }
        // System.out.println("LLave Publica: " + llavePublica);
        aux = llavePublica.getInicio();
        return llavePublica;
    }

    /**
     * metodo para pasar el texto abinario
     */
    public static String textoABinario(String texto) {
        String textoBinario = "";
        for (char letra : texto.toCharArray()) {
            textoBinario += String.format("%8s", Integer.toBinaryString(letra));
        }
        return textoBinario.replace("\u0020", "\u0030");
    }

    /**
     * en este metodo se retornara la lista cuando se convierte el caracter a
     * binario
     */
    public static Lista<BigInteger> lista(String texto) {
        String textoBinario = "";
        Lista<BigInteger> p = new Lista<BigInteger>();
        for (char letra : texto.toCharArray()) {
            String l = letra + "";
            int i = Integer.parseInt(l);
            BigInteger o = new BigInteger(i + "");
            //p.insertarFinal(o);
            p.InsertarFinal(o);
        }
        return p;
    }

    /**
     * este metodo servira para encriptar el mensaje de caracter en caracter
     */
    public BigInteger encriptar(char letra) {
        System.out.println("Caracter a encriptar = [" + letra + "]");
        String auxiliar = letra + "";
        Lista<BigInteger> z = new Lista<BigInteger>();
        z = lista(textoABinario(auxiliar));
        Nodo<BigInteger> nodo1 = z.getInicio();
        Nodo<BigInteger> nodo2 = lista01.getInicio();
        int e = 0, auxiliar5 = 0;
        Lista<BigInteger> f = new Lista<BigInteger>();
        Nodo<BigInteger> nodo3;

        System.out.print("Valor binario {");
        //////
        z.Recorrei();
        System.out.print("");
        while (nodo1 != null) {

            BigInteger multiplicacion = (BigInteger) this.LlavePublica().getInicio().getDato();

            f.InsertarFinal(multiplicacion);
            nodo1 = nodo1.getSiguiente();
            nodo2 = nodo2.getSiguiente();
        }
        nodo3 = f.getInicio();
        while (nodo3 != null) {
            BigInteger l = nodo3.getDato();
            e = l.intValue();
            auxiliar5 += e;
            nodo3 = nodo3.getSiguiente();
        }
        y = new BigInteger(auxiliar5 + "");
        //System.out.println("Caracter encriptado: " + y);
        return y;
    }
   
     public String desencriptar(char cadena) {
        int i = 0;
        System.out.println("\nMensaje Encriptado: " + this.encriptar(cadena).toString(i));
        i++;
        Lista<BigInteger> lista03 = new Lista<BigInteger>();// es la lista de  sumatorias para aplicar la formula 
        BigInteger yy = null, qq, rr;
        rr = this.GenerarR();
        qq = this.GenearQ();
        Nodo<BigInteger> recorre = lista02.getInicio();
        while (recorre != null) {
            // BigInteger y=m.encriptar(s.charAt(i)).multiply(r.modInverse(q)).mod(q);
            //decrip=recorre.getDato().multiply(valorR.modInverse(valorQ)).mod(valorQ);
            yy = recorre.getDato().multiply(rr.modInverse(qq)).mod(qq);
            lista03.InsertarFinal(yy);
            recorre = recorre.getSiguiente();

        }

        System.out.println("\n Lista con los elementos del mensaje encrptado aplicando la formula power MOD:" + lista03);
//invertimos la clave primaria   
        Lista<BigInteger> inversa = new Lista<BigInteger>();
        Nodo<BigInteger> aux = lista01.getInicio();

        while (aux != null) {
            inversa.AgregarInicio(aux.getDato());
            aux = aux.getSiguiente();
        }
        System.out.println("Llave privada inversa: " + inversa);

//desencriptamos
        Nodo<BigInteger> aux2 = inversa.getInicio();
        Nodo<BigInteger> aux3 = lista03.getInicio();
        Lista<String> binario = new Lista<String>();
        String bin = "";
        BigInteger tempo;
        while (aux3 != null) {

            tempo = aux3.getDato();
            while (aux2 != null) {

                if (aux2.getDato().compareTo(tempo) == -1 || aux2.getDato().compareTo(tempo) == 0) {
                    tempo = tempo.subtract(aux2.getDato());
                    bin += "1";
                } else {
                    bin += "0";
                }
                aux2 = aux2.getSiguiente();
            }
            binario.InsertarFinal(bin);
            bin = "";
            aux3 = aux3.getSiguiente();
            aux2 = inversa.getInicio();

        }
        System.out.println("Binarios en  orden inverso: " + binario);

        // invertir binarios
        Lista<String> binarios2 = new Lista();
        Nodo<String> aux4 = binario.getInicio();
        String bueno = "";
        String a = "";

        while (aux4 != null) {
            a = aux4.getDato();

            for (int j = a.length() - 1; j >= 0; j--) {
                bueno += a.charAt(j);
            }
            binarios2.InsertarFinal(bueno);
            bueno = "";
            aux4 = aux4.getSiguiente();
        }
        System.out.println("Binarios en orden correcto: " + binarios2);
//convertir: convierte los numeros binarios a letras
        Nodo<String> vr = binarios2.getInicio();
        System.out.println("Mensaje desencriptado:");
        String textF = cadena + "";
        char c;
        while (vr != null) {

            c = (char) Integer.parseInt(vr.getDato(), 2);
            System.out.print(c);
            textF += c;
            vr = vr.getSiguiente();
        }
        return textF;

    }
}
