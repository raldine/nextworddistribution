package nxwd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {

     //listen from console for word to find

     WordInfo givenWord = new WordInfo(args[0].toLowerCase().trim());


    //import and read file by word - remember to remove special characters especially "

            
        Reader reader = new FileReader("littlewomen.txt");
        BufferedReader br = new BufferedReader(reader);


        ArrayList<String> bookWholeInWord = new ArrayList<>();
        String line = "x";

        while (null!=line){

            line = br.readLine();

            if(null==line)
            break;

           
            String lineRemov = line.replaceAll(",", "").replaceAll("\"","").replaceAll("_", "").replaceAll("\\p{Punct}", "").toLowerCase();
            String[] lineTrans = lineRemov.trim().split(" ");

            for (String word : lineTrans){

                bookWholeInWord.add(word);

            }

        }

    reader.close();
    br.close();

    System.out.printf("current word count before removing stopwords is %d\n", bookWholeInWord.size());


    // remove stopwords from linkedlist?

    Reader readstop = new FileReader("stopwords.txt");
    BufferedReader brstop = new BufferedReader(readstop);

    List<String> outPutStopList = new ArrayList<>();

    String lineStop = "x";

        while (null!=lineStop){

            lineStop = brstop.readLine();

            if(null==lineStop)
            break;

           
            String lineRemov = lineStop.replaceAll(",", "").replaceAll("\"","").toLowerCase();
            String[] lineTrans = lineRemov.trim().split(" ");

            for (String word : lineTrans){

                outPutStopList.add(word);

            }

        }

    readstop.close();
    brstop.close();
    
    // System.out.println(outPutStopList);

    // for (String word : bookWholeInWord){
    //     for (String stopword : outPutStopList ){
    //         if (word.equals(stopword)){
    //             bookWholeInWord.remove(word);
    //         }
    //     }
    // } // couldnt use iterator above, must use iterator below

    Iterator<String> iterator = bookWholeInWord.iterator();

    
    while (iterator.hasNext()) {
        String element = iterator.next();
        for (String stopword : outPutStopList){
            if (element.equals(stopword)) {
                iterator.remove();  // Use iterator's remove method
            }       
        }

    }

    System.out.printf("current word count AFTER removing stopwords is %d\n", bookWholeInWord.size());
    

    //evaluation of next word distribution - math behind is here: https://towardsai.net/p/nlp/how-do-language-models-predict-the-next-word
    //find given word in already filtered linkedlist + find how many times it occur
    
    givenWord.findOccrIn(bookWholeInWord);
    



    //store next words in hashMap so tht it holds key = word, value = 

    givenWord.getListNxWord(bookWholeInWord);



    //probability calculation

    givenWord.calcProbNxWord();

    givenWord.nextWordAns();















    }// end of main
}