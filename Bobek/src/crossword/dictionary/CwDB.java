package crossword.dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class CwDB {
    protected LinkedList<Entry> dict = new LinkedList<>();
    String filename;

    public CwDB(String filename) throws IOException {
        this.filename = filename;
        createDB(this.filename);
    }

    public void add(String word, String clue){
        dict.add(new Entry(word, clue));
    }

    public Entry get(String word){
        for(Entry e : dict){
            if(e.getWord().equals(word))
                return e;
        }
        return null;
    }

    public void remove(String word){
        for (Entry e : dict){
            if(e.getWord().equals(word)){
                dict.remove(e);
                break;
            }
        }
    }

    //public void saveDB(String filename){}
    //public int getSize(){}

    protected void createDB(String filename) throws IOException {
        FileReader read = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(read);

        try{
            String word = bufferedReader.readLine();
            while(word != null){
                String clue = bufferedReader.readLine();
                add(word, clue);
                word = bufferedReader.readLine();
            }
        }finally {
            bufferedReader.close();
        }
    }
}
