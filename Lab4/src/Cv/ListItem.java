package Cv;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;
import java.io.PrintStream;

public class ListItem {
    @XmlValue
    String content;

    ListItem(String item){
        this.content = item;
    }

    void writeHTML(PrintStream out){
        out.printf("<li>" + this.content + "</li>");
    }
}
