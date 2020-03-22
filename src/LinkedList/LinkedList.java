/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkedList;

/**
 *
 * @author zalve
 */

    //      [EXTRAS]
    //  list.Size()- get the size
    //  list.get(index)    - To access an element in the List
    //  list.set(index,value)    - change an item in the list with an index 
    //  list.clear() - Removes all the value from the list [reset]
    //  list.contains(value) - checks if the list has the value 
    //  list.isEmpty() - checks the list is empty or not 
    //  
    
    //     [ ENQUEUE ]
    //  list.push(value);  This method can add one or more elements at the end of the list;
    //  list.unshift(value);   It add one or more elements to the beginning of the list;
    //  list.Insert(index,value) It add one or more elements to the list and insert it to a given index 
    
    //      [DEQUEUE]
    //   list.pop() This method removes the last element from the given array and returns it.
    //   list.Shift() It delete the first element from the given array and returns it.
    //   list.remove(Index) It delete the index element from the given array and returns it.

public class LinkedList<T>{
    Node head;
    private int size;
    /*
         ___                  _    
        / __| ___ __ _ _ _ __| |_  
        \__ \/ -_) _` | '_/ _| ' \ 
        |___/\___\__,_|_| \__|_||_|                  
    */
    public boolean isEmpty(){
        return size == 0;
    }
    public boolean contains(T value){
        
        if(size<=0){
            return false;
        }
        Node n = head;
        while(n.next != null){
            if((value+"").equals(n.data+""))
                return true;
            n = n.next;
        }
        return false;
    }
    public void clear(){
        head = null;
        size = 0 ;
    }
    public T get(int index){
        
        if(size >=0 && index <size && size >=0){
            Node n = head;
            for(int i = 0 ; i < index ; i++){
                n = n.next;
            }
            return (T) n.data;
        }else{
            return null;
        
        }
    }
    
    public void set(int index , T value){
        Node val = new Node();
        val.data = value;
        val.next  =null;
        if(index  == 0 & size >0){
         
         Node node = new Node();
         //Convert the data into node
         node.data = value;
         // Point the pointer to the head 
         node.next = head;
         // set the head to data 
         head = node;
         Node n= head;
         n.next = n.next.next;

            
        }else if(size >0 && index <size ){
           
           
           Node n = head;
           for(int i = 1 ; i < index ; i ++){
               n = n.next;
           } 
           Node shft = n.next.next;
           n.next = val;
           n.next.next = shft;
       }
    }
    
    
    /* =============================================
     ___                             
    |   \ ___ __ _ _  _ ___ _  _ ___ 
    | |) / -_) _` | || / -_) || / -_)
    |___/\___\__, |\_,_\___|\_,_\___|
                |_|                  
     ============================================= */
    
    
    //pop()
    public T remove(int index){
        if(index == 0){
            return shift();
        } else if(index < size){
            Node node = head;
            for(int i = 1 ; i < index ; i ++){
                node = node.next; 
            } 
            T val = (T) node.next.data;
            node.next = node.next.next;
            size--;
            return val;
        }else{
            return null;
        }
    }
    
    public T pop(){
        Node node = head;
        
        if(size>1){
            while (node.next.next != null){
                node = node.next;
            }
            T val = (T) node.next.data;
            node.next = node.next.next;
            size --;
            return val;
        }else if(size == 1){
            Node n = new Node();
            T val = (T) n.data;
            head = n.next;
            size --;
            return val;
        }
        return null;
    }
    
    public T shift(){
        T val = (T) head.data;
        head = head.next;
        size--;
        return val;
        
    }
    
   
    /*
     ___                             
    | __|_ _  __ _ _  _ ___ _  _ ___ 
    | _|| ' \/ _` | || / -_) || / -_)
    |___|_||_\__, |\_,_\___|\_,_\___|
    */
    
    // PUSH =============================================
    public void push(T value){
        Node node = new Node();
        // Converts value into node
        node.data = value;
        // Create its own pointer
        //node.next = null;
        if(head == null){
            head =node;
        }else{
            // Call the recursive functions
            _push(node,head);
        }
        size ++;
    }
    public Node _push(Node value,Node curNode){
        // If not null Iterate 
        if(curNode.next != null){
            return _push(value,curNode.next);
        }
        curNode.next = value;
        return value;
    }
        
     // PUSH =============================================   
        
    
    // Unshift()=============================================  
    /*
        This method is similar to push() method, the only difference is that
        it works at the beginning of the array. It add one or more elements
        to the beginning of an array.
    */
    public void unshift(T value){
        // Call the list 
        Node node = new Node();
        //Convert the data into node
        node.data = value;
        // Point the pointer to the head 
        node.next = head;
        // set the head to data 
        head = node;
        size ++;
    }
    
    public void Insert(int index,T value){
        if(index == 0){
            unshift(value);
        }
        else if(index <= size && index >0){
            size++;
            Node node = new Node();
            node.next = null ;
            node.data = value;
            Node n = head;
            for(int i = 0 ; i < index-1 ; i ++){
                n = n.next;
            }
            Node nextSlide = n.next;
            n.next = node;
            n.next.next= nextSlide;
            while(n.next != null){
                n = n.next;
            }

        }else{
            System.out.println("  : OutOfBounce Exception ");
        }
    }
    
    
  
    //========================================================================================
    //                                     [EXTRAS]
    //========================================================================================
    
    public String toString(){
        if(size <=0)
            return "{ }";
        Node node = head;
        String print = "";
        while(node.next != null){
            print += node.data+" ";
            node = node.next;
        }
       print += node.data+" ";
        return "[ "+print+"]";
    }
    
    
  
    public int size(){
        return this.size;
    }
    
    
    //========================================================================================
    //========================================================================================
    
}


