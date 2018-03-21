package crossword.dictionary;

import java.io.IOException;
import java.util.LinkedList;

public class Main {
    public static void main(String[] argv) throws IOException {
        InteliCwDB k = new InteliCwDB("cwdb.txt");
        /*for(Entry e : k.dict){
            System.out.println(e.getWord());
        }*/

        LinkedList<Entry> found = new LinkedList<>();
        found = k.findAll("B\\Dg\\D\\Dn\\D");

        for(Entry e:found){
            System.out.println(e.getWord());
        }
    }
}
