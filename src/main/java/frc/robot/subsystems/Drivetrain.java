package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

import com.kauailabs.navx.frc.AHRS; 
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.util.subsystems.MechanicalSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.util.Limelight;



public class Drivetrain extends MechanicalSubsystem{
    // LEFT MOTORS
  public final WPI_TalonSRX LEFT_MOTOR_ONE;
  public final WPI_TalonSRX LEFT_MOTOR_TWO;
  public final WPI_TalonSRX LEFT_MOTOR_THREE;
  
  // RIGHT MOTOR
  public final WPI_TalonSRX RIGHT_MOTOR_ONE;
  public final WPI_TalonSRX RIGHT_MOTOR_TWO;
  public final WPI_TalonSRX RIGHT_MOTOR_THREE;

  //SPEED CONTROLLER GROUPS
  public final MotorControllerGroup LEFT_M_GROUP;
  public final MotorControllerGroup RIGHT_M_GROUP;

  //DIFFERENTIAL DRIVE
  public final DifferentialDrive DIFF_DRIVE;

  //DRIVETRAIN SHIFTGEAR SOLENOIDS
  public final PneumaticsModuleType type = PneumaticsModuleType.CTREPCM;
  public DoubleSolenoid DRIVESOLENOID;

  //VARIABLE RPM ELECTRO-SHIFT
  public int shiftStatus;
  public double rPMCoefficient;

  //NAVX MXP GYRO
  private final AHRS GYRO;
  private final double KP_GYRO;

  //Vision
  private final int ANGLE_LENIENCY = 5;
  private final double Y_LOWER_LIMIT = 2;
  private final double X_THRESHOLD = 8;
  
  private final int LEAVE_HUB_TIME = 2;
  private final int ENTER_HUB_TIME = 2;

  public Drivetrain() 
  {
    //SPARK MAX LEFT MOTORS
    this.LEFT_MOTOR_ONE = new WPI_TalonSRX(Robot.ROBOTMAP.drivetrainLeftMotorPortOne);
    this.LEFT_MOTOR_TWO = new WPI_TalonSRX(Robot.ROBOTMAP.drivetrainLeftMotorPortTwo);
    this.LEFT_MOTOR_THREE = new WPI_TalonSRX(Robot.ROBOTMAP.drivetrainLeftMotorPortThree);
    
    //SPARK MAX RIGHT MOTORS
    this.RIGHT_MOTOR_ONE = new WPI_TalonSRX(Robot.ROBOTMAP.drivetrainRightMotorPortOne);
    this.RIGHT_MOTOR_TWO = new WPI_TalonSRX(Robot.ROBOTMAP.drivetrainRightMotorPortTwo);
    this.RIGHT_MOTOR_THREE = new WPI_TalonSRX(Robot.ROBOTMAP.drivetrainRightMotorPortThree);

    //SPEED CONTROLLER GROUPS
    this.RIGHT_M_GROUP = new MotorControllerGroup(this.RIGHT_MOTOR_ONE, this.RIGHT_MOTOR_TWO, this.RIGHT_MOTOR_THREE);
    this.LEFT_M_GROUP = new MotorControllerGroup(this.LEFT_MOTOR_ONE, this.LEFT_MOTOR_TWO, this.LEFT_MOTOR_THREE);

    //DIFFERENTIAL DRIVE
    this.DIFF_DRIVE = new DifferentialDrive(this.RIGHT_M_GROUP, this.LEFT_M_GROUP);

    //NAVX MXP GYRO
    this.GYRO = new AHRS(SPI.Port.kMXP);
    this.KP_GYRO = Robot.ROBOTMAP.KP_GYRO;

    //VARIABLE RPM ELECTRO-SHIFT
    this.rPMCoefficient = 2;//original 1.75

    //DRIVETRAIN SOLENOID
    this.DRIVESOLENOID = new DoubleSolenoid(type, Robot.ROBOTMAP.drivetrainSolenoidPortOn, Robot.ROBOTMAP.drivetrainSolenoidPortOff);

    this.LEFT_M_GROUP.setInverted(true);
    this.RIGHT_M_GROUP.setInverted(false);

    configureMotors();
  }

