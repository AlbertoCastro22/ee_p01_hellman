
package Servidor;
import java.net.*;
import java.io.*;
//import messenger.VServidor;
public class Conector extends Thread {
    private Socket s;
    private ServerSocket ss;
    private InputStreamReader entradaSocket;
    private DataOutputStream salida;
    private BufferedReader entrada;
    final int puerto =8181;
    /**
     * constructor que se le pasa como parametro el nombre de la red
     */
    public Conector(String nombre){
        super(nombre);
    }
     /**
      * metodo para enviar un mensaje desde el servidor
      */
    public void enviarMSG(String msg){
        try{
              String s=msg;
         MerkleHellman m = new MerkleHellman();
        m.LlavePrivada();
        m.GenerarSumatoriaW();
        System.out.println("q=" + m.GenearQ());
        System.out.println("r=" + m.GenerarR());
        m.LlavePublica();
        String cadenota="";
        for(int i=0;i<s.length();i++){
           m.encriptar(s.charAt(i));
           for(int x=0;x<s.length();x++){
              // System.out.println("cedena desecncriptada "+m.desencriptar(s.charAt(x)));
               cadenota+=m.desencriptar(s.charAt(x)); 
               //this.salida.writeUTF(m.desencriptar(s.charAt(x))); 
           }
           
        }
           this.salida.writeUTF(cadenota+"\n");   
        //  this.salida.writeUTF("\n");  
           
     
          
        }catch (IOException e){};
    }
     /**
      * metodo run para que se haga nuestro chat,es decir para que envie y reciba los mensajes
      */
    public void run(){
     String text="test";
     try{
         this.ss = new ServerSocket(puerto);
         this.s = ss.accept();
            /**
            * creacion de entrada de datos para la lectura de mensajes
            */
         this.entradaSocket = new InputStreamReader(s.getInputStream());
         this.entrada = new BufferedReader(entradaSocket);
            /**
            * creacion de salida de datos para el envio de mensajes
            */
         this.salida = new DataOutputStream(s.getOutputStream());
          MerkleHellman m = new MerkleHellman();
        m.LlavePrivada();
        m.GenerarSumatoriaW();
        System.out.println("q=" + m.GenearQ());
        System.out.println("r=" + m.GenerarR());
        m.LlavePublica();    
         while(true){
              for(int i=0;i<text.length();i++){
            m.encriptar(text.charAt(i)); 
             for(int x=0;x<text.length();x++){
               System.out.println("cedena desecncriptada "+m.desencriptar(text.charAt(x)));
               //text=m.desencriptar(text.charAt(x));
                
           }
        }
             text = this.entrada.readLine();
             System.out.println(text);
             VServidor.jTextArea1.setText(VServidor.jTextArea1.getText()+"\n"+text);      
         }
         }catch (IOException e){
         System.out.println("Algun Tipo de error");
         };
    }
      /**
      * metodo para leer los mensajes enviados desde un cliente
      */
    public String leerMSG(){
        try{
            return this.entrada.readLine();
        }catch(IOException e){};
        return null;
    }
      /**
      * metodo para desconectar el servidor y el cliente
      */
    public void desconectar(){
        try{
            s.close();
        }catch(IOException e){};
        try{
            ss.close();
        }catch(IOException e){};
    }
}