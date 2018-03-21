package Administration;

import java.util.Comparator;
import java.util.function.Predicate;

public class AdminUnitQuery {
    AdminUnitList src;
    Predicate<AdminUnit> p = a->true;
    Comparator<AdminUnit> cmp;
    int limit = Integer.MAX_VALUE;
    int offset = 1;


    public AdminUnitQuery selectFrom(AdminUnitList src){
        this.src = src;
        return this;
    }

    public AdminUnitQuery where(Predicate<AdminUnit> pred){
        this.p = pred;
        return this;
    }

    public AdminUnitQuery and(Predicate<AdminUnit> pred){
        this.p = p.and(pred);
        return this;
    }

    public AdminUnitQuery or(Predicate<AdminUnit> pred){
        this.p = p.or(pred);
        return this;
    }

    public AdminUnitQuery sort(Comparator<AdminUnit> cmp){
        this.cmp = cmp;
        return this;
    }

    public AdminUnitQuery limit(int limit){
        this.limit = limit;
        return this;
    }

    public AdminUnitQuery offset(int offset){
        this.offset = offset;
        return this;
    }

    public AdminUnitList execute(){

        System.out.println("looool");
        return this.src.filter(this.p, this.offset, this.limit).sort(this.cmp);
    }
}
