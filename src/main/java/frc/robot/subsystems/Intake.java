package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Robot;
import frc.util.subsystems.MechanicalSubsystem;

public class Intake extends MechanicalSubsystem {
    
  // TALON SRX MOTOR CONTROLLER
  public final WPI_TalonSRX intakeMotorOne;

  // SPEED CONTROLLER GROUP
  public final MotorControllerGroup intakeMotorGroup;

  // SOLENOID
  public DoubleSolenoid intakeSol;

  // DRIVETRAIN CONSTRUCTOR
  public Intake() {
    // TALON SRX MOTOR CONTROLLER
    this.intakeMotorOne = new WPI_TalonSRX(Robot.ROBOTMAP.intakeMotorOnePort);

    // SPEED CONTROLLER GROUPS
    this.intakeMotorGroup = new MotorControllerGroup(this.intakeMotorOne);

    // SOLENOID
    //this.intakeSol = new DoubleSolenoid(Robot.ROBOTMAP.intakeSolOnPort, Robot.ROBOTMAP.intakeSolOffPort);
    
    configureMotors();
  }

  public void spinRoller(boolean isForward) {
    System.out.println("spinning motors");
    if (isForward) {
      this.intakeMotorGroup.set(-Robot.ROBOTMAP.getIntakeSpeed());
    } else {
      this.intakeMotorGroup.set(Robot.ROBOTMAP.getIntakeSpeed());
    }
  }

  public void spinRoller(double speed) {
    System.out.println("2");
    this.intakeMotorGroup.set(-speed);
  }

  public void spinRollerReverse(double speed) {
    System.out.println(-2);
    this.intakeMotorGroup.set(speed);
  }

  public boolean stopRoll() {
    this.intakeMotorGroup.stopMotor();
    return true;
  }

  public void togglePosition() {
    if (this.intakeSol.get() == DoubleSolenoid.Value.kForward) {
      down();
    } else {
      up();
    }
    System.out.println("Shifted gears!");
  }

  public void up() {
    System.out.println("up");
    this.intakeSol.set(DoubleSolenoid.Value.kForward);
  }

  public void down() {
    System.out.println("down");
    this.intakeSol.set(DoubleSolenoid.Value.kReverse);
  }

  public void configureMotors() {
    this.intakeMotorOne.configFactoryDefault();

    this.intakeMotorOne.setNeutralMode(NeutralMode.Coast);
  }

  public void smartDashboard() {
    SmartDashboard.putNumber("Intake Motor One Speed", this.intakeMotorOne.get());
  }

  public boolean isAlive() {
    return intakeMotorOne.isAlive();
  }

  public void ping() {
  }

  public boolean stop() {
    this.intakeMotorOne.stopMotor();
    return true;
  }


  public void shuffleBoard() {
	
  }
}