package crossword.dictionary;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InteliCwDB extends CwDB {
    public InteliCwDB(String filename) throws IOException {
        super(filename);
    }

    public LinkedList<Entry> findAll(String pattern){
        LinkedList<Entry> sublist = new LinkedList<>();
        Pattern pat = Pattern.compile(pattern);
        for(Entry e : dict){
            Matcher matcher = pat.matcher(e.getWord());
            if(matcher.matches())
                sublist.add(e);
        }
        return sublist;
    }

    //public Entry getRandom(){}

    public Entry getRandom(int length){
        return getRandom("\\D{"+length+"}");
    }

    public Entry getRandom(String pattern){
        Random generator = new Random();
        return findAll(pattern).get(generator.nextInt(findAll(pattern).size()));
    }

    public void add(String word, String clue){
        for(int i = 0 ; i<dict.size()+1 ; ++i){
            if(i == dict.size()){
                dict.add(new Entry(word,clue));
                break;
            }
            if(word.compareToIgnoreCase(dict.get(i).getWord())>0)
                continue;
            else {
                dict.add(i, new Entry(word, clue));
                break;
            }
        }
    }
}
