package crossword.dictionary;

import java.util.LinkedList;

import static crossword.dictionary.BoardCell.Position.START;

public class Board {
    private BoardCell[][] board;
    private int width;
    private int height;

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public BoardCell getCell(int x, int y) {
        return this.board[x][y];
    }

    public void setCell(int x, int y, BoardCell b){
        this.board[x][y] = b;
    }

    public LinkedList<BoardCell> getStartCells(){
        LinkedList<BoardCell> result = new LinkedList<>();
        for(int i=0 ; i<width ; ++i){
            for(int j=0 ; j<height ; ++j){
                if(this.board[i][j].getP() == START){
                    result.add(this.board[i][j]);
                }
            }
        }
        return result;
    }

    public String createPattern(int fromx, int fromy, int tox, int toy) {
        String pattern = "";
        //CwEntry.Direction d = (fromx == tox)? CwEntry.Direction.HORIZ: CwEntry.Direction.VERT;
        int wordLen = tox - fromx + toy - fromy + 1;

        for (int i = 0; i < wordLen; ++i) {
            if (fromx == tox) {
                if (this.board[fromx][i].getContent() != null) {
                    pattern = pattern + this.board[fromx][i].getContent();
                } else
                    pattern = pattern + "\\D";
            } else {
                if (this.board[i][fromy].getContent() != null) {
                    pattern = pattern + this.board[i][fromy].getContent();
                } else
                    pattern = pattern + "\\D";
            }
        }
        return pattern;
    }

}
