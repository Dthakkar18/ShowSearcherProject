// --== CS400 Project One File Header ==--
// Name: Daven Thakkar 
// CSL Username: daven
// Email: dcthakkar@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: <any optional extra notes to your grader>


/**
 * Class that tests the HashTableSortedSets class
 * @author Daven Thakkar
 *
 */
public class AlgorithmEngineerTests {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println("addTest: " + addTest());
    System.out.println("getTest: " + getTest());
    System.out.println("sizeTest: " + sizeTest());
    System.out.println("containsTest: " + containsTest());
    System.out.println("removeTest: " + removeTest());

  }
  
  /**
   * Testing add method
   * @return true if test pass and false otherwise
   */
  public static boolean addTest() { 
    
    try {
      
      // creating keys and values
      String keyOne = "Shrek";
      Integer valueOne = 1;
      
      String keyTwo = "Shrek";
      Integer valueTwo = 2;
      
      String keyThree = "Ted";
      Integer valueThree = 4;
      
      
      // create map
      HashTableSortedSets<String, Integer> map = new HashTableSortedSets<>();
      
      //adding values in map
      map.add(keyOne, valueOne);
      map.add(keyTwo, valueTwo);
      map.add(keyThree, valueThree);
      
      //checking size of map
      if(map.size() != 3) {
        return false;
      }
      
      
    }catch(Exception e) {
      return false; // no exception should be thrown
    }
    
    return true; 
  }
  
  /**
   * Testing get method
   * @return true if test pass and false otherwise
   */
  public static boolean getTest() {  
    
    try {
      
      // creating keys and values
      String keyOne = "Shrek";
      Integer valueOne = 1;
      
      String keyTwo = "Shrek";
      Integer valueTwo = 2;
      
      String keyThree = "Ted";
      Integer valueThree = 4;
      
      
      // create map
      HashTableSortedSets<String, Integer> map = new HashTableSortedSets<>();
      
      //adding values in map
      map.add(keyOne, valueOne);
      map.add(keyTwo, valueTwo);
      map.add(keyThree, valueThree);
      
      //checking size of map
      if(map.size() != 3) {
        return false;
      }
      
      // getting each value from value list of corresponding key 
      int valueOneNum = map.get(keyOne).get(1); // valueOne 
      int valueTwoNum = map.get(keyOne).get(0); // valueTwo (gets added to the list of keyOne HashObject and when sorted this shifts to first)
      int valueThreeNum = map.get(keyThree).get(0); // valueThree
      
      // checking if the values are the right ones
      if (valueOneNum != valueOne) {
        return false;
      }
      if(valueTwoNum != valueTwo) {
        return false;
      }
      if(valueThreeNum != valueThree) {
        return false;
      }
      
      
    }catch(Exception e) {
      return false; // no exception should be thrown
    }
    
    return true; 
    
  }
  
  
  /**
   * Testing size method
   * @return true if test pass and false otherwise
   */
  public static boolean sizeTest() {
    
    try {
      
      // creating keys and values
      String keyOne = "Shrek";
      Integer valueOne = 1;
      
      String keyTwo = "Tony";
      Integer valueTwo = 5;
      
      String keyThree = "Ted";
      Integer valueThree = 4;
      
      
      // create map
      HashTableSortedSets<String, Integer> map = new HashTableSortedSets<>();
      
      //adding key value paires and cheching size each time
      map.add(keyOne, valueOne);
      if(map.size() != 1) {
        return false;
      }
      map.add(keyTwo, valueTwo);
      if(map.size() != 2) {
        return false;
      }
      map.add(keyThree, valueThree);
      if(map.size() != 3) {
        return false;
      }
      
      
    }catch(Exception e) {
      return false; // no exception should be thrown
    }
    
    
    return true; 
    
  }
  
  
  /**
   * Testing contains method
   * @return true if test pass and false otherwise
   */
  public static boolean containsTest() {  
    
    try {
      
      // creating keys and values
      String keyOne = "Shrek";
      Integer valueOne = 1;
      
      String keyTwo = "Tony";
      Integer valueTwo = 5;
      
      String keyThree = "Ted";
      Integer valueThree = 4;
      
      
      // create map
      HashTableSortedSets<String, Integer> map = new HashTableSortedSets<>();
      
      //adding key value paires and cheching if the contains is working everytime
      map.add(keyOne, valueOne);
      if(map.containsKey(keyOne) == false) {
        return false;
      }
      map.add(keyTwo, valueTwo);
      if(map.containsKey(keyTwo) == false) {
        return false;
      }
      map.add(keyThree, valueThree);
      if(map.containsKey(keyThree) == false) {
        return false;
      }
      
      // making sure contains is false with a key not in map
      String keyFour = "Bob";
      if(map.containsKey(keyFour)) {
        return false;
      }
      
      
    }catch(Exception e) {
      return false; // no exception should be thrown
    }
    
    return true; 
    
  }
  
  /**
   * Testing remove method
   * @return true if test pass and false otherwise
   */
  public static boolean removeTest() {  
    
    try {
      
      // creating keys and values
      String keyOne = "Shrek";
      Integer valueOne = 1;
      
      String keyTwo = "Tony";
      Integer valueTwo = 5;
      
      String keyThree = "Ted";
      Integer valueThree = 4;
      
      
      // create map
      HashTableSortedSets<String, Integer> map = new HashTableSortedSets<>();
      
      //adding key value paires and making sure they are in the map
      map.add(keyOne, valueOne);
      map.add(keyTwo, valueTwo);
      map.add(keyThree, valueThree);
      if(map.containsKey(keyOne) == false) {
        return false;
      }
      if(map.containsKey(keyTwo) == false) {
        return false;
      }
      if(map.containsKey(keyThree) == false) {
        return false;
      }
      
      // removing each node and making sure it is not in map
      map.remove(keyOne);
      map.remove(keyTwo);
      map.remove(keyThree);
      if(map.containsKey(keyOne)) {
        return false;
      }
      if(map.containsKey(keyTwo)) {
        return false;
      }
      if(map.containsKey(keyThree)) {
        return false;
      }
      
      
      
    }catch(Exception e) {
      return false; // no exception should be thrown
    }
    
    
    return true; 
    
  }

}
