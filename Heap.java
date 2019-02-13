package project3;

import java.util.ArrayList;

/** 
 * Heap class-blueprint for heap object
 * @author julian
 * @param <T> is the generic object that will make up the heap
 */
public class Heap <T extends Comparable<T>>
{
    /** The collection where the items will be stored*/
    private ArrayList<T> heap;
    
    /** 
    * Constructor-creates objects of Heap type
    */    
    public Heap()
    {
        heap=new ArrayList<>();
    }
    
    /** 
    * Gets the size of the collection
    * @return the size of the list
    */
    public int size()
    {
        return heap.size();
    }
    
    /** 
    * Checks if collection contains items
    * @return false if list is not empty and true if it is
    */    
    public boolean isEmpty()
    {
        return heap.isEmpty();
    }
    
    /** 
    * Gets location of parent element
    * @param i the index of the child element 
    * @return the location of the parent 
    */ 
    private int getPLoc(int i)
    {
        return (i-1)/2;
    }
    
    /** 
    * Gets location of left child
    * @param i the index of the parent element 
    * @return the location of the left child
    */ 
    private int getLCLoc(int i)
    {
        return 2*i+1;
    }
    
    /** 
    * Gets location of right child
    * @param i the index of the parent element 
    * @return the location of the right child
    */ 
    private int getRCLoc(int i)
    {
        return 2*i+2;
    }
    
    /** 
    * Gets the element stored in the heap
    * @param i the index of the desired element
    * @return the generic object
    */ 
    private T getItem(int i)
    {
        return heap.get(i);
    }
    
    /** 
    * Adds elements to heap
    * @param t the element to add
    */ 
    public void addItem(T t)
    {                      
        heap.add(null);        
        int index=heap.size()-1;
        
        while(index>0 && getItem(getPLoc(index)).compareTo(t)<0)
        {
            heap.set(index, getItem(getPLoc(index)));
            index=getPLoc(index);
        }
        heap.set(index, t);
    }
    
    /** 
    * Gets the elements in the heap
    * @return the elements as a single string
    */ 
    @Override
    public String toString() 
    {
        //returns string in tree order, not sorted order. Adds new line after each item
        String s="";
        for(int i=0;i<heap.size();i++)
        {
            s+=heap.get(i)+"\n";
        }
        return s;
    }   
    
    /** 
    * Gets the element at the top of the heap
    * @return the top element at index 0
    */ 
    public T getCurrent()
    {
        return heap.get(0); 
    }
    
    /** 
    * Removes element from top of heap and re-adjusts the tree order 
    * @return the item that was removed
    */ 
    public T removeItem()
    {   
        T min=heap.get(0);
        int index=heap.size()-1;
        T last=heap.remove(index);
        
        if(index>0)
        {
            heap.set(0,last);
            T root=heap.get(0);
            int end=heap.size()-1;
            index=0;
            boolean done=false;
            while(!done)
            {
                if(getLCLoc(index)<=end)//left exists
                {
                    T child=getItem(getLCLoc(index));
                    int childLoc=getLCLoc(index);
                    
                    if(getRCLoc(index)<=end)//right exists
                    {
                        if(getItem(getRCLoc(index)).compareTo(child)>0)
                        {
                            child=getItem(getRCLoc(index));
                            childLoc=getRCLoc(index);
                        }                                            
                    }
                    
                    if(root.compareTo(child)<0)
                    {
                        heap.set(index, child);
                        index=childLoc;
                    }
                    
                    else
                    {
                        done=true;
                    }
                }
                else
                {
                    done=true;                            
                }
            }
            heap.set(index, root);
        }
        return min;
    }
}

