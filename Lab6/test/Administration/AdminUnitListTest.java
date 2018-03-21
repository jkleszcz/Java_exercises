package Administration;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class AdminUnitListTest {
    private AdminUnitList a;
    @Before
    public void createAdminUnitListInstance(){
        a = new AdminUnitList();
        a.read("admin-units.csv");
    }

    @Test
    public void adminUnitsFileReading(){
        assertEquals(a.units.size(),15265);
    }

    @Test
    public void listWholeAdminUnitsFile(){
        a.list(System.out);
    }

    @Test
    public void listAdminUnitsFileWithOffsetAndLimit(){
        a.list(System.out,15260,100);
    }

    @Test
    public void listAdminUnitFileWithIncorrectOffset(){
        a.list(System.out,20000,100);
    }

    @Test
    public void selectByNameWithoutRegex(){
        AdminUnitList wojewodztwa = a.selectByName("województwo",false);
        wojewodztwa.list(System.out);
        assertEquals(wojewodztwa.units.size(),16);
    }

    @Test
    public void selectByNameWithRegex(){
        a.selectByName(".*ewództwo ma.*",true).list(System.out);
    }

    @Test
    public void getNeighbors(){
        AdminUnit klecza = a.selectByName("Klecza Dolna");
        a.getNeighbors(klecza,20).list(System.out);
    }

    @Test
    public void getNeighborsMethodsTimeComparation(){

        double t1 = System.nanoTime()/1e6;
        a.getNeighbors(a.selectByName("powiat wadowicki"),20);
        double t2 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"Using searching algorithm: %f\n",t2-t1);

        double t3 = System.nanoTime()/1e6;
        a.getNeighborsIterator(a.selectByName("powiat wadowicki"),20);
        double t4 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"Searching by units list iteration: %f\n",t4-t3);

        assertEquals(a.getNeighborsIterator(a.selectByName("powiat wadowicki"),20).units.size(),
                a.getNeighborsIterator(a.selectByName("powiat wadowicki"),20).units.size());
    }

    @Test
    public void sortingByName(){
        a.sortInPlaceByName().list(System.out);
    }

    @Test
    public void sortingByArea(){
        a.sortInPlaceByArea().list(System.out);
    }

    @Test
    public void sortingByPopulation(){
        a.sortInPlaceByPopulation().list(System.out);
    }

    @Test
    public void filterWithPredicate(){
        Predicate<AdminUnit> p2 = i -> i.name.startsWith("powiat");
        Predicate<AdminUnit> p3 = i -> i.parent.name.equals("województwo małopolskie");
        a.filter(p2.and(p3)).list(System.out);
    }
}