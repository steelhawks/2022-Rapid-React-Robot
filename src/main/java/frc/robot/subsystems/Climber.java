package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Robot;
import frc.util.subsystems.MechanicalSubsystem;

public class Climber extends MechanicalSubsystem {

  public final WPI_TalonSRX WINCH_MOTOR;
  public final WPI_TalonSRX PIVOT_MOTOR;

  public Climber() {
    this.WINCH_MOTOR = new WPI_TalonSRX(Robot.ROBOTMAP.WINCH_PORT);
    this.PIVOT_MOTOR = new WPI_TalonSRX(Robot.ROBOTMAP.PIVOT_PORT);

    configureMotors();
  }

  public void climberRollWinch() {
    System.out.println("forward");
    this.WINCH_MOTOR.set(Robot.ROBOTMAP.climberSpeed);
  }
  public void climberUnrollWinch() {
    System.out.println("backwards");
    this.WINCH_MOTOR.set(-Robot.ROBOTMAP.climberSpeed);
  }

  public void climberPivotForward() {
    System.out.println("pivot");
    this.PIVOT_MOTOR.set(Robot.ROBOTMAP.pivotSpeed);
  }

  public void climberPivotReverse() {
    System.out.println("reverse pivot");
    this.PIVOT_MOTOR.set(-Robot.ROBOTMAP.pivotSpeed);
  }

  public void configureMotors() {
    this.WINCH_MOTOR.configFactoryDefault();
    this.PIVOT_MOTOR.configFactoryDefault();

    this.WINCH_MOTOR.setNeutralMode(NeutralMode.Brake);
    this.PIVOT_MOTOR.setNeutralMode(NeutralMode.Brake);
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
    this.WINCH_MOTOR.stopMotor();
    this.PIVOT_MOTOR.stopMotor();
    return false;
  }

}