  //DRIVING METHOD
  public void arcadeDrive(Joystick stick) 
  {
    this.DIFF_DRIVE.arcadeDrive(stick.getY() / this.rPMCoefficient, -stick.getTwist(), false);
  }

  //SHIFTING METHOD
  public void shiftGear() 
  {
    if (this.DRIVESOLENOID.get() == DoubleSolenoid.Value.kForward) {
      highGear();
    } else {
      lowGear();
    }
    System.out.println("Drive Train Shifted gears!");
  }

  public void lowGear() {
    this.DRIVESOLENOID.set(DoubleSolenoid.Value.kForward);
  }

  public void highGear() {
    this.DRIVESOLENOID.set(DoubleSolenoid.Value.kReverse);
  }

  //MOVING STRAIGHT USING THE GYRO METHOD
  public void gyroMoveStraight(double speed)
  {
    this.DIFF_DRIVE.arcadeDrive(speed, -this.GYRO.getAngle() * this.KP_GYRO);
  }

  //MOVING STRAIGHT USING GYRO AND ANGLE VALUE METHOD
  public void gyroMoveStraight(double speed, double angle)
  {
    this.DIFF_DRIVE.arcadeDrive(-speed, -angle * this.KP_GYRO);
  }

  //ROTATE ROBOT
  public void rotate(double speed)
  {
    this.LEFT_M_GROUP.set(speed);
    this.RIGHT_M_GROUP.set(speed);
  }

  public void configureMotors() {
    this.LEFT_MOTOR_ONE.configFactoryDefault();
    this.LEFT_MOTOR_TWO.configFactoryDefault();
    this.LEFT_MOTOR_THREE.configFactoryDefault(); 
    this.RIGHT_MOTOR_ONE.configFactoryDefault();
    this.RIGHT_MOTOR_TWO.configFactoryDefault();
    this.RIGHT_MOTOR_THREE.configFactoryDefault();
    this.LEFT_MOTOR_ONE.setNeutralMode(NeutralMode.Coast);
    this.LEFT_MOTOR_TWO.setNeutralMode(NeutralMode.Coast);
    this.LEFT_MOTOR_THREE.setNeutralMode(NeutralMode.Coast);
    this.RIGHT_MOTOR_ONE.setNeutralMode(NeutralMode.Coast);
    this.RIGHT_MOTOR_TWO.setNeutralMode(NeutralMode.Coast);
    this.RIGHT_MOTOR_TWO.setNeutralMode(NeutralMode.Coast);
  }

  public AHRS getGyro()
  {
    return this.GYRO;
  }

  public double getGyroAngle() 
  {
    return this.GYRO.getAngle(); 
  }

  public double getGyroAxis() 
  {
    return this.GYRO.getBoardYawAxis().board_axis.getValue();
  }

  public void resetGyro() 
  {
    this.GYRO.reset();
    this.GYRO.zeroYaw();
  }

  public boolean stop(){
    rotate(0);
    return false;
  }

  public void motorTimeout(){
    System.out.println(LEFT_MOTOR_ONE.getExpiration());
  }

  /** Pings the subsystem. */
  public void ping(){}

  /** Checks if the subsystem is functioning properly. @return True if functioning */
  public boolean isAlive(){
    return true;
  }

  /** Print info to shuffleboard */
  public void shuffleBoard(){
    Shuffleboard.getTab("Subsystem");
  }


  //VISION STUFF

