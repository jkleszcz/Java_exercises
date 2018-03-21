package Cv;

import javax.xml.bind.annotation.XmlElement;
import java.io.PrintStream;

public class ParagraphWithList extends Paragraph {

    @XmlElement
    UnorderedList Items = new UnorderedList();

    ParagraphWithList(){

    }

    ParagraphWithList(String content){
        this.content = content;
    }

    ParagraphWithList addItemToList(ListItem item){
        this.Items.addItem(item);
        return this;
    }

    ParagraphWithList addItemToList(String item){
        this.Items.addItem(item);
        return this;
    }

    ParagraphWithList setContent(String s){
        this.content = s;
        return this;
    }

    void writeHTML(PrintStream out){
        out.printf("<p>");
        this.Items.writeHTML(out);
        out.printf("</p>");
    }


}
