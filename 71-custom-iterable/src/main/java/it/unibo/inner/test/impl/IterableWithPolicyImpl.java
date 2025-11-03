package   it.unibo.inner.test.impl;

import java.util.Iterator;

import it.unibo.inner.api.Predicate;
import it.unibo.inner.api.IterableWithPolicy;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private T[] array;
    private Predicate<T> filter;
    public IterableWithPolicyImpl(T[] array){
        
        this(array , new Predicate<T>() {
            public boolean test(T t){
                return true;
            }
        });
    }
    public IterableWithPolicyImpl(T[] array , Predicate<T> p){
        this.array = array;
        this.filter = p;
        //boolean b = filter.test(array[0]);
    }
    @Override
    public void setIterationPolicy(it.unibo.inner.api.Predicate<T> filter) {
        
        this.filter = filter;
    }
    
    public Iterator<T> iterator(){
        return this.new IteratorP();
    }
    public class IteratorP implements Iterator<T> {

        private int index ;

        public boolean hasNext(){
            // TODO pensare a un modo per far partire e terminsre l'iteratore solo se è true il filtro...

            while(this.index < array.length){
               if(filter.test(array[index]) ){
                 return this.index +1 <= array.length;
               }else{
                 index++;
                 
                }
            
            }
            return false;
        }   
        public T next(){
            
            return array[index++];
                
                
            }
            
        
    }
   
    

}
