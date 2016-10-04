
package Servidor;

public class Messenger {
    public static Conector servidor,cliente;
    public static void main (String[] args){
        VServidor server = new VServidor();
        server.show();      
    }
    
    public static void initServer(){
        servidor = new Conector("hilos");
        servidor.start();
    } 
}