  // LOOK FOR BALL
  public void spinRobot() {
    this.DIFF_DRIVE.arcadeDrive(0, 0.7);
  }

  
  public boolean goToBall(){

    // Limelight.setPipeline(Robot.VISION.isRedAlliance ? 0 : 1);
    // Limelight.setPipeline(Robot.VISION.getBallPipeline());
    if(! Robot.VISION.isCargoPipeline()){
      return true;
    }
    
    // Robot.VISION.switchToBallPipeline();

    if(! Limelight.hasValidTarget()) {
      spinRobot();
      // Limelight.updateValues();
      Robot.VISION.updateNetworkValues();
      
    }
    
    
    if(Limelight.hasValidTarget() && Robot.VISION.isCargoPipeline()){
      if(Limelight.getYOffset() > Y_LOWER_LIMIT){
        gyroMoveStraight(0.8, 8 * Limelight.getXOffset());
      }
      else if(Math.abs(Limelight.getXOffset()) > X_THRESHOLD){
        gyroMoveStraight(0, 4 * Limelight.getXOffset());
      }else{
        return true; // intake here
      }
    }

    return false;
  }

  // public void goToHub(){
  //   if(Limelight.hasValidTarget() && Robot.VISION.isHubPipeline())
  //   gyroMoveStraight(0.4, 2 * Limelight.getXOffset());
  // }

  public boolean rotateToHub() {

    Robot.VISION.switchToHubPipeline();

    if (!Limelight.hasValidTarget()) {
      spinRobot();
      Robot.VISION.updateNetworkValues();
    } 

    double hubAngle = -Limelight.getXOffset();
    boolean angleInRange = hubAngle > -ANGLE_LENIENCY && hubAngle < ANGLE_LENIENCY;;

    if (Limelight.hasValidTarget() && Robot.VISION.isHubPipeline() && !angleInRange){
      this.LEFT_M_GROUP.set(hubAngle / 60);
      this.RIGHT_M_GROUP.set(-hubAngle / 60);

      hubAngle = Limelight.getXOffset();
      angleInRange = hubAngle > -ANGLE_LENIENCY && hubAngle < ANGLE_LENIENCY;

    } 

    return angleInRange;
  }

  public void straightHub() {

    double hubArea = Limelight.getArea();

    if (Limelight.hasValidTarget() && Robot.VISION.isHubPipeline()){
      this.LEFT_M_GROUP.set(0.2 / hubArea);
      this.RIGHT_M_GROUP.set(0.2 / hubArea);

      hubArea = Limelight.getArea();
      
    } 

    // move straight a set amount then turn 180
    // enterHub();
    // rotateRobot();
    // leaveHub();
  }

  // Turns robot 180 degrees
  private void rotateRobot() {
    this.DIFF_DRIVE.arcadeDrive(0, 180);
  }

  private void enterHub() {
    long timeStarted = System.currentTimeMillis();

    while (System.currentTimeMillis() - timeStarted < ENTER_HUB_TIME) {
      this.LEFT_M_GROUP.set(0.3);
      this.RIGHT_M_GROUP.set(0.3);
    }
  }

  private void leaveHub() {
    long timeStarted = System.currentTimeMillis();

    while (System.currentTimeMillis() - timeStarted < LEAVE_HUB_TIME) {
      this.LEFT_M_GROUP.set(0.5);
      this.RIGHT_M_GROUP.set(0.5);
    }
  }
    
  public void turn(double angle, double speed){
    if (angle > ANGLE_LENIENCY) {
      this.LEFT_M_GROUP.set(speed);
      this.RIGHT_M_GROUP.set(-speed);
    } else {
      this.RIGHT_M_GROUP.set(speed);
      this.LEFT_M_GROUP.set(-speed);
    }

  }
  public void alignTape() {
    if(Robot.VISION.getLineAngle() > ANGLE_LENIENCY || Robot.VISION.getLineAngle() < -ANGLE_LENIENCY) {
      turn(Robot.VISION.getLineAngle(), 0.25);
    } else { 
      Robot.DRIVETRAIN.rotate(0.1);
    }
  }

}