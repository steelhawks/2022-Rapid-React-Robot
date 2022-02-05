package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
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
  public DoubleSolenoid solenoidLeft;
  public DoubleSolenoid solenoidRight;

  //Solenoid Type
  private final PneumaticsModuleType type = PneumaticsModuleType.CTREPCM;

  // DRIVETRAIN CONSTRUCTOR
  public Intake() {
    // TALON SRX MOTOR CONTROLLER
    this.intakeMotorOne = new WPI_TalonSRX(Robot.ROBOTMAP.intakeMotorOnePort);

    // SPEED CONTROLLER GROUPS
    this.intakeMotorGroup = new MotorControllerGroup(this.intakeMotorOne);

    // SOLENOID
    this.solenoidLeft = new DoubleSolenoid(type, 0, 1);
    this.solenoidRight = new DoubleSolenoid(type, 2, 3);
    
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

  public void toggle() {
    if (this.solenoidLeft.get().equals(DoubleSolenoid.Value.kForward)) {
      retract();
    } else {
      extend();
    }
    System.out.println("Shifted gears!");
  }

  public void extend() {
    System.out.println("extend");
    this.solenoidLeft.set(DoubleSolenoid.Value.kForward);
    this.solenoidRight.set(DoubleSolenoid.Value.kForward);
  }

  public void retract() {
    System.out.println("retract");
    this.solenoidLeft.set(DoubleSolenoid.Value.kReverse);
    this.solenoidRight.set(DoubleSolenoid.Value.kReverse);
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