package CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {
    String filename;
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;
    String[] current;

    List<String> columnLabels = new ArrayList<>();
    Map<String,Integer> columnLabelsToInt = new HashMap<>();

    public CSVReader(){

    }
    public CSVReader (String filename, String delimiter){
        try{
            reader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException f){
            System.out.println(f.getMessage());
        }
        this.reader = reader;
        this.filename = filename;
        this.delimiter = delimiter;
    }
    public CSVReader (String filename){
        try{
            reader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException f){
            System.out.println(f.getMessage());
        }
        this.reader = reader;
        this.filename = filename;
    }

    public CSVReader(String filename,String delimiter,boolean hasHeader) {
        try {
            reader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException f){
            System.out.println(f.getMessage());
        }
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if(hasHeader)
            parseHeader();
    }
    public CSVReader(BufferedReader reader, String delimiter, boolean hasHeader){
        this.reader = reader;
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if (hasHeader)
            parseHeader();
    }

    void parseHeader() {
        String line = null;
        try {
            line = reader.readLine();
        }catch (IOException f){
            System.out.println(f.getMessage());
        }
        if (line == null) {
            return;
        }
        String[] header = line.split(delimiter,-1);
        for (int i = 0; i < header.length; i++) {
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i],i);
        }
    }

    public boolean next(){
        String nextLine = null;
        try {
            nextLine = reader.readLine();
        }catch(IOException f){
            return false;
        }

        if (nextLine == null) {
            return false;
        }
        String[] header = nextLine.split(delimiter,-1);
        this.current = header;
        return true;
    }

    List<String> getColumnLabels(){
        return columnLabels;
    }

    int getRecordLength(){
        return this.current.length;
    }

    boolean isMissing(int columnIndex){
        return this.current[columnIndex].isEmpty();
    }

    public boolean isMissing(String columnLabel){
        return this.current[this.columnLabelsToInt.get(columnLabel)].isEmpty();
    }

    String get(int columnIndex){
        if(columnIndex >= this.current.length)
            throw new NullPointerException(String.format("Column not found"));
        return this.current[columnIndex];
    }

    public String get(String columnLabel){
        if(!columnLabelsToInt.containsKey(columnLabel))
            throw new IllegalArgumentException(String.format("Column not found"));
        return this.current[this.columnLabelsToInt.get(columnLabel)];
    }

    int getInt(int columnIndex){
        if(columnIndex >= this.current.length)
            throw new NullPointerException(String.format("Column not found"));
        return Integer.parseInt(this.current[columnIndex]);
    }

    public int getInt(String columnLabel){
        if(!columnLabelsToInt.containsKey(columnLabel))
            throw new IllegalArgumentException(String.format("Column not found"));
        return Integer.parseInt(this.current[this.columnLabels.indexOf(columnLabel)]);
    }

    public long getLong(int columnIndex){
        if(columnIndex >= this.current.length)
            throw new NullPointerException(String.format("Column not found"));
        return Long.parseLong(this.current[columnIndex]);
    }

    public long getLong(String columnLabel){
        if(!columnLabelsToInt.containsKey(columnLabel))
            throw new IllegalArgumentException(String.format("Column not found"));
        return Long.parseLong(this.current[this.columnLabels.indexOf(columnLabel)]);
    }

    double getDouble(int columnIndex){
        if(columnIndex >= this.current.length)
            throw new NullPointerException(String.format("Column not found"));
        return Double.parseDouble(this.current[columnIndex]);
    }

    public double getDouble(String columnLabel){
        if(!columnLabelsToInt.containsKey(columnLabel))
            throw new IllegalArgumentException(String.format("Column not found"));
        return Double.parseDouble(this.current[this.columnLabels.indexOf(columnLabel)]);
    }

}
