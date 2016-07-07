/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fortune;

import java.awt.*;
import java.util.ArrayList;

class MapFindingPath {

    protected IMapCheckPoint checkPoint;

    protected boolean toEnd;

    protected MapPath path;

    protected ArrayList<PathPoint> possiblePoints;

    protected ArrayList<PathPoint> usedPoints;

    public void setcheckPoint(IMapCheckPoint checkPoint) {
        this.checkPoint = checkPoint;
    }

    public MapPath getPath() {
        return path;
    }

    public boolean findPath(int startx, int starty, int endx, int endy) {
        toEnd = false;
        path = new MapPath();
        usedPoints = new ArrayList<PathPoint>();
        possiblePoints = new ArrayList<PathPoint>();

        PathPoint p = new PathPoint(startx, starty, -1, -1, Math.abs(startx - endx) + Math.abs(starty - endy), true);
        usedPoints.add(p);
        possiblePoints.add(p);
        PathPoint node;
        while (possiblePoints.size() > 0) {
            node = possiblePoints.get(0);
            possiblePoints.remove(0);

            if (node.x == endx && node.y == endy) {
                makePatch(node);
                toEnd = true;
                break;
            } else {
                node.visited = true;
                addNode(node, node.x + 1, node.y, endx, endy);
                addNode(node, node.x - 1, node.y, endx, endy);
                addNode(node, node.x, node.y + 1, endx, endy);
                addNode(node, node.x, node.y - 1, endx, endy);
            }
        }

        usedPoints = null;
        possiblePoints = null;

        return toEnd;
    }

    protected void makePatch(PathPoint node) {
        path.clear();
        while (node.px != -1) {
            path.add(new Point(node.x, node.y));
            for (PathPoint p : usedPoints) {
                if (p.x == node.px && p.y == node.py) {
                    node = p;
                    break;
                }
            }
        }
    }

    protected void addNode(PathPoint node, int x, int y, int endx, int endy) {

        if (checkPoint.check(x, y)) {
            int cost = Math.abs(x - endx) + Math.abs(y - endy);
            PathPoint px = new PathPoint(x, y, node.x, node.y, cost, false);
            PathPoint old = null;

            for (PathPoint p : usedPoints) {
                if (p.x == px.x && p.y == px.y) {
                    old = p;
                    break;
                }
            }

            if (old == null || old.cost > cost) {
                usedPoints.add(px);
                int i = 0;
                for (; i < possiblePoints.size(); i++) {
                    if (cost < possiblePoints.get(i).cost) {
                        possiblePoints.add(i, px);
                        break;
                    }
                }

                if (i >= possiblePoints.size()) {
                    possiblePoints.add(px);
                }
            }
        }

    }
}

class PathPoint {

    public int x, y, px, py, cost;
    public boolean visited;

    public PathPoint(int x, int y, int px, int py, int cost, boolean visited) {

        setData(x, y, px, py, cost, visited);
    }

    final public void setData(int x, int y, int px, int py, int cost, boolean visited) {
        this.x = x;
        this.y = y;
        this.px = px;
        this.py = py;
        this.cost = cost;
        this.visited = visited;

    }
} 
