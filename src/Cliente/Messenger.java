
package Cliente;


public class Messenger {
    
    public static Conector servidor,cliente;
    public static void main (String[] args){
        VCliente cliente = new VCliente();
        cliente.show();
    }
    
    public static void initCliente(String ip)
    {
        cliente = new Conector(ip);
        //cliente.enviarMSG(ip);
        cliente.start();
    }
    
}
