package Administration;

import java.util.ArrayList;
import java.util.List;

public class AdminUnit{
    public String name;
    int adminLevel;
    double population;
    public double area;
    double density;
    public AdminUnit parent;
    BoundingBox bbox;
    List<AdminUnit> children = new ArrayList<>();

    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("NAZWA: ");
        buf.append(name);
        buf.append("\n");
        buf.append("POPULACJA: ");
        buf.append(population);
        buf.append("\n");
        buf.append("POWIERZCHNIA: ");
        buf.append(area);
        buf.append("\n");
       buf.append("JEDNOSTKA NADRZEDNA: ");
        if(parent != null)
            buf.append(parent.name);
        else
            buf.append("brak");
        if(bbox != null)
            buf.append("\n"+bbox.toString());
        buf.append("\n\n");
        return buf.toString();
    }
}
