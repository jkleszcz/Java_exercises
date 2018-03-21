package crossword.dictionary;

public class BoardCell {
    public enum Position{
        START, INNER, END;
    }

    private boolean enable;
    private CwEntry.Direction d;
    private Position p;
    private String content;

    public BoardCell(boolean enable, CwEntry.Direction d, Position p){
        this.enable = enable;
        this.d = d;
        this.p = p;
    }

    public Position getP() {
        return p;
    }

    public void setEnable(boolean e){
        this.enable = e;
    }

    public void setD(CwEntry.Direction d){
        this.d = d;
    }

    public void setP(Position p){
        this.p = p;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getContent(){
        return this.content;
    }
}
