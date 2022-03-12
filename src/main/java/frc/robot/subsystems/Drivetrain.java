package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.kauailabs.navx.frc.AHRS; 
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.util.pathcorder.JoystickRecorder;
import frc.util.subsystems.MechanicalSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid;



public class Drivetrain extends MechanicalSubsystem{
    // LEFT MOTORS
  public final WPI_TalonFX LEFT_MOTOR_ONE;
  public final WPI_TalonFX LEFT_MOTOR_TWO;
  public final WPI_TalonFX LEFT_MOTOR_THREE;
  
  // RIGHT MOTOR
  public final WPI_TalonFX RIGHT_MOTOR_ONE;
  public final WPI_TalonFX RIGHT_MOTOR_TWO;
  public final WPI_TalonFX RIGHT_MOTOR_THREE;

  //SPEED CONTROLLER GROUPS
  public final MotorControllerGroup LEFT_M_GROUP;
  public final MotorControllerGroup RIGHT_M_GROUP;

  //DIFFERENTIAL DRIVE
  public final DifferentialDrive DIFF_DRIVE;
  public boolean isForward;

  //DRIVETRAIN SHIFTGEAR SOLENOIDS
  public final PneumaticsModuleType type = PneumaticsModuleType.CTREPCM;
  public DoubleSolenoid DRIVE_SOLENOID_LEFT;
  public DoubleSolenoid DRIVE_SOLENOID_RIGHT;

  //VARIABLE RPM ELECTRO-SHIFT
  public int shiftStatus;
  public double rPMCoefficient;

  //TWIST COEFFICIENT
  public double twistCoefficient;

  //NAVX MXP GYRO
  public final AHRS GYRO;
  public final double KP_GYRO;

  //PATHCORDER
  public int count = 0;

  public Drivetrain() 
  {
    //SPARK MAX LEFT MOTORS
    this.LEFT_MOTOR_ONE = new WPI_TalonFX(Robot.ROBOTMAP.drivetrainLeftMotorPortOne);
    this.LEFT_MOTOR_TWO = new WPI_TalonFX(Robot.ROBOTMAP.drivetrainLeftMotorPortTwo);
    this.LEFT_MOTOR_THREE = new WPI_TalonFX(Robot.ROBOTMAP.drivetrainLeftMotorPortThree);
    
    //SPARK MAX RIGHT MOTORS
    this.RIGHT_MOTOR_ONE = new WPI_TalonFX(Robot.ROBOTMAP.drivetrainRightMotorPortOne);
    this.RIGHT_MOTOR_TWO = new WPI_TalonFX(Robot.ROBOTMAP.drivetrainRightMotorPortTwo);
    this.RIGHT_MOTOR_THREE = new WPI_TalonFX(Robot.ROBOTMAP.drivetrainRightMotorPortThree);

    //SPEED CONTROLLER GROUPS
    this.RIGHT_M_GROUP = new MotorControllerGroup(this.RIGHT_MOTOR_ONE, this.RIGHT_MOTOR_TWO, this.RIGHT_MOTOR_THREE);
    this.LEFT_M_GROUP = new MotorControllerGroup(this.LEFT_MOTOR_ONE, this.LEFT_MOTOR_TWO, this.LEFT_MOTOR_THREE);

    //DIFFERENTIAL DRIVE
    this.DIFF_DRIVE = new DifferentialDrive(this.RIGHT_M_GROUP, this.LEFT_M_GROUP);

    //NAVX MXP GYRO
    this.GYRO = new AHRS(SPI.Port.kMXP);
    this.KP_GYRO = Robot.ROBOTMAP.KP_GYRO;

    //VARIABLE RPM ELECTRO-SHIFT
    this.rPMCoefficient = 1.25;//original 1.75

    //TWIST COEFFICIENT
    this.twistCoefficient = 1.25;

    //DRIVETRAIN SOLENOID
    this.DRIVE_SOLENOID_LEFT = new DoubleSolenoid(8, type, Robot.ROBOTMAP.drivetrainSolenoidLeftPortOn, Robot.ROBOTMAP.drivetrainSolenoidLeftPortOff);
    this.DRIVE_SOLENOID_RIGHT = new DoubleSolenoid(8, type, Robot.ROBOTMAP.drivetrainSolenoidRightPortOn, Robot.ROBOTMAP.drivetrainSolenoidRightPortOff);

    this.LEFT_M_GROUP.setInverted(false);
    this.RIGHT_M_GROUP.setInverted(true);

    configureMotors();
  }

  //DRIVING METHOD
  public void arcadeDrive(Joystick stick) 
  {
    double y = stick.getY();
    double rotate = stick.getTwist();
    this.DIFF_DRIVE.arcadeDrive(y / this.rPMCoefficient, rotate / this.twistCoefficient, false);
    if (Robot.RECORDER.isRecording) {
      count++;
      Robot.RECORDER.recordJoystick(new JoystickRecorder(y, rotate, false, count));
    }

  }

  //SHIFTING METHOD
  public void shiftGear() 
  {
    if (this.DRIVE_SOLENOID_LEFT.get() == DoubleSolenoid.Value.kForward) {
      highGear();
    } else {
      lowGear();
    }
    System.out.println("Drive Train Shifted gears!");
  }

  public void lowGear() {
    this.DRIVE_SOLENOID_LEFT.set(DoubleSolenoid.Value.kForward);
    this.DRIVE_SOLENOID_RIGHT.set(DoubleSolenoid.Value.kForward);
  }

  public void highGear() {
    this.DRIVE_SOLENOID_LEFT.set(DoubleSolenoid.Value.kReverse);
    this.DRIVE_SOLENOID_RIGHT.set(DoubleSolenoid.Value.kReverse);
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
    this.RIGHT_MOTOR_THREE.setNeutralMode(NeutralMode.Coast);
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

  /** Pings the subsystem. */
  public void ping(){}

  /** Checks if the subsystem is functioning properly. @return True if functioning */
  public boolean isAlive(){
    return true;
  }

  /** Print info to shuffleboard */
  public void shuffleBoard(){
    SmartDashboard.putNumber("L1 velocity", this.LEFT_MOTOR_ONE.getSensorCollection().getIntegratedSensorVelocity());
    SmartDashboard.putNumber("L3 velocity", this.LEFT_MOTOR_THREE.getSensorCollection().getIntegratedSensorVelocity());
    SmartDashboard.putNumber("R1 velocity", this.RIGHT_MOTOR_ONE.getSensorCollection().getIntegratedSensorVelocity());
    SmartDashboard.putNumber("R2 velocity", this.RIGHT_MOTOR_TWO.getSensorCollection().getIntegratedSensorVelocity());

    SmartDashboard.putNumber("TalonFXControlMode.Velocity", TalonFXControlMode.Velocity.value);
    SmartDashboard.putNumber("TalonFXControlMode.PercentOutput", TalonFXControlMode.PercentOutput.value);
    SmartDashboard.putNumber("TalonFXControlMode.Position", TalonFXControlMode.Position.value);



    SmartDashboard.putNumber("gyroangle", getGyroAngle());
    SmartDashboard.putNumber("gyro axis", getGyroAxis());
    
  }

}





