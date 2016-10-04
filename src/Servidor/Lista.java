/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.util.Random;
/**
 *
 * @author Usersone
 */
public class Lista <T extends Comparable<T>>{
    private Nodo<T> inicio;
    /**
     * setter y geter
    */
    public void setInicio(Nodo<T> inicio){
        this.inicio=inicio;
    }
    public Nodo<T> getInicio(){
        return inicio;
    }
     /**
      * Agregar inicio
    */
    public void AgregarInicio(T dato){
        Nodo<T> nuevo=new Nodo<T>(dato);
        nuevo.setSiguiente(inicio);
        inicio=nuevo;
    }
    /**
     * Agregar al final
    */
    public void InsertarFinal(T dato){
        /**
         * solo servira aqui...
         */
        Nodo<T> temporal;
        temporal=inicio;
        /**
         * si la lista esta vacia se generara un NullPointerException
         */
        if(inicio==null){
            this.AgregarInicio(dato);
        }
        else{
            while(temporal.getSiguiente()!=null){
                temporal=temporal.getSiguiente();
            }
            temporal.setSiguiente(new Nodo(dato));
        }
    }
    /**
     * Agregar un dato antes de otro dato
    */
    public void AgregarAntesDE(T dato){
        Nodo <T> temporal,nodoEncontrado = null,nuevo;
        boolean band=true;
        temporal=inicio;
        while(dato.compareTo(temporal.getDato())==-1&&band){
            if(temporal.getSiguiente()!=null){
                nodoEncontrado=temporal;
                temporal=temporal.getSiguiente();
            }
            else{
                band=false;
            }
        }
        if(band){
            if(temporal==inicio){
                AgregarInicio(dato);
            }
            else{
                nuevo=new Nodo(dato);
                nuevo.setSiguiente(temporal);
                nodoEncontrado.setSiguiente(nuevo);  
            }
        }
        else {
            InsertarFinal(dato);
        }
    }

    /**
     * metodo para insertar un dato despues de cierto dato
     */
    public void InsertarDespuesDe(T dato, T referencia){
        Nodo<T> temporal, nuevo;
        boolean band=true;
        temporal= inicio;
        while(temporal.getDato()!=referencia && band){
            if(temporal.getSiguiente()!=null){
                nuevo=temporal;
                temporal=temporal.getSiguiente();  
            }
            else{
                band=false;
                System.out.println("no se encontro el elemento...\n");
            }  
        }
        if(band){
            if(temporal==inicio){              
            }
            else{
                nuevo=new Nodo(dato);
                nuevo.setSiguiente(temporal.getSiguiente());
                temporal.setSiguiente(nuevo);
            }
        }
    }

    /**
     * metodo para eliminar el primer elemento
     */
    public void EliminaPrimero(){
        Nodo<T> temporal=inicio;
        if(temporal.getSiguiente()!=null){
            inicio=temporal.getSiguiente();
        }
        else{
            inicio=null;
        }
    }

    /** 
     * metodo para eliminar el ultimo elemnto de la lista
     */
    public void EliminaUltimo(){
        Nodo<T> temporal,anterior =null;
        temporal=inicio;
        if(inicio.getSiguiente()==null){
            inicio=null;
        }
        else{
            while(temporal.getSiguiente()!=null){
                anterior=temporal;
                temporal=temporal.getSiguiente();    
            }
            anterior.setSiguiente(null);
        }
    }
    /**
     * eliminar parte de la lista depues de cierto dato
    */
    public void EliminadespuesDe(T referencia){
        Nodo<T> temporal, nuevo,anterior=null;
        boolean band=true;
        temporal= inicio;
        while(temporal.getDato()!=referencia && band){
            if(temporal.getSiguiente()!=null){
                nuevo=temporal;
                anterior=temporal;
                temporal=temporal.getSiguiente();     
            }
            else{
                band=false;
                System.out.println("no se encontro el elemento...\n");
            } 
        }
        if(band){
            if(temporal==inicio){    
            }
            else{
                nuevo=new Nodo(referencia);                 
                anterior.setSiguiente(nuevo);
            }    
        }    
    }
    /**
     * medodo para recorrer la lista 
    */
    public void Recorrer_R(Nodo<T> auxiliar){
        if(auxiliar!=null) {
            System.out.println(auxiliar.getDato());
            Recorrer_R(auxiliar.getSiguiente());
        } 
    }
    /**
     * Metodo para recorrer la lista en forma ierativa  
    */
    public void Recorrei(){
        Nodo<T> temporal=inicio;
        if(inicio==null){
            System.out.println("Lista vacia");
        }
        else{
            while(temporal!=null){
                System.out.print(temporal);
                temporal=temporal.getSiguiente();
            }
        }
        System.out.print(temporal);
        System.out.println();
    }
    /**
     * metodo para buscar un dato en la lista
    */
    public Nodo<T> busca(T m){
        Nodo<T> temporal=inicio,encontrado = null;
        boolean band=true;
        while(temporal.getDato().compareTo(m)!=0){
            if(temporal.getSiguiente()!=null){

                temporal=temporal.getSiguiente();
                //temporal=encontrado.getSiguiente();
            }else{
                band=false;
            }
        }
        if(band){
            return temporal;
        }
        else{
            return null;
        }
    }
    /**
     * este metodo insertara un dato antes de otro  
     * 
     */
    public void InsertarAntesDe(T dato, T referencia){
        Nodo<T> temporal,nodoEncontrado, nuevo;
        boolean band=true;
        temporal= inicio;
        while(temporal.getDato()!=referencia && band){
            if(temporal.getSiguiente()!=null){
                nodoEncontrado=temporal;
                temporal=temporal.getSiguiente();    
            }
            else{
                band=false;
                System.out.println("no se encontro el elemento...\n");
            }
        }
         if(band){
                if(temporal==inicio){    
                }
                else{
                    nodoEncontrado=temporal;
                     nuevo=new Nodo(dato);
                    nuevo.setSiguiente(temporal);
                    nodoEncontrado.setSiguiente(nuevo); 
                }
            }
    }/** 
     * metodo para sobreescribir el tipo de dato en la clase...
     */
     public String toString(){
   
   Nodo<T> temporal=inicio;
    String s="";
     while(temporal!=null){
       s+="["+temporal.getDato()+"]"+"-->";
       temporal=temporal.getSiguiente();
   }
    return s+""+null;
    }
     /**
      * otro metodo para buscar un dato
      */
     public Nodo<T> buscar(T m){
        Nodo<T> aux = inicio;
        while(aux != null){
              if(aux.getDato().compareTo(m) == 0)      
                 return aux;
              aux = aux.getSiguiente();
        } 
        System.out.println(aux);
        return null;
     }
}