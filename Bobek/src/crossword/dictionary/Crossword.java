package crossword.dictionary;

import java.util.Iterator;
import java.util.LinkedList;

public class Crossword {
    private LinkedList<CwEntry> entries;
    private Board b;
    private InteliCwDB cwdb;
    private final long ID = -1;

    //public Iterator<CwEntry> getROEntryIter();

    public Board getBoardCopy(){
        return b;
    }

    public InteliCwDB getCwdb() {
        return cwdb;
    }

    public void setCwdb(InteliCwDB Cwdb){
        this.cwdb = Cwdb;
    }

    public boolean contains(String word){

    }

    public final void addCwEntry(CwEntry cwe, Strategy s){
        entries.add(cwe);
        s.updateBoard(b,cwe);
    }

    public final void generate(Strategy s){
        Entry e = null;
        while((e = s.findEntry(this)) != null){
            addCwEntry(e,s);
        }
    }
}
