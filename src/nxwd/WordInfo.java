package nxwd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;



public class WordInfo {

    public String wordToEval;
    public int wordOccur;
    public Map<String, Integer> nxWrL = new HashMap<>();
    public LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
    public LinkedHashMap<String, Double> sortedPMap = new LinkedHashMap<>();
    public boolean inBook; 


    // how to create sorted hashmap https://www.digitalocean.com/community/tutorials/sort-hashmap-by-value-java

    public ArrayList<Integer> sortingList = new ArrayList<>();


    

    public WordInfo(String word){

        this.wordToEval = word;
        this.inBook = false;





    }

    public void findOccrIn(ArrayList<String> list ){

        for(String word : list){

            if(this.wordToEval.equals(word)){

                this.wordOccur++;

            }

        }

        if(wordOccur == 0){
            this.inBook = false;
            System.out.printf("the word %s is not found in the book\n", this.wordToEval);
        } else {
            this.inBook = true;
            System.out.printf("the word %s occured %d times\n", this.wordToEval, this.wordOccur);
        }

    }


    public void getListNxWord(ArrayList<String> list){
         if (this.inBook == false){
            return; // exit method if word not even in book
         }
    

        //method finds next word, make list of next words and sort them by occurance
        String nextWord;
        int wordMatchI;
        int nextWordI;
        String wordFmList;

        for(int i = 0; i < list.size(); i++){

            wordFmList = list.get(i);

            if(wordFmList.equals(this.wordToEval)){
                

                nextWordI = i+1;
                
                if (nextWordI < list.size()){
                nextWord = list.get(nextWordI);
                } else {
                    nextWord = "null";
                }

                if(nxWrL.containsKey(nextWord)){
                    //if word is in the list
                    int currentCount = nxWrL.get(nextWord);
                    currentCount++;
                    this.nxWrL.put(nextWord, currentCount);

                } else {
                    //if word is not in list
                    this.nxWrL.put(nextWord, 1);
                }
            }
 
        }
    // remove unwanted

    this.nxWrL.remove("");

    // System.out.println(this.nxWrL);

    for (Map.Entry<String, Integer> entry : this.nxWrL.entrySet()) {
        sortingList.add(entry.getValue());
    }

    Collections.sort(sortingList); // this sorts values by ascending order
    for (int num : sortingList) {
        for (Entry<String, Integer> entry : this.nxWrL.entrySet()) {
            if (entry.getValue().equals(num)) {
                this.sortedMap.put(entry.getKey(), num);
            }
        }
    }
    System.out.println("");
    System.out.println("");
    System.out.println("");
    // System.out.println(this.sortedMap);

    }// end of getListNxWord

public void calcProbNxWord(){
    if (this.inBook == false){
        return; // exit method if word not even in book
    }

    String keyword;
    double value;
    double probValue;
    double probValueR;

    for ( Map.Entry<String, Integer> entry : this.sortedMap.entrySet()){
        keyword = entry.getKey();
        value = entry.getValue();

        probValue = value/this.wordOccur;
        probValueR = Math.round(probValue * 1000.0) / 1000.0; // Rounds to 3 decimal places


        this.sortedPMap.put(keyword, probValueR);

    }
    System.out.println(this.sortedPMap);
    System.out.println("");
    System.out.println("");
    System.out.println("");


}


public void nextWordAns(){
    if (this.inBook == false){
        return; // exit method if word not even in book
     }
    LinkedList<String> listOfWords = new LinkedList<>();
    LinkedList<Double> listOfValues = new LinkedList<>();
    String keyword;
    double value;

    for ( Map.Entry<String, Double> entry : this.sortedPMap.entrySet()){
        listOfWords.add(entry.getKey());
        listOfValues.add(entry.getValue());

    }

    System.out.printf("The most likely word after %s is %s at %f percent\n", this.wordToEval, listOfWords.getLast(), listOfValues.getLast());

}










    
}
