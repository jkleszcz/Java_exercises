package Cv;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class SectionTest {
    @Test
    public void writeHTML() throws Exception {
        String title = "Wyksztalcenie";
        String paragraph1 = "To jest pierwszy paragraf";
        Paragraph paragraph2 = new Paragraph();
        paragraph2.setContent("To jest kolejny paragraf");
        // Utwórz strumień zapisujący w pamięci
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        // Utwórz obiekt i zapisz do strumienia
        Section sec = new Section();
        sec.setTitle(title).addParagraph(paragraph1).addParagraph(paragraph2);
        sec.writeHTML(ps);
        String result = null;
        // Pobierz jako String
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //System.out.println(result);

        // Sprawdź, czy result zawiera wybrane elementy
        assertTrue(result.contains("<h1>"));
        assertTrue(result.contains(title));
        assertTrue(result.contains("</h1>"));
        assertTrue(result.contains("<p>"));
        assertTrue(result.contains(paragraph1));
        assertTrue(result.contains(paragraph2.content));
        assertTrue(result.contains("</p"));
    }

}