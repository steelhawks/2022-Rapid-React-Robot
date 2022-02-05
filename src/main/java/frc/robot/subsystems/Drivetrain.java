package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.SPI;
// import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
// import frc.util.pathcorder.JoystickRecorder;
// import frc.util.subsystems.MechanicalSubsystem;

public class Drivetrain extends SubsystemBase {

  private final PneumaticsModuleType type = PneumaticsModuleType.CTREPCM;

  // CANSPARK MAX LEFT MOTORS
  public final WPI_TalonSRX driveLeftMotorOne;
  public final WPI_TalonSRX driveLeftMotorTwo;
  public final WPI_TalonSRX driveLeftMotorThree;

  // CANSPARK MAX RIGHT MOTORS
  public final WPI_TalonSRX driveRightMotorOne;
  public final WPI_TalonSRX driveRightMotorTwo;
  public final WPI_TalonSRX driveRightMotorThree;

  // SPEED CONTROLLER GROUPS
  public final MotorControllerGroup driveLeftMotorGroup;
  public final MotorControllerGroup driveRightMotorGroup;

  // Differntial Drive
  public final DifferentialDrive diffDrive;

  // NAX MXP GYRO
  public final AHRS gyro;

  // SHIFTING SOLENOIDS
  public DoubleSolenoid shiftSol; 

  // DIRECTION
  public boolean isForward;

  // PATHCORDER
  public int count;

  // DRIVETRAIN CONTRUCTOR
  public Drivetrain() {
    // DRIVETRAIN LEFT MOTORS
    this.driveLeftMotorOne = new WPI_TalonSRX(Robot.ROBOT_MAP.leftMotorOne);
    this.driveLeftMotorTwo = new WPI_TalonSRX(Robot.ROBOT_MAP.leftMotorTwo);
    this.driveLeftMotorThree = new WPI_TalonSRX(Robot.ROBOT_MAP.leftMotorThree);

    // DRIVETRAIN RIGHT MOTORS
    this.driveRightMotorOne = new WPI_TalonSRX(Robot.ROBOT_MAP.rightMotorOne);
    this.driveRightMotorTwo = new WPI_TalonSRX(Robot.ROBOT_MAP.rightMotorTwo);
    this.driveRightMotorThree = new WPI_TalonSRX(Robot.ROBOT_MAP.rightMotorThree);

    // SPEEDCONTROLLER GROUPS
    this.driveLeftMotorGroup = new MotorControllerGroup(this.driveLeftMotorOne, this.driveLeftMotorTwo, this.driveLeftMotorThree);
    this.driveRightMotorGroup = new MotorControllerGroup(this.driveRightMotorOne, this.driveRightMotorTwo, this.driveRightMotorThree);

    // DIFFERENTIAL DRIVE
    this.diffDrive = new DifferentialDrive(this.driveLeftMotorGroup, this.driveRightMotorGroup);

    // Shift Gears Solenoid
    this.shiftSol = new DoubleSolenoid(this.type, Robot.ROBOT_MAP.driveSolenoidOn, Robot.ROBOT_MAP.driveSolenoidOff);

    // NAVX MXP
    this.gyro = new AHRS(SPI.Port.kMXP);

    // DIRECTION & FOLLOWER
    this.isForward = true;

    // PATHCORDER
    this.count = 0;

    configureMotors();
  }

  // DRIVING METHOD
  public void arcadeDrive(Joystick stick) {
    double y = stick.getY();
    double rotate = stick.getTwist();
    if (this.isForward) {
      // nothing
    } else {
      y = -y;
    }
    this.diffDrive.arcadeDrive(y, -rotate);

    /* Currently no recorder */

    // if (Robot.RECORDER.isRecording) {
    //   count++;
    //   Robot.RECORDER.recordJoystick(new JoystickRecorder(y, rotate, false, count));
    // }
  }

  public void arcadeDrive(double joyY, double rotation) {
    double y = joyY;
    double rotate = rotation;
    if (Robot.DRIVETRAIN.isForward) {
      Robot.DRIVETRAIN.diffDrive.arcadeDrive(y, -rotate);
    } else {
      Robot.DRIVETRAIN.diffDrive.arcadeDrive(-y, -rotate);
    }
  }

  public void rotate(double speed) {
    this.diffDrive.arcadeDrive(0, speed);
  }

  public void reverseDirection() {
    if (!this.isForward) {
      this.isForward = true;
    } else {
      this.isForward = false;
    }
    System.out.println("Motion reversed!");
  }

  // SHIFTING METHOD
  public void shiftGear() {
    if (this.shiftSol.get() == DoubleSolenoid.Value.kForward) {
      highGear();
    } else {
      lowGear();
    }
    System.out.println("Shifted gears!");
  }

  public void lowGear() {
    this.shiftSol.set(DoubleSolenoid.Value.kForward);
  }

  public void highGear() {
    this.shiftSol.set(DoubleSolenoid.Value.kReverse);
  }

  public void configureMotors() {
    this.driveLeftMotorOne.configFactoryDefault();
    this.driveLeftMotorTwo.configFactoryDefault();
    this.driveLeftMotorThree.configFactoryDefault();
    this.driveRightMotorOne.configFactoryDefault();
    this.driveRightMotorTwo.configFactoryDefault();
    this.driveRightMotorThree.configFactoryDefault();

    this.driveLeftMotorOne.setNeutralMode(NeutralMode.Brake);
    this.driveLeftMotorTwo.setNeutralMode(NeutralMode.Coast);
    this.driveLeftMotorThree.setNeutralMode(NeutralMode.Coast);
    this.driveRightMotorOne.setNeutralMode(NeutralMode.Brake);
    this.driveRightMotorTwo.setNeutralMode(NeutralMode.Coast);
    this.driveRightMotorThree.setNeutralMode(NeutralMode.Coast);
  }

  public void smartDashboard() {
  }

  public boolean stop() {
    this.driveLeftMotorGroup.set(0.0);
    this.driveRightMotorGroup.set(0.0);
    return true;
  }

  public void ping() {
  }

  public boolean isAlive() {
    return this.driveLeftMotorOne.isAlive() && this.driveLeftMotorTwo.isAlive()
        && this.driveLeftMotorThree.isAlive() && this.driveRightMotorOne.isAlive()
        && this.driveRightMotorTwo.isAlive() && this.driveRightMotorThree.isAlive();
  }
}