package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.util.pathcorder.JoystickRecorder;
import frc.util.subsystems.MechanicalSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.util.Limelight;

public class Drivetrain extends MechanicalSubsystem {
  // LEFT MOTORS
  public final WPI_TalonFX LEFT_MOTOR_ONE;
  public final WPI_TalonFX LEFT_MOTOR_TWO;
  public final WPI_TalonFX LEFT_MOTOR_THREE;

  // RIGHT MOTOR
  public final WPI_TalonFX RIGHT_MOTOR_ONE;
  public final WPI_TalonFX RIGHT_MOTOR_TWO;
  public final WPI_TalonFX RIGHT_MOTOR_THREE;

  // SPEED CONTROLLER GROUPS
  public final MotorControllerGroup LEFT_M_GROUP;
  public final MotorControllerGroup RIGHT_M_GROUP;

  // DIFFERENTIAL DRIVE
  public final DifferentialDrive DIFF_DRIVE;
  public boolean isForward;

  // DRIVETRAIN SHIFTGEAR SOLENOIDS
  public final PneumaticsModuleType type = PneumaticsModuleType.CTREPCM;
  public DoubleSolenoid DRIVE_SOLENOID;

  // VARIABLE RPM ELECTRO-SHIFT
  public int shiftStatus;
  public double rPMCoefficient;

  // TWIST COEFFICIENT
  public double twistCoefficient;

  // NAVX MXP GYRO
  public final AHRS GYRO;
  public final double KP_GYRO;

  // PATHCORDER
  public int count = 0;

  // Vision
  private final int ANGLE_LENIENCY = 3;
  private final double Y_LOWER_LIMIT = -16;
  private final double Y_MAX_LIMIT_HUB = 17;
  private final double X_THRESHOLD = 5;

  private final int LEAVE_HUB_TIME = 2;
  private final int ENTER_HUB_TIME = 2;
  public boolean angleInRange = false;
  public boolean ballUnaligned;

  public Drivetrain() {
    // SPARK MAX LEFT MOTORS
    this.LEFT_MOTOR_ONE = new WPI_TalonFX(Robot.ROBOTMAP.drivetrainLeftMotorPortOne);
    this.LEFT_MOTOR_TWO = new WPI_TalonFX(Robot.ROBOTMAP.drivetrainLeftMotorPortTwo);
    this.LEFT_MOTOR_THREE = new WPI_TalonFX(Robot.ROBOTMAP.drivetrainLeftMotorPortThree);

    // SPARK MAX RIGHT MOTORS
    this.RIGHT_MOTOR_ONE = new WPI_TalonFX(Robot.ROBOTMAP.drivetrainRightMotorPortOne);
    this.RIGHT_MOTOR_TWO = new WPI_TalonFX(Robot.ROBOTMAP.drivetrainRightMotorPortTwo);
    this.RIGHT_MOTOR_THREE = new WPI_TalonFX(Robot.ROBOTMAP.drivetrainRightMotorPortThree);

    // SPEED CONTROLLER GROUPS
    this.RIGHT_M_GROUP = new MotorControllerGroup(this.RIGHT_MOTOR_ONE, this.RIGHT_MOTOR_TWO, this.RIGHT_MOTOR_THREE);
    this.LEFT_M_GROUP = new MotorControllerGroup(this.LEFT_MOTOR_ONE, this.LEFT_MOTOR_TWO, this.LEFT_MOTOR_THREE);

    // DIFFERENTIAL DRIVE
    this.DIFF_DRIVE = new DifferentialDrive(this.RIGHT_M_GROUP, this.LEFT_M_GROUP);

    // NAVX MXP GYRO
    this.GYRO = new AHRS(SPI.Port.kMXP);
    this.KP_GYRO = Robot.ROBOTMAP.KP_GYRO;

    // VARIABLE RPM ELECTRO-SHIFT
    this.rPMCoefficient = 1;// original 1.75

    // TWIST COEFFICIENT
    this.twistCoefficient = 1.25;

    this.isForward = true;

    // DRIVETRAIN SOLENOID
    this.DRIVE_SOLENOID = new DoubleSolenoid(type, Robot.ROBOTMAP.drivetrainSolenoidPortOn,
        Robot.ROBOTMAP.drivetrainSolenoidPortOff);

    this.LEFT_M_GROUP.setInverted(false);
    this.RIGHT_M_GROUP.setInverted(true);

    configureMotors();
  }

