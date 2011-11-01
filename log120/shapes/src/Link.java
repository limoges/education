public class Link<T> {                          
                                                
  private T obj;                                
  private Link<T> next;                         
  private int index;                            
                                                
  public Link(T obj, int index, Link<T> next) { 
    this.obj = obj;                             
    this.next = next;                           
  }                                             
                                                
  public String toString() {                    
    return "Link(" + obj.toString() +")";       
  }                                             
                                                
  public void set(T obj) {
    this.obj = obj;
  }

  public void setNext(Link<T> next) {           
    this.next = next;                           
  }                                             
                                                
  public boolean hasNext() {                    
    return next != null;                        
  }                                             
                                                
  public Link<T> next() {                       
    return next;                                
  }                                             
                                                
  public T get() {                           
    return obj;                                 
  }                                             
                                                
}                                               

