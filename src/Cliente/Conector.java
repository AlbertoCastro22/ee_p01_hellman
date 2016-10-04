package Cliente;
import java.net.*;
import java.io.*;
public class Conector extends Thread{
    private Socket s;
    private ServerSocket ss;
    private InputStreamReader entradaSocket;
    private DataOutputStream salida;
    private BufferedReader entrada;
    final int puerto =8181;
    /**
      * constructor al que se le pasa como parametro la ip..
      * tambien se iniciara la entrada para la lectura de mensajes y
      * a la vez se creara la sali   da para enviar los mensajes...
      */
    public Conector(String ip){
        try{
            this.s = new Socket(ip,this.puerto);
            /**
            * creacion de entrada de datos para la lectura de mensajes
            */
            this.entradaSocket = new InputStreamReader(s.getInputStream());
            this.entrada = new BufferedReader(entradaSocket);
            /**
            * creacion de salida de datos para el envio de mensajes
            */
            this.salida= new DataOutputStream(s.getOutputStream());
            this.salida.writeUTF("Conectado- \n");     
        }catch (Exception e){};
    }
      /**
      * metodo run para que se haga nuestro chat,es decir para que envie y reciba los mensajes
      */
    public void run(){
        String texto;
        while(true){
            try{
                 /**
            * creacion de entrada de datos para la lectura de mensajes y se pueda leer en el camo de texto 
            * de el chat...
            */
            texto = entrada.readLine();
            VCliente.jTextArea1.setText(VCliente.jTextArea1.getText()+"\n"+ texto);
        }catch(IOException e){};
        }
    }
      /**
      * metodo para enviar un mensaje desde el el cliente
      */
    public void enviarMSG(String msg){
        try{
             System.out.println("enviado");
          MerkleHellman m = new MerkleHellman();
        m.LlavePrivada();
        String su=msg;
        m.GenerarSumatoriaW();
        System.out.println("q=" + m.GenearQ());
        System.out.println("r=" + m.GenerarR());
        m.LlavePublica();
        String cadenita="";
        this.salida = new DataOutputStream(s.getOutputStream());
        for(int i=0;i<su.length();i++){
               m.encriptar(su.charAt(i));
             for(int x=0;x<su.length();x++){
             
           cadenita+=m.desencriptar(su.charAt(x)); 
       
           } 
        } 
        this.salida.writeUTF(cadenita+"\n"); 
        }catch (IOException e){
            System.out.println("Problema al enviar");
        };
    }
     /**
      * metodo para leer los mensajes enviados desde un cliente
      */
    public String leerMSG(){
        try{
            return entrada.readLine();
        }catch(IOException e){};
        return null;
    }   
}