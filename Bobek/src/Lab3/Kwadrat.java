package Lab3;

public class Kwadrat extends Shape{
    int edge;

    Kwadrat(String name, int edge){
        super(name);
        this.edge = edge;
    }

    public void draw(){
        for(int i = 0 ; i<edge ; ++i){
            for(int j=0 ; j<edge ; ++j){
                if(i==0 || i == edge-1 || j == 0 || j == edge-1){
                    System.out.print(" * ");
                }else{
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
    }
}