  // DRIVING METHOD
  public void arcadeDrive(Joystick stick) {
    double y = stick.getY();
    double rotate = stick.getTwist();

    if (this.isForward){
      //nothing
    }
    else {
      y = -y;
    }
    // if(y < 0.1 && y > -0.1) {
    //   this.DIFF_DRIVE.arcadeDrive(0, rotate, false);
    // } else {
      this.DIFF_DRIVE.arcadeDrive(y / this.rPMCoefficient, rotate / this.twistCoefficient, false);  
      // this.DIFF_DRIVE.=urvatureDrive(y / this.rPMCoefficient, rotate / this.twistCoefficient, false);  

      // }
    
    if (Robot.RECORDER.isRecording) {
      count++;
      Robot.RECORDER.recordJoystick(new JoystickRecorder(y, rotate, false, count));
    }
    
  }

  public void reverseDirection() {
    if (!this.isForward){
      this.isForward = true;
    } else {
      this.isForward = false;
    }
    System.out.println("motion reversed");
  }

  // SHIFTING METHOD
  public void shiftGear() {
    if (this.DRIVE_SOLENOID.get() == DoubleSolenoid.Value.kForward) {
      highGear();
      System.out.println("SHIFT: in high gear");
    } else {
      lowGear();
      System.out.println("SHIFT: in low gear");
    }
  }

  public void lowGear() {
    this.DRIVE_SOLENOID.set(DoubleSolenoid.Value.kForward);
  }

  public void highGear() {
    this.DRIVE_SOLENOID.set(DoubleSolenoid.Value.kReverse);
  }

  // MOVING STRAIGHT USING THE GYRO METHOD
  public void gyroMoveStraight(double speed) {
    this.DIFF_DRIVE.arcadeDrive(speed, -this.GYRO.getAngle() * this.KP_GYRO);
  }

  // MOVING STRAIGHT USING GYRO AND ANGLE VALUE METHOD
  public void gyroMoveStraight(double speed, double angle) {
    this.DIFF_DRIVE.arcadeDrive(-speed, -angle * this.KP_GYRO);
  }

  // ROTATE ROBOT
  public void rotate(double speed) {
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
    this.LEFT_MOTOR_ONE.setNeutralMode(NeutralMode.Brake);
    this.LEFT_MOTOR_TWO.setNeutralMode(NeutralMode.Brake);
    this.LEFT_MOTOR_THREE.setNeutralMode(NeutralMode.Brake);
    this.RIGHT_MOTOR_ONE.setNeutralMode(NeutralMode.Brake);
    this.RIGHT_MOTOR_TWO.setNeutralMode(NeutralMode.Brake);
    this.RIGHT_MOTOR_THREE.setNeutralMode(NeutralMode.Brake);
  }

  public AHRS getGyro() {
    return this.GYRO;
  }

  public double getGyroAngle() {
    return this.GYRO.getYaw();
  }

  public double getGyroAxis() {
    return this.GYRO.getBoardYawAxis().board_axis.getValue();
  }

  public void resetGyro() {
    this.GYRO.reset();
    this.GYRO.zeroYaw();
  }

  public boolean stop() {
    rotate(0);
    return false;
  }

  /** Pings the subsystem. */
  public void ping() {
  }

  /**
   * Checks if the subsystem is functioning properly. @return True if functioning
   */
  public boolean isAlive() {
    return true;
  }

  public void resetEncoders() {
    // this.LEFT_MOTOR_ONE.getSensorCollection().setIntegratedSensorPosition(0, 0);
    // this.LEFT_MOTOR_TWO.getSensorCollection().setIntegratedSensorPosition(0, 0);
    // this.LEFT_MOTOR_THREE.getSensorCollection().setIntegratedSensorPosition(0, 0);
    // this.RIGHT_MOTOR_ONE.getSensorCollection().setIntegratedSensorPosition(0, 0);
    // this.RIGHT_MOTOR_TWO.getSensorCollection().setIntegratedSensorPosition(0, 0);
    // this.RIGHT_MOTOR_THREE.getSensorCollection().setIntegratedSensorPosition(0, 0);
  }

