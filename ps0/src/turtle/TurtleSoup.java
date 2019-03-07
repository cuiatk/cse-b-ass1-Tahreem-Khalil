/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;

import java.util.List;
import java.awt.Color;
import java.util.ArrayList;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
    	
    	
    	PenColor[] arr= {PenColor.CYAN,PenColor.GREEN,PenColor.ORANGE,PenColor.MAGENTA,PenColor.YELLOW,PenColor.BLUE,
    			PenColor.BLACK,PenColor.GRAY,PenColor.PINK,PenColor.RED};
    	turtle.color(arr[0]);
    	int i=0;
    	while (i !=4) {    		
    		
    		turtle.forward(sideLength);
    		
    		turtle.turn(90.0);
    		turtle.color(arr[i]);
			i++;
		}
 	
        //throw new RuntimeException("implement me!");
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
    	double exteriorAngle;
    	double interiorAngle=0.0;
    	interiorAngle=(sides-2)*180;
    	interiorAngle=interiorAngle/sides;
    	exteriorAngle=180-interiorAngle;
    	
    		return interiorAngle ;
    	
    	
    	
        //throw new RuntimeException("implement me!");
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
    	
    	double polygonSidesFronAngle=0;
    	if(angle>0 && angle<180)
    	{
    		polygonSidesFronAngle= (360/(180-angle));
    		polygonSidesFronAngle= Math.round(polygonSidesFronAngle);
    	}
    	else
    	{
    		System.out.println("enter angle correctly");
    	}
    	
    	return (int) polygonSidesFronAngle;
    	
       // throw new RuntimeException("implement me!");
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
    	int i=0;
    	double exteriorAngle;
    	exteriorAngle=180-calculateRegularPolygonAngle(sides);
    	PenColor[] arr= {PenColor.CYAN,PenColor.GREEN,PenColor.ORANGE,PenColor.MAGENTA,PenColor.YELLOW,PenColor.BLUE,
    			PenColor.BLACK,PenColor.GRAY,PenColor.PINK,PenColor.RED,};
    	turtle.color(arr[0]);
    	for(i=0;i!=sides;i++)
    	{	
    		if(i<10)
    		{
    			turtle.forward(sideLength); 
    		turtle.turn(exteriorAngle);
    		
    		turtle.color(arr[i]);
    		}
    		
    
    	}
    	
    	
        //throw new RuntimeException("implement me!");
    }

    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentHeading. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentHeading current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to heading (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
                                                 int targetX, int targetY) {
    	  int diffX = targetX - currentX;
          int diffY = targetY - currentY;
          double clockwiseFromNorth = Math.toDegrees(Math.atan2(diffX, diffY));
          double angle = clockwiseFromNorth - currentHeading;

          if(angle < 0)
          
        	   angle += 360; 
          
          return angle;
          
       // throw new RuntimeException("implement me!");
    }

    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateHeadingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
    	
    	List<Double> headingChanges = new ArrayList<Double>();
        int numberOfCoordinates = xCoords.size();
        double currentHeading = 0;
        for(int i = 1; i < numberOfCoordinates; i++){
            double adjustment = calculateHeadingToPoint(currentHeading, xCoords.get(i-1), yCoords.get(i-1),
            		xCoords.get(i), xCoords.get(i));
            currentHeading += adjustment;
            headingChanges.add(adjustment);
        }
        return headingChanges;
        //throw new RuntimeException("implement me!");
    }

    /**
     * Draw your personal, custom art.
     * 
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
    	   
    	for(int i = 0; i < 10; i++){ // i < 15 to limit size of shapes
            drawRegularPolygon(turtle, i, i);
            drawRegularPolygon(turtle, i*5, i+5);
            drawSquare(turtle, i*15);
            drawRegularPolygon(turtle, i, i*5);   
            drawSquare(turtle, i*10);
    	} 
        //throw new RuntimeException("implement me!");
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
    	
        DrawableTurtle turtle = new DrawableTurtle();
        List<Integer> xpoints = new ArrayList<>();
        List<Integer> ypoints = new ArrayList<>();
        
        xpoints.add(0);
        xpoints.add(1);
        xpoints.add(1);
        ypoints.add(0);
        ypoints.add(1);
        ypoints.add(2);
   
        //drawSquare(turtle, 100);
        //drawRegularPolygon(turtle, 6, 80);
        
       //System.out.println( calculateRegularPolygonAngle(6));
       //System.out.println(calculatePolygonSidesFromAngle(120.0));
       // System.out.println(calculateHeadingToPoint(0.0, 0, 0, 1, 0));
        System.out.println(calculateHeadings(xpoints, ypoints));
        // draw the window
      turtle.draw();
        drawPersonalArt(turtle);
       
    }

}
