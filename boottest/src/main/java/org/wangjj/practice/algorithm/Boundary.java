package org.wangjj.practice.algorithm;

import java.util.*;

/**
 * ClassName: Bandary <br/>
 * Function: <br/>
 * Reason:  <br/>
 * date: 2020/12/15 下午10:27 <br/>
 *
 * @author wangjunjie
 * @since JDK test.8
 */
public class Boundary {

    private int value[][];

    private int rowCount = 0, colCount = 0;
    private List<Point> onHandPoint = new ArrayList<>();

    private int curHeight = 0;
    private int boundaryHeight = 0;
    private int minHeight = 0;
    private int maxHeight = 0;

    private int cap = 0;

    private Map<Integer, List<Point>> peakPointsMap = new HashMap<>();
    private Map<Integer, List<Point>> prePeakPointsMap = new HashMap<>();
    private Map<Integer, List<Point>> valleyPointsMap = new HashMap<>();

    private class Point implements Comparable<Point>{
        int x;
        int y;
        boolean isExcept = false;
        int skyHeight = 0;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public boolean isExcept() {
            return isExcept;
        }

        public void setExcept(boolean except) {
            isExcept = except;
        }

        public int getSkyHeight() {
            return skyHeight;
        }

        public void setSkyHeight(int skyHeight) {
            this.skyHeight = skyHeight;
        }

        public int getValue() {
            return value[x][y];
        }

        @Override
        public int compareTo(Point o) {
            return value[this.getX()][this.getY()] - value[o.getX()][o.getY()];
        }

        @Override
        public String toString() {
            return String.format("%d,%d", x, y);
        }
    }

    public Boundary(int value[][]) {
        this.value = value;
        this.rowCount = value.length;
        this.colCount = value[0].length;
        for (int i=0; i<value.length; i++) {
            for (int j=0; j<value[i].length; j++) {
                onHandPoint.add(new Point(i, j));
            }
        }

        minHeight = onHandPoint.get(0).getValue();
        maxHeight = onHandPoint.get(onHandPoint.size() - 1).getValue();

    }

    public void getAllValley() {
        curHeight = maxHeight;
        //对于凸出的凹槽的处理
        if (curHeight > boundaryHeight) {

        } else { //对于下沉的凹槽的处理
            int c = getCapacity();
        }

    }
    public int getCapacity() {

        //找到边界最小值
        int min = value[0][0];
        //上边界
        for (int j=0; j<value[0].length; j++) {
            if (min > value[0][j]) min = value[0][j];
        }
        //左边界
        for (int i=0; i<value.length; i++) {
            if (min > value[i][0]) min = value[i][0];
        }
        //右
        for (int i=0; i<value.length; i++) {
            if (min > value[i][value[i].length-1]) min = value[i][value[i].length-1];
        }
        //下
        for (int j=0; j<value[value.length-1].length; j++) {
            if (min > value[value.length-1][j]) min = value[value.length-1][j];
        }
        boundaryHeight = min;

        for (Point p : onHandPoint) {
            if (p.x == 0 || p.x==value.length || p.y==0 || p.y==value[0].length)
                continue;
            if (p.getValue() < boundaryHeight)
                cap += boundaryHeight - p.getValue();

        }
        return cap;
    }


    private List<Point> getBoundary(Point point,
                                    List<String> prePeakPoints) {
        int x = point.getX();
        int y = point.getY();
        List<Point> boundary = new ArrayList<>();
        if (prePeakPoints.contains(point.toString())){
            return boundary;
        }

        if (x > 0 && x<rowCount-1 &&y > 0 && y<colCount) {
            boundary.add(new Point(x-1, y));
            boundary.add(new Point(x+1, y));
            boundary.add(new Point(x, y-1));
            boundary.add(new Point(x, y+1));
        }
        return boundary;
    }

    public static void main(String[] args) {
        int[][] testData = {{9,9,9,9},
                            {3,0,0,9},
                            {7,8,9,6}};
//        int[][] testData = {{9,8,7,8,test,8},
//                {5,2,0,test,test,9},
//                {9,2,6,test,8,9},
//                {5,2,6,6,8,9}};
        Boundary boundary = new Boundary(testData);
        System.out.println("容量："+boundary.getCapacity());
    }
}
