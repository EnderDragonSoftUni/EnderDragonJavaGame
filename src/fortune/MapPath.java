package fortune;

import java.awt.*;
import java.util.ArrayList;

public class MapPath {

    public ArrayList<Point> path;
    public Point p;
    protected int index = 0;
    
    public MapPath() {
        path = new ArrayList<Point>();
    }

    public void clear() {
        path.clear();
    }

    public void add(Point p) {
        path.add(p); 
    }

    public void startMove(int posX, int posY) {
        p = new Point(posX, posY);
        index = path.size()-1;
    }

    public boolean hasNext() {
       return (index > -1); 
    }

    public Point nextPos(int step) {
        int x = path.get(index).x * BaseTile.SIZE,
            y = path.get(index).y * BaseTile.SIZE; 
        if(p.x != x)  {
            p.x += step * ((x < p.x) ? -1: 1);
        } 
        if(p.y != y)  {
            p.y += step * ((y < p.y) ? -1: 1);
        } 
            
        if(p.x == x && p.y == y) {
            index --; 
        } 

        return p;
    }
}
