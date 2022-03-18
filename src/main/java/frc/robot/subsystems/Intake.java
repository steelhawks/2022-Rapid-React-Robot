package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Robot;
import frc.util.subsystems.MechanicalSubsystem;

public class Intake extends MechanicalSubsystem {
    
  // TALON SRX MOTOR CONTROLLER
  public final WPI_TalonSRX INTAKE_MOTOR_ONE;

  // SPEED CONTROLLER GROUP
  public final MotorControllerGroup INTAKE_MOTOR_GROUP;

  // SOLENOID
  public DoubleSolenoid INTAKE_SOLENOID;

  //Solenoid Type
  private final PneumaticsModuleType type = PneumaticsModuleType.CTREPCM;

  // BEAM BREAKER
  public DigitalInput beamI = new DigitalInput(Robot.ROBOTMAP.beamBreakerPortOne);
  boolean previousIntake = true;

  // DRIVETRAIN CONSTRUCTOR
  public Intake() {
    // TALON SRX MOTOR CONTROLLER
    this.INTAKE_MOTOR_ONE = new WPI_TalonSRX(Robot.ROBOTMAP.intakeMotorOnePort);

    // SPEED CONTROLLER GROUPS
    this.INTAKE_MOTOR_GROUP = new MotorControllerGroup(this.INTAKE_MOTOR_ONE);

    // SOLENOID
    this.INTAKE_SOLENOID = new DoubleSolenoid(type, Robot.ROBOTMAP.intakeSoleForward, Robot.ROBOTMAP.intakeSoleReverse);
    
    configureMotors();
  }

  public void spinRoller(boolean isForward) {
    // System.out.println("spinning motors");
    if (isForward) {
      this.INTAKE_MOTOR_GROUP.set(-Robot.ROBOTMAP.intakeSpeed);
    } else {
      this.INTAKE_MOTOR_GROUP.set(Robot.ROBOTMAP.intakeSpeed);
    }
  }

  public void spinRoller(double speed) {
    // System.out.println("intake set to spin at:" + speed);
    this.INTAKE_MOTOR_GROUP.set(-speed);
  }

  public void spinRollerReverse(double speed) {
    // System.out.println("intake spinning out at:" + speed);
    this.INTAKE_MOTOR_GROUP.set(speed);
  }

  public boolean stopRoll() {
    this.INTAKE_MOTOR_GROUP.stopMotor();
    return true;
  }

  public void intakeToggleSolenoid() {
    if (this.INTAKE_SOLENOID.get().equals(DoubleSolenoid.Value.kForward)) {
      intakeRetractSolenoid();
      System.out.println("intake up: toggle: retract intake pistons");
    } else {
      intakeExtendSolenoid();
      System.out.println("intake down : toggle: extend intake pistons");
    }
    
  }

  public void intakeExtendSolenoid() {
    System.out.println("extend intake");
    this.INTAKE_SOLENOID.set(DoubleSolenoid.Value.kForward);
  }

  public void intakeRetractSolenoid() {
    System.out.println("retract");
    this.INTAKE_SOLENOID.set(DoubleSolenoid.Value.kReverse);
  }

  public void configureMotors() {
    this.INTAKE_MOTOR_ONE.configFactoryDefault();

    this.INTAKE_MOTOR_ONE.setNeutralMode(NeutralMode.Coast);
  }

  public void smartDashboard() {
    SmartDashboard.putNumber("Intake Motor One Speed", this.INTAKE_MOTOR_ONE.get());
  }

  public boolean isAlive() {
    return INTAKE_MOTOR_ONE.isAlive();
  }

  public void ping() {
  }

  public boolean stop() {
    this.INTAKE_MOTOR_ONE.stopMotor();
    return true;
  }


  public void shuffleBoard() {
	
  }

  public void countIntake(){
    if(previousIntake != beamI.get() && beamI.get()){
      Robot.ballCount++;
    }
    previousIntake = beamI.get();
  }
}