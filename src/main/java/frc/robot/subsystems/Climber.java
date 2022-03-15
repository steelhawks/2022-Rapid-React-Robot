package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.util.subsystems.MechanicalSubsystem;

public class Climber extends MechanicalSubsystem {

  public final WPI_TalonFX CLIMBER_MOTOR_LEFT;
  public final WPI_TalonFX CLIMBER_MOTOR_RIGHT;

  public final DoubleSolenoid CLIMBER_SOLENOID_LEFT;
  public final DoubleSolenoid CLIMBER_SOLENOID_RIGHT;

  private final PneumaticsModuleType type = PneumaticsModuleType.CTREPCM;

  public final DigitalInput leftLimit;
  public final DigitalInput rightLimit;

  public double leftEncoderValue;
  public double rightEncoderValue;

  public Climber() {
    this.CLIMBER_MOTOR_LEFT = new WPI_TalonFX(Robot.ROBOTMAP.climberLeftPort);
    this.CLIMBER_MOTOR_RIGHT = new WPI_TalonFX(Robot.ROBOTMAP.climberRightPort);

    this.CLIMBER_SOLENOID_LEFT = new DoubleSolenoid(type, 4, 5);
    this.CLIMBER_SOLENOID_RIGHT = new DoubleSolenoid(type, 6, 7);

    this.CLIMBER_MOTOR_LEFT.setInverted(true);

    leftLimit = new DigitalInput(Robot.ROBOTMAP.leftLimit);
    rightLimit = new DigitalInput(Robot.ROBOTMAP.rightLimit);

    leftEncoderValue = 0;
    rightEncoderValue = 0;

    this.CLIMBER_MOTOR_LEFT.getSensorCollection().setIntegratedSensorPosition(0, 0);
    this.CLIMBER_MOTOR_RIGHT.getSensorCollection().setIntegratedSensorPosition(0, 0);

    configureMotors();
  }

  public void climberRoll(boolean isDown) {
    System.out.println("climber");

    if (isDown) {

      if (leftLimit.get()) {
        this.CLIMBER_MOTOR_LEFT.set(Robot.ROBOTMAP.climberSpeed);
      } else {
        stop();
      }

      if (rightLimit.get()) {
        this.CLIMBER_MOTOR_RIGHT.set(Robot.ROBOTMAP.climberSpeed);
      } else {
        stop();
      }

    }

    else {

      if ((this.CLIMBER_MOTOR_LEFT.getSensorCollection().getIntegratedSensorPosition() / 2048) < 10) {

        this.CLIMBER_MOTOR_LEFT.set(-Robot.ROBOTMAP.climberSpeed);
      }

      else {
        stop();
      }

      if ((this.CLIMBER_MOTOR_RIGHT.getSensorCollection().getIntegratedSensorPosition() / 2048) < 150) {
        this.CLIMBER_MOTOR_RIGHT.set(-Robot.ROBOTMAP.climberSpeed);
      }

      else {
        stop();
      }

    }
  }

  // public void climberRollWinch() {
  // System.out.println("forwards");
  // this.CLIMBER_MOTOR_LEFT.set(Robot.ROBOTMAP.climberSpeed);
  // }

  // public void climberUnrollWinch() {
  // System.out.println("backwards");
  // this.CLIMBER_MOTOR_LEFT.set(-Robot.ROBOTMAP.climberSpeed);
  // }

  public void climberToggleSolenoid() {
    if (this.CLIMBER_SOLENOID_LEFT.get().equals(DoubleSolenoid.Value.kForward)) {
      climberRetractSolenoid();
      System.out.println("Climber Retract: point diagonal back");
    } else {
      climberExtendSolenoid();
      System.out.println("Climber extend: Pointed Straight UP");
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
  // System.out.println("pivot");
  // this.PIVOT_MOTOR.set(Robot.ROBOTMAP.pivotSpeed);
  // }

  // public void climberPivotReverse() {
  // System.out.println("reverse pivot");
  // this.PIVOT_MOTOR.set(-Robot.ROBOTMAP.pivotSpeed);
  // }

  public void configureMotors() {
    this.CLIMBER_MOTOR_LEFT.configFactoryDefault();
    this.CLIMBER_MOTOR_RIGHT.configFactoryDefault();

    this.CLIMBER_MOTOR_LEFT.setNeutralMode(NeutralMode.Brake);
    this.CLIMBER_MOTOR_RIGHT.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void ping() {
  }

  @Override
  public boolean isAlive() {
    return false;
  }

  @Override
  public void shuffleBoard() {
    SmartDashboard.putNumber("L encoder rotation",
        this.CLIMBER_MOTOR_LEFT.getSensorCollection().getIntegratedSensorPosition() / 2048);
    SmartDashboard.putNumber("R encoder rotation",
        this.CLIMBER_MOTOR_LEFT.getSensorCollection().getIntegratedSensorPosition() / 2048);
  }

  @Override
  public boolean stop() {
    this.CLIMBER_MOTOR_LEFT.stopMotor();
    this.CLIMBER_MOTOR_RIGHT.stopMotor();
    return false;
  }

}