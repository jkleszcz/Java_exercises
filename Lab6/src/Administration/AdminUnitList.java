package Administration;

import CSVReader.CSVReader;

import java.io.PrintStream;
import java.text.Collator;
import java.util.*;
import java.util.function.Predicate;

public class AdminUnitList{
    List<AdminUnit> units = new ArrayList<>();
    Map<Long,AdminUnit> unitsID = new HashMap<>();
    Map<AdminUnit,Long> unitsParent = new HashMap<>();
    Map<Long,List<AdminUnit>>parentid2child = new HashMap<>();

    public void read(String filename){
        CSVReader reader = new CSVReader(filename,",",true);
        while(reader.next()){
            AdminUnit unit = new AdminUnit();
            unitsID.put(reader.getLong("id"),unit);
            if(!reader.isMissing("parent"))
                unitsParent.put(unit,reader.getLong("parent"));
            unit.name = reader.get("name");
            if(!reader.isMissing("admin_level"))
                unit.adminLevel = reader.getInt("admin_level");
            if(!reader.isMissing("population"))
                unit.population = reader.getDouble("population");
            if(!reader.isMissing("area"))
                unit.area = reader.getDouble("area");
            if(!reader.isMissing("density"))
                unit.density = reader.getDouble("density");
            if(!reader.isMissing("x1") && !reader.isMissing("y1") &&
                    !reader.isMissing("x3") && !reader.isMissing("y3"))
                unit.bbox = new BoundingBox(reader.getDouble("x1"), reader.getDouble("y1"),
                    reader.getDouble("x3"),reader.getDouble("y3"));

            units.add(unit);
        }
        for(AdminUnit n:units){
            if(unitsParent.containsKey(n)) {
                n.parent = unitsID.get(unitsParent.get(n));
                n.parent.children.add(n);
                //Dodaje do parent2child tylko te elementy, ktore maja dzieci
                if(!parentid2child.containsKey(unitsParent.get(n)))
                    parentid2child.put(unitsParent.get(n),n.parent.children);
            }

        }
        for(AdminUnit n:units){
            if(n.population!=0)
                continue;
            fixMissingValues(n);
        }
    }

    public void list(PrintStream out){
        for(AdminUnit n:units)
            out.printf(n.toString());
    }

    public void list(PrintStream out,int offset, int limit ){
        int end = (offset+limit <= units.size())? offset+limit:units.size();
        for(int i = offset ; i<end ; ++i)
            out.printf(units.get(i).toString());
    }

    public AdminUnitList selectByName(String pattern, boolean regex){
        AdminUnitList ret = new AdminUnitList();
        for(AdminUnit n:units){
            if((regex && n.name.matches(pattern)) || (!regex && n.name.contains(pattern)))
                ret.units.add(n);
        }
        return ret;
    }

    public AdminUnit selectByName(String pattern){
        for(AdminUnit n:units){
            if(pattern.equals(n.name))
                return n;
        }
        return null;
    }

    private void fixMissingValues(AdminUnit au){
        if(au.parent.density != 0){
            au.density = au.parent.density;
            au.population = au.area * au.density;
        }else
            fixMissingValues(au.parent);
    }
    public AdminUnitList getNeighbors(AdminUnit unit, double maxdistance){
        if(unit.bbox == null)
            throw new NullPointerException("Bounding Box is not implemented");

        AdminUnitList neighbors = new AdminUnitList();
        if(unit.parent == null){
            for(Long key : parentid2child.keySet()){
                if(unit.bbox.intersects(unitsID.get(key).bbox) && unitsID.get(key).adminLevel == unit.adminLevel
                        && unitsID.get(key) != unit)
                    neighbors.units.add(unitsID.get(key));
            }
        }
        else{
            for (AdminUnit n : unit.parent.children) {
                if ((unit.bbox.intersects(n.bbox) || (unit.adminLevel>7 && unit.bbox.distanceTo(n.bbox) <= maxdistance))
                        && n.adminLevel == unit.adminLevel && n != unit)
                    neighbors.units.add(n);
            }

            for(AdminUnit n : this.getNeighbors(unit.parent,maxdistance).units){
                for(AdminUnit m:n.children) {
                    if ((unit.bbox.intersects(m.bbox) || (unit.adminLevel>7 && unit.bbox.distanceTo(m.bbox) <= maxdistance))
                            && m.adminLevel == unit.adminLevel && m != unit)
                        neighbors.units.add(m);
                }
            }
        }
        return neighbors;
    }



    //Metoda używana wyłącznie w celach testowych. Powinna być bardziej kosztowna niż getNeighbors, ale daje taki sam wynik
    public AdminUnitList getNeighborsIterator(AdminUnit unit, double maxdistance){
        if(unit.bbox == null)
            throw new NullPointerException("Bounding Box is not implemented");

        AdminUnitList neighbors = new AdminUnitList();
        for(AdminUnit n:units){
            if(n.bbox != null && (unit.bbox.intersects(n.bbox) || (unit.adminLevel>1 && unit.bbox.distanceTo(n.bbox) <= maxdistance))
                    && n.adminLevel == unit.adminLevel && n != unit)
                neighbors.units.add(n);
        }
        return neighbors;
    }

    public AdminUnitList sortInPlaceByName(){
        class Comp implements Comparator<AdminUnit>{
            @Override
            public int compare(AdminUnit a, AdminUnit b){
                Collator c = Collator.getInstance(new Locale("pl", "PL"));
                return c.compare(a.name,b.name);
            }
        }
        Comp c = new Comp();
        this.units.sort(c);
        return this;
    }

    public AdminUnitList sortInPlaceByArea(){
        this.units.sort(new Comparator<AdminUnit>() {
            @Override
            public int compare(AdminUnit a, AdminUnit b) {
                return Double.compare(a.area,b.area);
            }
        });
        return this;
    }

    public AdminUnitList sortInPlaceByPopulation(){
        this.units.sort((a,b)->Double.compare(a.population,b.population));
        return this;
    }

    AdminUnitList sortInPlace(Comparator<AdminUnit> cmp){
        this.units.sort(cmp);
        return this;
    }

    public AdminUnitList sort(Comparator<AdminUnit> cmp){
        AdminUnitList result = new AdminUnitList();
        result.units = this.units;
        result.unitsID = this.unitsID;
        result.unitsParent = this.unitsParent;
        result.parentid2child = this.parentid2child;
        result.sortInPlace(cmp);
        return result;
    }

    public AdminUnitList filter(Predicate<AdminUnit> pred){
        AdminUnitList result = new AdminUnitList();
        for (AdminUnit u:units){
            if(pred.test(u))
                result.units.add(u);
        }
        return result;
    }

    public AdminUnitList filter(Predicate<AdminUnit> pred, int limit){
        AdminUnitList result = new AdminUnitList();
        for (AdminUnit u:units){
            if(pred.test(u))
                result.units.add(u);
            if(result.units.size()==limit)
                break;
        }
        return result;
    }

    public AdminUnitList filter(Predicate<AdminUnit> pred, int offset, int limit){
        AdminUnitList filtered = new AdminUnitList();
        filtered = filter(pred,limit);
        AdminUnitList result = new AdminUnitList();
        int end = (offset+limit <= filtered.units.size())? offset+limit:filtered.units.size();
        for(int i = offset ; i<end ; ++i)
            result.units.add(filtered.units.get(i));
        return result;
    }

}
