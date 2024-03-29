package frc.util;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {

    private static NetworkTableInstance tableInstance;
    private static NetworkTable table;

    private static double ta; // target area
    private static double tx; // x offset or horizontal angle fromcenter
    private static double ty; // y offset
    private static double tv; // if target present 1; if not 0
    private static double horizontalBoundingSide; // horizontal side of rough bounding box
    private static double verticalBoundingSide; // vertical side of rough bounding box
    private static double[] contours;

    private static int pipeline;

    public static void init(){
        tableInstance = NetworkTableInstance.getDefault();
        table = tableInstance.getTable("limelight");
        pipeline = 0;
    }

    public static void updateValues(){

        ta = getEntryAsDouble("ta");
        tx = getEntryAsDouble("tx");
        ty = getEntryAsDouble("ty");
        tv = getEntryAsDouble("tv");
        horizontalBoundingSide = getEntryAsDouble("thor");
        verticalBoundingSide = getEntryAsDouble("tvert");
        
        pipeline = (int)getEntryAsDouble("getpipe");

        contours = table.getEntry("tcornxy").getDoubleArray(new double[0]);

    }

    public static double getArea(){
        return ta;
    }

    public static double getXOffset(){
        return tx;
    }

    public static double getYOffset(){
        return ty;
    }

    public static boolean hasValidTarget(){
        return tv == 1;
    }

    public static double getHorizontalSide(){
        return horizontalBoundingSide;
    }

    public static double getVerticalSide(){
        return verticalBoundingSide;
    }

    public static double[] getContours(){
        return contours;
    }

    public static int getPipeline(){
        return pipeline;
    }

    public static void setPipeline(int pipeline){
        table.getEntry("pipeline").setNumber(pipeline);
    }

    public static double getEntryAsDouble(String key){
        return table.getEntry(key).getDouble(0);
    }

}
