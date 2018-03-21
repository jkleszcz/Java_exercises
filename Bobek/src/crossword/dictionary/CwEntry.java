package crossword.dictionary;

public class CwEntry extends Entry{
    public enum Direction{
        HORIZ, VERT;
    }

    private int x;
    private int y;
    private Direction d;

    public CwEntry(String word, String clue, int x, int y, Direction d){
        super(word, clue);
        this.x = x;
        this.y = y;
        this.d = d;
    }
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public Direction getDir(){
        return this.d;
    }

}
