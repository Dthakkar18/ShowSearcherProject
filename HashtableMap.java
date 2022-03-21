// --== CS400 Project One File Header ==--
// Name: Daven Thakkar 
// CSL Username: daven
// Email: dcthakkar@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: <any optional extra notes to your grader>

import java.util.NoSuchElementException;
import java.util.LinkedList;

/**
 * This class represents an object that contains a key and value to be stored in a hash table.
 * It is a helper class for the HashtableMap.java class
 * 
 * @author Daven Thakkar
 *
 */
class HashObject<KeyT, ValueT> { // do I need to change this to --> HashTable<KeyT, List<ValueT>>

  // data fields hold the key and value for each key-value pair in a hashtable.
  protected KeyT key;
  protected ValueT value; // do I need to change this to --> List<ValueT>[] value

  /**
   * This constructor creates a HashObject that holds a key-value pair
   * 
   * @param k the key
   * @param v the value
   */
  public HashObject(KeyT k, ValueT v) {
    key = k;
    value = v;
  }

  /**
   * This method returns the key that is stored in the HashObject
   * 
   * @return
   */
  public KeyT getKey() {
    return key;
  }

  /**
   * This method returns the value that is stored in the HashObject
   */
  public ValueT getValue() {
    return value;
  }

}


/**
 * This class implements the MapADT interface and represents a hash table and contains its
 * associated algorithms
 * 
 * @author aaronhou
 *
 * @param <KeyType>   data type of the key
 * @param <ValueType> data type of the values
 */
public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

  // single dimensional array representing a hashtable of HashObjects
  protected LinkedList<HashObject<KeyType, ValueType>>[] array; // do change this too --> LinkedList<HashObject<KeyType, List<ValueType>>>[] array

  /**
   * Constructor creates a Hashtable Map, instantiates array with the capacity provided
   * 
   * @param capacity represents the initial capacity of the array
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public HashtableMap(int capacity) {

    array = new LinkedList[capacity];
    array = (LinkedList<HashObject<KeyType, ValueType>>[]) (array); // do I change this to --> (LinkedList<HashObject<KeyType, List<ValueType>>>[])
    for (int i = 0; i < array.length; i++) {
      array[i] = new LinkedList();
    }
  }

  /**
   * Constructor that instantiates the array with a default capacity of 20
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public HashtableMap() {

    array = new LinkedList[20];
    array = (LinkedList<HashObject<KeyType, ValueType>>[]) (array);
    for (int i = 0; i < array.length; i++) {
      array[i] = new LinkedList();
    }
  }

  /**
   * This method inserts a value to its designated index in the hash table. The method calculates
   * where it is inserted and resizes array when necessary by calling helper methods.
   */
  public boolean put(KeyType key, ValueType value) {

    // returns false if the key is null
    if (key == null) {
      return false;
    }

    // traverses through the hashtable and returns false if key-value pair is already stored in
    // hashtable
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[i].size(); j++) {
        if (array[i].get(j).getKey().equals(key)) {
          return false;
        }
      }
    }

    // key is put through hash function helper method and calculated
    int resultIndex = hashFunctionHelper(key);

    // HashObject is created and stored in the hashtable
    HashObject<KeyType, ValueType> pair = new HashObject<KeyType, ValueType>(key, value);
    array[resultIndex].add(pair);

    int count = this.size();

    // calculates load factor and tests whether it's greater than or equal to 75% 
    // assigns array data field to 2x size array if load factor >= 75% using helper method
    double percentage = (double) count / array.length;
      if (percentage >= 0.75) {
        array = resizeHelper();
    }
      
    return true;
  }

  /**
   * Helper method helps calculate index given key
   * 
   * @param key the key for the key-value pair
   * @return index for where the pair will be inserted
   */
  public int hashFunctionHelper(KeyType key) {
    int resultIndex = Math.abs(key.hashCode()) % array.length;
    return resultIndex;
  }

  /**
   * This method rehashes everything in the current array into an array 2x size and returns that array
   * @return
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public LinkedList<HashObject<KeyType, ValueType>>[] resizeHelper() {


    // create temp array and re hash everything from current array into hash array
    LinkedList<HashObject<KeyType, ValueType>>[] tempArray = new LinkedList[array.length * 2];
    tempArray = (LinkedList<HashObject<KeyType, ValueType>>[]) (tempArray);
   
    for (int i = 0; i < tempArray.length; i++) {
      tempArray[i] = new LinkedList();
    }
    
    for (int i = 0; i < array.length; i++) {
      for(int j = 0 ; j < array[i].size() ; j++) {
        this.put(array[i].get(j).getKey(), array[i].get(j).getValue());
      }
    }
    return tempArray;
  }

  /**
   * method returns the value that correlates to the provided key
   * 
   * @throws NoSuchElementException if there is no node in the array that contains the provided key.
   */
  public ValueType get(KeyType key) throws NoSuchElementException {

    for (int i = 0; i < array[hashFunctionHelper(key)].size(); i++) {
      if (array[hashFunctionHelper(key)].get(i).getKey().equals(key)) {
        return array[hashFunctionHelper(key)].get(i).getValue();
      }
    }
    throw new NoSuchElementException();
  }

  /**
   * method returns the number of key value pairs in the hash table in an int
   */
  public int size() {

    int count = 0;
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[i].size(); j++) {
        count++;
      }
    }
    return count;
  }


  /**
   * method returns true if the hashtable contains a key-value pair with the same key as the one
   * inputed into the parameter, false otherwise
   */
  public boolean containsKey(KeyType key) {

    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[i].size(); j++) {
        if (array[i].get(j).getKey().equals(key)) {
          return true;
        }
      }
    }
    return false;

  }

  /**
   * Method removes the key value pair from hash table that has the key inputed into parameter,
   * returns the value associated with that pair. Returns null if when key is non-existent in hash
   * table.
   */
  public ValueType remove(KeyType key) {

    // goes to the corresponding index of the array which the Key is suppose to be within the LinkedList at that index
    for (int i = 0; i < array[hashFunctionHelper(key)].size(); i++) {
      if (array[hashFunctionHelper(key)].get(i).getKey().equals(key)) {
        ValueType valueTemp = array[hashFunctionHelper(key)].get(i).getValue();
        array[hashFunctionHelper(key)].remove(i);
        return valueTemp;
      }
    }
    return null;
  }


  /**
   * method removes all key-value pairs stored in the collection without changing the underlying
   * array capacity
   */
  public void clear() {
    for (int i = 0; i < array.length; i++) {
      array[i].clear();
    }
  }
}

