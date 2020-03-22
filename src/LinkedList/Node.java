/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkedList;

public class Node<T> {
    public T data;
    public Node previous;
    public Node next;
    
    public Node(){
        this.previous = null;
        this.data = null;
        this.next = null;
    }
    public  Node(T data){
        this.previous = null;
        this.data = data;
        this.next = null;
    }
    public Node(Node previous , T data ,  Node next){
        this.previous = previous;
        this.data = data;
        this.next = next;
    }
}