  /** Print info to shuffleboard */
  public void shuffleBoard() {
    // SmartDashboard.putNumber("L1 velocity",
    // this.LEFT_MOTOR_ONE.getSensorCollection().getIntegratedSensorVelocity());
    // SmartDashboard.putNumber("L3 velocity",
    // this.LEFT_MOTOR_THREE.getSensorCollection().getIntegratedSensorVelocity());
    // SmartDashboard.putNumber("R1 velocity",
    // this.RIGHT_MOTOR_ONE.getSensorCollection().getIntegratedSensorVelocity());
    // SmartDashboard.putNumber("R2 velocity",
    // this.RIGHT_MOTOR_TWO.getSensorCollection().getIntegratedSensorVelocity());

    // SmartDashboard.putNumber("TalonFXControlMode.Velocity",
    // TalonFXControlMode.Velocity.value);
    // SmartDashboard.putNumber("TalonFXControlMode.PercentOutput",
    // TalonFXControlMode.PercentOutput.value);
    // SmartDashboard.putNumber("TalonFXControlMode.Position",
    // TalonFXControlMode.Position.value);

    SmartDashboard.putNumber("gyroangle", this.GYRO.getAngle());
    SmartDashboard.putNumber("gyro axis", getGyroAxis());
    // SmartDashboard.putNumber("Left One Position", this.LEFT_MOTOR_ONE.getSensorCollection().getIntegratedSensorPosition());
    // SmartDashboard.putNumber("Left One Velocity", this.LEFT_MOTOR_ONE.getSensorCollection().getIntegratedSensorVelocity());
    // SmartDashboard.putNumber("Left Two Position", this.LEFT_MOTOR_TWO.getSensorCollection().getIntegratedSensorPosition());
    // SmartDashboard.putNumber("Left Two Velocity", this.LEFT_MOTOR_TWO.getSensorCollection().getIntegratedSensorVelocity());
    // SmartDashboard.putNumber("Left Three Position", this.LEFT_MOTOR_THREE.getSensorCollection().getIntegratedSensorPosition());
    // SmartDashboard.putNumber("Left Three Velocity", this.LEFT_MOTOR_THREE.getSensorCollection().getIntegratedSensorVelocity());
    // SmartDashboard.putNumber("Right One Position", this.RIGHT_MOTOR_ONE.getSensorCollection().getIntegratedSensorPosition());
    // SmartDashboard.putNumber("Right One Velocity", this.RIGHT_MOTOR_ONE.getSensorCollection().getIntegratedSensorVelocity());
    // SmartDashboard.putNumber("Right Two Position", this.RIGHT_MOTOR_TWO.getSensorCollection().getIntegratedSensorPosition());
    // SmartDashboard.putNumber("Right Two Velocity", this.RIGHT_MOTOR_TWO.getSensorCollection().getIntegratedSensorVelocity());
    // SmartDashboard.putNumber("Right Three Position", this.RIGHT_MOTOR_THREE.getSensorCollection().getIntegratedSensorPosition());
    // SmartDashboard.putNumber("Right Three Velocity", this.RIGHT_MOTOR_THREE.getSensorCollection().getIntegratedSensorVelocity());



   

  }

  // VISION STUFF

  // LOOK FOR BALL
  public void spinRobot() {
    this.LEFT_M_GROUP.set(-0.7);
    this.RIGHT_M_GROUP.set(0.7);
  }

