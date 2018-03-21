package CSVReader;

import Administration.AdminUnit;
import Administration.AdminUnitList;
import Administration.AdminUnitQuery;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        CSVReader nowy = new CSVReader("admin-units.csv",",",true);

        AdminUnitList nowa = new AdminUnitList();
        nowa.read("admin-units.csv");

        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(nowa)
                .where(a->a.area>1000)
                .or(a->a.name.startsWith("Sz"))
                .sort((a,b)->Double.compare(a.area,b.area))
                .limit(100);
        query.execute().list(System.out);
    }

    Predicate<AdminUnit> x = a->a.area > 10;
}
