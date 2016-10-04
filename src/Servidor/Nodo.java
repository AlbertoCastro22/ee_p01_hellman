
package Servidor;

/**
 *
 * @author Usersone
 */
public class Nodo<T> {
    private T dato;
    private Nodo<T> siguiente;
     public Nodo (T dato){
        this.dato=dato;
        siguiente=null;
    }
    public void setDato(T dato){
        this.dato=dato;
    }
    public   T getDato(){
        return dato;
    }
    public void setSiguiente(Nodo<T> siguiente){
        this.siguiente=siguiente;
    }
    public Nodo <T> getSiguiente(){
        return siguiente;
    }
     public String toString(){
        String s="";
        s+="["+dato+"]-->";
    return s;
    }
}