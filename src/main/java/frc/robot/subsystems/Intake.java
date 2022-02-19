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
  public DoubleSolenoid intakeSolenoidLeft;
  public DoubleSolenoid intakeSolenoidRight;

  //Solenoid Type
  private final PneumaticsModuleType type = PneumaticsModuleType.CTREPCM;

  // DRIVETRAIN CONSTRUCTOR
  public Intake() {
    // TALON SRX MOTOR CONTROLLER
    this.intakeMotorOne = new WPI_TalonSRX(Robot.ROBOTMAP.intakeMotorOnePort);

    // SPEED CONTROLLER GROUPS
    this.intakeMotorGroup = new MotorControllerGroup(this.intakeMotorOne);

    // SOLENOID
    this.intakeSolenoidLeft = new DoubleSolenoid(0, type, Robot.ROBOTMAP.intakeSoleLeftForward, Robot.ROBOTMAP.intakeSoleLeftReverse);
    this.intakeSolenoidRight = new DoubleSolenoid(0, type, Robot.ROBOTMAP.intakeSoleRightForward, Robot.ROBOTMAP.intakeSoleRightReverse);
    
    configureMotors();
  }

  public void spinRoller(boolean isForward) {
    System.out.println("spinning motors");
    if (isForward) {
      this.intakeMotorGroup.set(-Robot.ROBOTMAP.intakeSpeed);
    } else {
      this.intakeMotorGroup.set(Robot.ROBOTMAP.intakeSpeed);
    }
  }

  public void spinRoller(double speed) {
    System.out.println("intake set to spin at:" + speed);
    this.intakeMotorGroup.set(-speed);
  }

  public void spinRollerReverse(double speed) {
    System.out.println("intake spinning out at:" + speed);
    this.intakeMotorGroup.set(speed);
  }

  public boolean stopRoll() {
    this.intakeMotorGroup.stopMotor();
    return true;
  }

  public void toggle() {
    if (this.intakeSolenoidLeft.get().equals(DoubleSolenoid.Value.kForward)) {
      intakeRetract();
      System.out.println("toggle: retract intake pistons");
    } else {
      intakeExtend();
      System.out.println("toggle: extend intake pistons");
    }
    
  }

  public void intakeExtend() {
    System.out.println("extend intake");
    this.intakeSolenoidLeft.set(DoubleSolenoid.Value.kForward);
    this.intakeSolenoidRight.set(DoubleSolenoid.Value.kForward);
  }

  public void intakeRetract() {
    System.out.println("retract");
    this.intakeSolenoidLeft.set(DoubleSolenoid.Value.kReverse);
    this.intakeSolenoidRight.set(DoubleSolenoid.Value.kReverse);
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