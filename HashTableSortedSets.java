
// --== CS400 Project One File Header ==--
// Name: Daven Thakkar 
// CSL Username: daven
// Email: dcthakkar@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: <any optional extra notes to your grader>

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Class that extends HashtableMap and implements IHashTableSortedSets interface
 * @author Daven Thakkar
 *
 * @param <KeyType> type for the key
 * @param <ValueType> type for the value
 */
public class HashTableSortedSets<KeyType, ValueType extends Comparable<ValueType>> extends HashtableMap<KeyType, List<ValueType>> implements IHashTableSortedSets<KeyType, ValueType>{


  
  
  /**
  * This add method is different from the put() method in that it appends a
  * single value to the end of the list associated with a given key.  If a
  * key does not yet have a list of values associated with it, then a new 
  * one will be created when this method is called.
  * @param key used to later lookup the list containing this value
  * @param value associated with the previous key
  */ 
  public void add(KeyType key, ValueType value){
    
    //creating list, adding the value and passing that as the "value" for HashObject
    List<ValueType> list = new ArrayList<ValueType>();
    list.add(value);
    
    // HashObject is created and put the list as value 
    HashObject<KeyType, List<ValueType>> pair = new HashObject<KeyType, List<ValueType>>(key, list);
    
    // hashed index is found for that key
    int index = hashFunctionHelper(key);
    
    //checking if the map contains that key somehere already
    boolean hasInMap = containsKey(key);
    
    //if there isn't a key at all then just add it to the ll at corresponding hashed index 
    if(hasInMap == false) {
      array[index].add(pair);
    }else { // if there is already something in that LinkedList go and check if one of the nodes in that ll has the same key
      
      // getting ll from that index to check if one of the nodes has the same key 
      LinkedList<HashObject<KeyType, List<ValueType>>> templl = array[index];
      
      
      // if there is multiple nodes at the same hashed index
      // traverse through to find which node's value list you want to add to by
      // finding the node with the same key
      for(HashObject<KeyType, List<ValueType>> tempNode : templl) { 
        // if the node we are at has the same key as the node we want to add then  
        if(tempNode.getKey().equals(key)) {
          tempNode.getValue().add(value); // add into existing list of values within the node that is already there that has same key
          //sort that list of values 
          Collections.sort(tempNode.getValue(), Collections.reverseOrder()); // not use if this is how to sort the list of values
        }
      }
      // if there were no nodes within that ll that had the same key then just add to ll
      templl.add(pair);
      

    }
    
  }
  
}
