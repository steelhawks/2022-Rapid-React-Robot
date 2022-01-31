
package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.Robot;
import frc.util.subsystems.MechanicalSubsystem;




public class Drivetrain extends MechanicalSubsystem
{
  // LEFT MOTORS
  public final WPI_TalonSRX LEFT_M_UP;
  public final WPI_TalonSRX LEFT_M_DOWN;
  
  // RIGHT MOTOR
  public final WPI_TalonSRX RIGHT_M_UP;
  public final WPI_TalonSRX RIGHT_M_DOWN;

  //SPEED CONTROLLER GROUPS
  public final MotorControllerGroup UP_M_GROUP;
  public final MotorControllerGroup DOWN_M_GROUP;

  //DIFFERENTIAL DRIVE
  public final DifferentialDrive DIFF_DRIVE;

  //VARIABLE RPM ELECTRO-SHIFT
  public int shiftStatus;
  public double rPMCoefficient;

  //NAVX MXP GYRO
  private final AHRS GYRO;
  private final double KP_GYRO;

  //DRIVETRAIN CONSTRUCTOR
  public Drivetrain() 
  {
    //SPARK MAX LEFT MOTORS
    this.LEFT_M_UP = new WPI_TalonSRX(Robot.ROBOTMAP.getLeftMotorPortUp());
    this.LEFT_M_DOWN = new WPI_TalonSRX(Robot.ROBOTMAP.getLeftMotorPortDown());
    
    //SPARK MAX RIGHT MOTORS
    this.RIGHT_M_UP = new WPI_TalonSRX(Robot.ROBOTMAP.getRightMotorPortUp());
    this.RIGHT_M_DOWN = new WPI_TalonSRX(Robot.ROBOTMAP.getRightMotorPortDown());

    //SPEED CONTROLLER GROUPS
    this.UP_M_GROUP = new MotorControllerGroup(this.LEFT_M_UP, this.RIGHT_M_UP);
    this.DOWN_M_GROUP = new MotorControllerGroup(this.LEFT_M_DOWN, this.RIGHT_M_DOWN);
    this.DOWN_M_GROUP.setInverted(true);

    //DIFFERENTIAL DRIVE
    this.DIFF_DRIVE = new DifferentialDrive(this.UP_M_GROUP, this.DOWN_M_GROUP);

    //NAVX MXP GYRO
    this.GYRO = new AHRS(SPI.Port.kMXP);
    this.KP_GYRO = Robot.ROBOTMAP.getKPGyro();

    //VARIABLE RPM ELECTRO-SHIFT
    this.shiftStatus = 1;
    this.rPMCoefficient = 1.75;

    configureMotors();

    this.DOWN_M_GROUP.setInverted(true);
    this.LEFT_M_DOWN.setInverted(true);
  }

  //DRIVING METHOD
  public void arcadeDrive(Joystick stick) 
  {
    this.DIFF_DRIVE.arcadeDrive(stick.getY() / this.rPMCoefficient, -stick.getTwist(), false);
  }

  //SHIFTING METHOD
  public void shiftGear() 
  {
    if (this.shiftStatus == 1) 
    {
      this.shiftStatus = 2;
      this.rPMCoefficient = 1.5;
    } 
    else if (this.shiftStatus == 2)
    {
      this.shiftStatus = 3;
      this.rPMCoefficient = 1;
    }
    else if (this.shiftStatus == 3)
    {
      this.shiftStatus = 1;
      this.rPMCoefficient = 1.75;
    }
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
    this.UP_M_GROUP.set(speed);
    this.DOWN_M_GROUP.set(speed);
  }

  //CONVERT AN INT SPEED INTO A DECIMAL SPEED
  public double decimalSpeed(double speed)
  {
    return ((int)(((speed + 350) / 700.0) * 100) / 100.0);
  }

  public void configureMotors() {
    this.LEFT_M_UP.configFactoryDefault();
    this.LEFT_M_DOWN.configFactoryDefault();
    this.RIGHT_M_UP.configFactoryDefault();
    this.RIGHT_M_DOWN.configFactoryDefault();
    this.LEFT_M_UP.setNeutralMode(NeutralMode.Coast);
    this.RIGHT_M_UP.setNeutralMode(NeutralMode.Coast);
    this.LEFT_M_DOWN.setNeutralMode(NeutralMode.Coast);
    this.RIGHT_M_DOWN.setNeutralMode(NeutralMode.Coast);
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

  /** Print info to Smart Dashboard */
  public void smartDashboard(){}

}