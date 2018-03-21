package Cv;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Document {
    @XmlElement
    String title;
    @XmlElement
    Photo photo;
    @XmlElement(name="section")
    List<Section> sections = new ArrayList<>();

    Document(){
        this.title = null;
    }

    Document(String title){
        this.title = title;
    }

    Document setTitle(String title){
        this.title = title;
        return this;
    }

    Document addPhoto(String photoUrl){
        this.photo = new Photo(photoUrl);
        return this;
    }

    Section addSection(String sectionTitle){
        Section nsection = new Section();
        nsection.setTitle(sectionTitle);
        this.sections.add(nsection);
        return nsection;
    }

    Document addSection(Section s){
        this.sections.add(s);
        return this;
    }

    void writeHTML(PrintStream out){
        out.printf("<html><body><h1>" + this.title + "</h1>");
        photo.writeHTML(out);
        for (Section s : sections)
            s.writeHTML(out);
        out.printf("</body></html>");

    }

    ////////////////////Kontynuacja/////////////////////

    public void write(String fileName){
        try {
            JAXBContext jc = JAXBContext.newInstance(Document.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            FileWriter writer= new FileWriter(fileName);;
            m.marshal(this, writer);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    public static Document read(String fileName){
        try {
            JAXBContext jc = JAXBContext.newInstance(Document.class);
            Unmarshaller m = jc.createUnmarshaller();
            FileReader reader = new FileReader(fileName);
            return (Document) m.unmarshal(reader);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
