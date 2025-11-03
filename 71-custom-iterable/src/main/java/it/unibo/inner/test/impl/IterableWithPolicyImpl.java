package   it.unibo.inner.test.impl;


import java.util.Iterator;


import it.unibo.inner.api.IterableWithPolicy;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private T[] array;
    public IterableWithPolicyImpl(T[] array){
        this.array = array;
    }
    @Override
    public void setIterationPolicy(it.unibo.inner.api.Predicate<T> filter) {
        
        throw new UnsupportedOperationException("Unimplemented method 'setIterationPolicy'");
    }
    
    public Iterator<T> iterator(){
        return this.new IteratorP();
    }
    public class IteratorP implements Iterator<T> {

        private int index ;

        public boolean hasNext(){
            return this.index < array.length;
        }
        public T next(){
            if(hasNext()){
                
                return array[index++];
                
            }
            else{
                throw new UnsupportedOperationException("out of array");
            }
        }
    }
   
    

}
