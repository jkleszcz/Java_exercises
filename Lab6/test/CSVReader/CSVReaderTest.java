package CSVReader;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CSVReaderTest {

    @Test
    public void ReadFileWithHeader(){
        CSVReader read = new CSVReader("with-header.csv",";",true);
        List<String> header = new ArrayList<>();
        header = read.getColumnLabels();
        while(read.next()){
            for(String h:header)
                System.out.print(read.get(h)+"\t");
            System.out.println("");
        }
    }

    @Test
    public void ReadFileWithoutHeader(){
        CSVReader read = new CSVReader("no-header.csv",";",false);
        while(read.next()){
            for(int i=0 ; i<6 ; ++i)
                System.out.print(read.get(i)+"\t");
            System.out.println("");
        }
    }

    @Test
    public void MissingValues(){
        CSVReader read = new CSVReader("missing-values.csv",";",true);
        while(read.next()){
            System.out.print(read.get("population"));
            System.out.println("");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void NonexistentColumnByName(){
        CSVReader read = new CSVReader("with-header.csv",";",true);
        read.get("Nonexist Column");
    }

    @Test(expected = NullPointerException.class)
    public void NonexistentColumnByIndex(){
        CSVReader read = new CSVReader("no-header.csv",";",false);
        read.get(10);
    }
}