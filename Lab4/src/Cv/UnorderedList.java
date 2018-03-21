package Cv;

import javax.xml.bind.annotation.XmlElement;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {

    @XmlElement
    List<ListItem> items = new ArrayList<>();

    UnorderedList addItem(ListItem item){
        items.add(item);
        return this;
    }
    UnorderedList addItem(String item){
        ListItem i = new ListItem(item);
        this.items.add(i);
        return this;
    }

    void writeHTML(PrintStream out){
        out.printf("<ul>");
        for(ListItem i : items)
            i.writeHTML(out);
        out.printf("</ul>");
    }
}
