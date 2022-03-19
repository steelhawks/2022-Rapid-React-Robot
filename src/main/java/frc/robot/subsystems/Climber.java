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

  
  public double climberLimit;
  
  //280 - 30 is the limit for max extension at 30:1 gear ratio
  //126 is the limit for 5 ft 6 at 20:1 gear ratio
  

  public Climber() {
    this.CLIMBER_MOTOR_LEFT = new WPI_TalonFX(Robot.ROBOTMAP.climberLeftPort);
    this.CLIMBER_MOTOR_RIGHT = new WPI_TalonFX(Robot.ROBOTMAP.climberRightPort);

    this.CLIMBER_SOLENOID_LEFT = new DoubleSolenoid(type, Robot.ROBOTMAP.climberSoleLeftForward, Robot.ROBOTMAP.climberSoleLeftReverse);
    this.CLIMBER_SOLENOID_RIGHT = new DoubleSolenoid(type, Robot.ROBOTMAP.climberSoleRightForward, Robot.ROBOTMAP.climberSoleRightReverse);

    this.CLIMBER_MOTOR_LEFT.setInverted(true);

    leftLimit = new DigitalInput(Robot.ROBOTMAP.leftLimit);
    rightLimit = new DigitalInput(Robot.ROBOTMAP.rightLimit);

    leftEncoderValue = 0;
    rightEncoderValue = 0;

    this.CLIMBER_MOTOR_LEFT.getSensorCollection().setIntegratedSensorPosition(0, 0);
    this.CLIMBER_MOTOR_RIGHT.getSensorCollection().setIntegratedSensorPosition(0, 0);

    configureMotors();
  }


  public void leftClimberRoll(boolean isDown) {

    if (isDown) {

      if (leftLimit.get()) {
        this.CLIMBER_MOTOR_LEFT.set(Robot.ROBOTMAP.climberSpeed);
      } else {
        stopLeft();
      }
    }
    
    else {
      this.CLIMBER_MOTOR_RIGHT.set(-Robot.ROBOTMAP.climberSpeed);
    }
    
  }
  
  public void rightClimberRoll(boolean isDown) {
    
    if (isDown) {
      
      if (rightLimit.get()) {
        this.CLIMBER_MOTOR_RIGHT.set(Robot.ROBOTMAP.climberSpeed);
      } else {
        stopRight();
      }
    }
    
    else {
      this.CLIMBER_MOTOR_RIGHT.set(-Robot.ROBOTMAP.climberSpeed);
    }
  }
  
  
  
  public void climberRoll(boolean isDown) {
    
    if (isDown) {
      
      if (leftLimit.get()) {
        this.CLIMBER_MOTOR_LEFT.set(Robot.ROBOTMAP.climberSpeed);
      } else {
        stopLeft();
        this.CLIMBER_MOTOR_LEFT.getSensorCollection().setIntegratedSensorPosition(0, 0);
        
      }
      
      if (rightLimit.get()) {
        this.CLIMBER_MOTOR_RIGHT.set(Robot.ROBOTMAP.climberSpeed);
      } else {
        stopRight();
        this.CLIMBER_MOTOR_RIGHT.getSensorCollection().setIntegratedSensorPosition(0, 0);
      }
      
    }
    
    else {
      
      //250 is the limit for max extension at 30:1 gear ratio

      //126 is the limit for 5 ft 6 at 20:1 gear ratio

      //20:1 gear ratio code 

      //kinda maybe sometimes really not sure but ok fine // 5 encoder turns is one inch
      climberLimit = this.CLIMBER_SOLENOID_LEFT.get().equals(DoubleSolenoid.Value.kForward) ? 150 : 170; // first one is straight up, second one is back 150 : 170

      double leftEncoderRotations = this.CLIMBER_MOTOR_LEFT.getSensorCollection().getIntegratedSensorPosition() / 2048;
      double rightEncoderRotations = this.CLIMBER_MOTOR_RIGHT.getSensorCollection().getIntegratedSensorPosition() / 2048;

      if (leftEncoderRotations < climberLimit - 20) { //left lags, so we limit less 
        this.CLIMBER_MOTOR_LEFT.set(leftEncoderRotations < climberLimit - 60 ? -Robot.ROBOTMAP.climberSpeed : -Robot.ROBOTMAP.climberSpeedSlow);
      } else {
        System.out.print("LEFT limit");
        System.out.println(leftEncoderRotations);
        stopLeft();
      }
      
      if ((-rightEncoderRotations) < climberLimit - 20) {
        this.CLIMBER_MOTOR_RIGHT.set(leftEncoderRotations < climberLimit - 65 ? -Robot.ROBOTMAP.climberSpeed : -Robot.ROBOTMAP.climberSpeedSlow);
      } else {
        System.out.print("RIGHT limit");
        System.out.println(rightEncoderRotations);
        stopRight();
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

  public void stopLeft() {
    this.CLIMBER_MOTOR_LEFT.stopMotor();
  }

  public void stopRight() {
    this.CLIMBER_MOTOR_RIGHT.stopMotor();
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
    SmartDashboard.putBoolean("left sensor", leftLimit.get());
    SmartDashboard.putBoolean("right sensor", rightLimit.get());
    SmartDashboard.putNumber("Left Voltage", this.CLIMBER_MOTOR_LEFT.getMotorOutputVoltage());
    SmartDashboard.putNumber("Right Voltage", this.CLIMBER_MOTOR_RIGHT.getMotorOutputVoltage());
    SmartDashboard.putNumber("Left Amperage", this.CLIMBER_MOTOR_LEFT.getSupplyCurrent());
    SmartDashboard.putNumber("Right Amperage", this.CLIMBER_MOTOR_RIGHT.getSupplyCurrent());
  }

  @Override
  public boolean stop() {
    this.CLIMBER_MOTOR_LEFT.stopMotor();
    this.CLIMBER_MOTOR_RIGHT.stopMotor();
    return false;
  }

}