  public boolean goToBall() {

    // Limelight.setPipeline(Robot.VISION.isRedAlliance ? 0 : 1);
    // Limelight.setPipeline(Robot.VISION.getBallPipeline());
    if (!Robot.VISION.isCargoPipeline()) {
      return true;
    }

    // Robot.VISION.switchToBallPipeline();

    while (! Limelight.hasValidTarget()) { //update in the loop ensures a break condition
      spinRobot();
      Limelight.updateValues();

    }
    // stop();
    Robot.DRIVETRAIN.stop();

    while (Limelight.hasValidTarget() && Robot.VISION.isCargoPipeline()) {
      Limelight.updateValues();
      if (Limelight.getYOffset() > Y_LOWER_LIMIT) {
        gyroMoveStraight(0.5, 8 * -Limelight.getXOffset()); //make offset negative or positive depending on the drivetrain.
      } else if (Math.abs(Limelight.getXOffset()) > X_THRESHOLD) {
        gyroMoveStraight(0, 4 * -Limelight.getXOffset());
        //or try:
        // this.DIFF_DRIVE.arcadeDrive(0, Limelight.getXOffset() / 10);
      } else {
        Robot.VISION.autonPickUpBall();
        stop();
        break; // intake here
      }
    }

    return false;
  }
  
  public void adjustBall() {

    if (!Limelight.hasValidTarget()) {
      return;
    }


    Limelight.updateValues();
    ballUnaligned = Math.abs(Limelight.getXOffset()) > X_THRESHOLD;

    if (ballUnaligned) {
      gyroMoveStraight(0, 4 * -Limelight.getXOffset());
    }
    
  }

  public boolean rotateToHub() {

    Robot.VISION.switchToHubPipeline();
    Limelight.updateValues();

    while (!Limelight.hasValidTarget()) {
      Limelight.updateValues();
      spinRobot();
      // Robot.VISION.updateNetworkValues();
    }
    // stop();

    double hubAngle = -Limelight.getXOffset();
    angleInRange = hubAngle > -ANGLE_LENIENCY && hubAngle < ANGLE_LENIENCY;
    int direction = 1;

    while (Limelight.hasValidTarget() && Robot.VISION.isHubPipeline() && !angleInRange) {
      Limelight.updateValues();
      hubAngle = Limelight.getXOffset(); // CHANGE TO NEGATIVE IF DRIVETRAIN ERRORS AND ROTATES AWAY.
      direction = hubAngle >= 0  ? 1 : -1;
      this.LEFT_M_GROUP.set(-0.4 * direction);
      this.RIGHT_M_GROUP.set(0.4 * direction); //prevents any linear movement, only rotational adjustment. 

      angleInRange = hubAngle > -ANGLE_LENIENCY && hubAngle < ANGLE_LENIENCY;

    }
    stop();

    return angleInRange; //ends when sufficiently corrected to be in range/
  }

  public void straightHubTest() {
    boolean hubNotReached = true;

    while (hubNotReached) {

      if (Limelight.hasValidTarget() && Robot.VISION.isHubPipeline()) {
        if (Limelight.getYOffset() < Y_MAX_LIMIT_HUB) {
          Limelight.updateValues();
          rotate(-0.4);
        } else if (Limelight.getYOffset() >= Y_MAX_LIMIT_HUB) {
          stop();
          hubNotReached = false;
          break;
        }
      }
      else {
        stop();
        break;
      }

    } // end of while
  }

  public void straightHub() {

    double hubArea = Limelight.getArea();

    if (Limelight.hasValidTarget() && Robot.VISION.isHubPipeline()) {
      this.LEFT_M_GROUP.set(0.2 / hubArea);
      this.RIGHT_M_GROUP.set(0.2 / hubArea);

      hubArea = Limelight.getArea();

      if (Limelight.getYOffset() > 15) {
        this.LEFT_M_GROUP.set(0);
        this.RIGHT_M_GROUP.set(0);

      }

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

  public void turn(double angle, double speed) {
    if (angle > ANGLE_LENIENCY) {
      this.LEFT_M_GROUP.set(speed);
      this.RIGHT_M_GROUP.set(-speed);
    } else {
      this.RIGHT_M_GROUP.set(speed);
      this.LEFT_M_GROUP.set(-speed);
    }

  }

  public void alignTape() {
    if (Robot.VISION.getLineAngle() > ANGLE_LENIENCY || Robot.VISION.getLineAngle() < -ANGLE_LENIENCY) {
      turn(Robot.VISION.getLineAngle(), 0.25);
    } else {
      Robot.DRIVETRAIN.rotate(0.1);
    }
  }

}