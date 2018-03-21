package Cv;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class ParagraphWithListTest {
    @Test
    public void writeHTML() throws Exception {
        ParagraphWithList lista = new ParagraphWithList();
        lista.addItemToList("C++");
        lista.addItemToList("Java");
        // Utwórz strumień zapisujący w pamięci
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        // Utwórz obiekt i zapisz do strumienia
        lista.writeHTML(ps);
        String result = null;
        // Pobierz jako String
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //System.out.println(result);

        // Sprawdź, czy result zawiera wybrane elementy
        assertTrue(result.contains("<ul>"));
        assertTrue(result.contains("<li>"));
        assertTrue(result.contains("C++"));
        assertTrue(result.contains("Java"));
        assertTrue(result.contains("</li>"));
        assertTrue(result.contains("</ul>"));

    }

}