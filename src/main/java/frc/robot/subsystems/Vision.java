package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.util.Limelight;
import frc.util.subsystems.*;

public class Vision extends SensorSubsystem {

    private static final double FOCAL_LENGTH = 210;
    private static final double CARGO_DIAMETER = 24.13; // in cm

    private double dist; // ball distance from camera to front of ball
    private double hubDist; // HUB distance from camera to the 
    private double lineAngle; //angle of the line

    private final int RED_CARGO_PIPELINE = 0;
    private final int BLUE_CARGO_PIPELINE = 1;
    private final int TAPE_PIPELINE = 2;
    private final int HUB_PIPELINE = 5;
    private int BALL_PIPELINE = 0;

    public boolean isRedAlliance;
    public String detectMode = "BALL";

    public boolean limelightUp = false;

    public final Servo LIMELIGHT_MOTOR;
    
    // public final DigitalInput beamS = new DigitalInput(Robot.ROBOTMAP.beambreakerPort2);
    // public final DigitalInput beamI = new DigitalInput(Robot.ROBOTMAP.beambreakerPort);
    public int count = 0;
    public boolean previousIntact = true;



    public Vision(){
        Limelight.init();
        
        LIMELIGHT_MOTOR = new Servo(Robot.ROBOTMAP.LimelightMotorPort);
        setPipelineColor();
        // Limelight.setPipeline(isRedAlliance ? RED_CARGO_PIPELINE : BLUE_CARGO_PIPELINE);

        // updateNetworkValues();
    }
    
    public double getBallDistance() {
        return dist;
    }
    
    public double getHubDistance() {
        return hubDist;
    }
    
    public double getLineAngle(){
        return lineAngle;
    }

    public boolean isCargoPipeline(){
        return Limelight.getPipeline() <= 1;
    }

    public boolean isHubPipeline(){
        return Limelight.getPipeline() == 5;
    }
    


    public void updateAllianceColor() {
        if (DriverStation.getAlliance() == DriverStation.Alliance.Red) {
            isRedAlliance = true;
        }
        else {
            isRedAlliance = false;
        }
        // Limelight.setPipeline(isRedAlliance ? RED_CARGO_PIPELINE : BLUE_CARGO_PIPELINE);
        // setPipelineColor();
    }
    

    public void updateNetworkValues(){
        Limelight.updateValues();
        updateDistance();
        updateLineAngle();
        updateAllianceColor();
        setPipelineColor();
        //SmartDashboard.putBoolean("intact", beamI.get());
        shuffleBoard();
    }

    public void updateDistance() {
        dist = (int) (100 * (CARGO_DIAMETER * FOCAL_LENGTH) / Limelight.getHorizontalSide()) / 100.0;
    }

    public int getSlopeDirection(){
        double[] contours = Limelight.getContours();
        int xMinIndex = minYContour(contours) - 1;
        int xMaxIndex = maxYContour(contours) - 1;

        if(contours[xMinIndex] < contours[xMaxIndex]){
            return -1;
        }else{
            return 1;
        }

    }

    public void updateLineAngle(){
        if(Limelight.getContours().length > 0){
            double slope = Limelight.getVerticalSide() / Limelight.getHorizontalSide();
            slope *= getSlopeDirection();
            lineAngle = Math.toDegrees(Math.atan(slope));
        }else{
            lineAngle = 91;
        }
    }

    public int maxYContour(double[] list){
        int maxIndex = 1;
        for(int i = 1; i < list.length; i += 2){
            if(list[i] > list[maxIndex]){
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public int minYContour(double[] list){
        int minIndex = 1;
        for(int i = 1; i < list.length; i += 2){
            if(list[i] < list[minIndex]){
                minIndex = i;
            }
        }
        return minIndex;
    }

    public void faceLimelightDown(){
        if (limelightUp) {
            LIMELIGHT_MOTOR.setAngle(35);
            limelightUp = false;
        }
        
        // LIMELIGHT_MOTOR.set(0.1);
        // Limelight.setPipeline(Robot.VISION.isRedAlliance ? 0 : 1);
        // Limelight.setPipeline(BALL_PIPELINE); //just to test 
    }

    public void faceLimelightUp(){
        if (!limelightUp) {
            LIMELIGHT_MOTOR.setAngle(175);
            limelightUp = true;
        }
        
        // LIMELIGHT_MOTOR.set(1);
        // System.out.println("limelight up running");
        // Limelight.setPipeline(HUB_PIPELINE);
    }

    @Override
    public void enable() {

    }

    @Override
    public void disable() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void ping() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isAlive() {
        // TODO Auto-generated method stub
        return false;
    }

    public void shuffleBoard() {
        SmartDashboard.putBoolean("Valid Target", Limelight.hasValidTarget());
        SmartDashboard.putNumber("X Offset", Limelight.getXOffset());
        SmartDashboard.putNumber("Y Offset", Limelight.getYOffset());
        SmartDashboard.putNumber("Area", Limelight.getArea());
        SmartDashboard.putNumber("Distance (cm)", dist);
        SmartDashboard.putNumber("Distance (in)", dist / 2.54);
        SmartDashboard.putNumber("Line Angle", lineAngle);
        SmartDashboard.putBoolean("isRedAlliance", isRedAlliance);
        SmartDashboard.putNumber("BALL PIPELINE", BALL_PIPELINE);
    }

    public void setPipelineColor(){
        BALL_PIPELINE = isRedAlliance ? RED_CARGO_PIPELINE : BLUE_CARGO_PIPELINE;
        // Limelight.setPipeline(isRedAlliance ? RED_CARGO_PIPELINE : BLUE_CARGO_PIPELINE);
        // faceLimelightDown();
    }

    public int getBallPipeline() {
        return BALL_PIPELINE;
    }

    
    public void switchToLinePipeline(){
        Limelight.setPipeline(TAPE_PIPELINE);
        faceLimelightDown();
    }
    
    public void switchToHubPipeline() {
        Limelight.setPipeline(HUB_PIPELINE);
        faceLimelightUp();
    }

    public void switchToBallPipeline() {
        Limelight.setPipeline(BALL_PIPELINE);
        faceLimelightDown();

    }    
    public void toggleDetect(){
        detectMode = detectMode.equals("BALL") ? "HUB" : "BALL";
    }




    public void autonPickUpBall(){
    //     
    
    //    if(beamI.get()){
    //         Robot.INTAKE.spinRoller(false);; //runs intake at start of auton
    //         Robot.STORAGE.storageIn(true); //runs sushi rollers at start of auton
    //         if(!previousIntact){
    //             Robot.INTAKE.stopRoll();
    //             Robot.STORAGE.storageMotorStop();
    //             // Robot.STORAGE.storageInStop();
    //             // Robot.STORAGE.storageUpStop();
    //             Robot.INTAKE.stopRoll();
    //             count++;
    //         }else{
    //             previousIntact = true;
    //         }
    //     }
    //     if(!beamI.get()){
    //         Robot.INTAKE.stopRoll();
    //         Robot.STORAGE.storageIn(true);
    //         Robot.STORAGE.storageRun(false);
        
    //         previousIntact = false;
      
    //     }
    }
}
