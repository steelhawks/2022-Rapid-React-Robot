package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.robot.Robot;
import frc.util.subsystems.MechanicalSubsystem;

public class Climber extends MechanicalSubsystem {

  public final WPI_TalonSRX CLIMBER_MOTOR_LEFT;
  public final WPI_TalonSRX CLIMBER_MOTOR_RIGHT;

  public final DoubleSolenoid CLIMBER_SOLENOID_LEFT;
  public final DoubleSolenoid CLIMBER_SOLENOID_RIGHT;

  private final PneumaticsModuleType type = PneumaticsModuleType.CTREPCM;

  public Climber() {
    this.CLIMBER_MOTOR_LEFT = new WPI_TalonSRX(Robot.ROBOTMAP.climberLeftPort);
    this.CLIMBER_MOTOR_RIGHT = new WPI_TalonSRX(Robot.ROBOTMAP.climberRightPort);

    this.CLIMBER_SOLENOID_LEFT = new DoubleSolenoid(type, 4, 5);
    this.CLIMBER_SOLENOID_RIGHT = new DoubleSolenoid(type, 6, 7);

    configureMotors();
  }

  public void climberRoll(boolean isForward) {
    System.out.println("climber");
    if (isForward) {
      this.CLIMBER_MOTOR_LEFT.set(Robot.ROBOTMAP.climberSpeed);
      this.CLIMBER_MOTOR_RIGHT.set(Robot.ROBOTMAP.climberSpeed);
    } else {
      this.CLIMBER_MOTOR_LEFT.set(-Robot.ROBOTMAP.climberSpeed);
      this.CLIMBER_MOTOR_RIGHT.set(-Robot.ROBOTMAP.climberSpeed);
    }
  }

  // public void climberRollWinch() {
  //   System.out.println("forwards");
  //   this.CLIMBER_MOTOR_LEFT.set(Robot.ROBOTMAP.climberSpeed);
  // }

  // public void climberUnrollWinch() {
  //   System.out.println("backwards");
  //   this.CLIMBER_MOTOR_LEFT.set(-Robot.ROBOTMAP.climberSpeed);
  // }

  public void climberToggleSolenoid() {
    if(this.CLIMBER_SOLENOID_LEFT.get().equals(DoubleSolenoid.Value.kForward)) {
      climberRetractSolenoid();
      System.out.println("Climber Retract");
    } else {
      climberExtendSolenoid();
      System.out.println("Climber Extend");
    }
  }

  public void climberExtendSolenoid() {
    this.CLIMBER_SOLENOID_LEFT.set(DoubleSolenoid.Value.kForward);
    this.CLIMBER_SOLENOID_RIGHT.set(DoubleSolenoid.Value.kForward);
  }

  public void climberRetractSolenoid() {
    this.CLIMBER_SOLENOID_LEFT.set(DoubleSolenoid.Value.kReverse);
    this.CLIMBER_SOLENOID_RIGHT.set(DoubleSolenoid.Value.kReverse);
  }

  // public void climberPivotForward() {
  //   System.out.println("pivot");
  //   this.PIVOT_MOTOR.set(Robot.ROBOTMAP.pivotSpeed);
  // }

  // public void climberPivotReverse() {
  //   System.out.println("reverse pivot");
  //   this.PIVOT_MOTOR.set(-Robot.ROBOTMAP.pivotSpeed);
  // }

  public void configureMotors() {
    this.CLIMBER_MOTOR_LEFT.configFactoryDefault();
    this.CLIMBER_MOTOR_RIGHT.configFactoryDefault();

    this.CLIMBER_MOTOR_LEFT.setNeutralMode(NeutralMode.Brake);
    this.CLIMBER_MOTOR_RIGHT.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void ping() {}

  @Override
  public boolean isAlive() {
    return false;
  }

  @Override
  public void shuffleBoard() {}

  @Override
  public boolean stop() {
    this.CLIMBER_MOTOR_LEFT.stopMotor();
    this.CLIMBER_MOTOR_RIGHT.stopMotor();
    return false;
  }

}