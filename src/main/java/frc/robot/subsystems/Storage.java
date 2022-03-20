package frc.robot.subsystems;

import org.apache.commons.lang3.time.StopWatch;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Robot;
import frc.util.subsystems.MechanicalSubsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Storage extends MechanicalSubsystem {
  //storage motors 
  public final WPI_TalonFX STORAGE_MOTOR_UP;
  public final WPI_TalonSRX STORAGE_MOTOR_IN;

  // SPEED CONTROLLER GROUP
  public final MotorControllerGroup STORAGE_MOTOR_GROUP_UP;
  public final MotorControllerGroup STORAGE_MOTOR_GROUP_IN;

  StopWatch stopWatch = new StopWatch();

  // BEAM BREAKER
  //public DigitalInput beamS = new DigitalInput(Robot.ROBOTMAP.beambreakerPort2);
  //public boolean previousStorage = true;
  public DigitalInput beamI = new DigitalInput(Robot.ROBOTMAP.beamBreakerPortIntake); 
  public DigitalInput beamS = new DigitalInput(Robot.ROBOTMAP.beamBreakerPortStorage);
  boolean previousIntake = true;
  boolean previousIntake2 = true; 

  public Storage() {
    // TALON SRX MOTOR CONTROLLERS
    STORAGE_MOTOR_UP = new WPI_TalonFX(Robot.ROBOTMAP.storageMotorPortUp);
    STORAGE_MOTOR_IN = new WPI_TalonSRX(Robot.ROBOTMAP.storageMotorPortIn);

    // SPEED CONTROLLER GROUPS
    this.STORAGE_MOTOR_GROUP_UP = new MotorControllerGroup(this.STORAGE_MOTOR_UP);
    this.STORAGE_MOTOR_GROUP_IN = new MotorControllerGroup(this.STORAGE_MOTOR_IN);

    configureMotors();
  }

  public void configureMotors() {
    STORAGE_MOTOR_UP.configFactoryDefault();
    STORAGE_MOTOR_IN.configFactoryDefault();

    STORAGE_MOTOR_UP.setNeutralMode(NeutralMode.Coast);
    STORAGE_MOTOR_IN.setNeutralMode(NeutralMode.Coast);
  }

     
  public void storageRun(boolean isForward){
    System.out.println("rooooolling motors");
    if (isForward) {
      STORAGE_MOTOR_GROUP_UP.set(Robot.ROBOTMAP.storageSpeedUp);
    }
    else {
      STORAGE_MOTOR_GROUP_UP.set(-Robot.ROBOTMAP.storageSpeedUp);
      
    }
  }
  //for auto
  public void storageRun(int seconds){
    System.out.println("rooooolling motors");

    stopWatch.start();

    while (stopWatch.getTime() < seconds*10){
        this.STORAGE_MOTOR_GROUP_UP.set(Robot.ROBOTMAP.storageSpeedUp);
    }
  }

  public void storageIn(boolean isForward) {
    if (isForward) {
      this.STORAGE_MOTOR_GROUP_IN.set(-Robot.ROBOTMAP.storageSpeedUp);
    } else {
      this.STORAGE_MOTOR_GROUP_IN.set(Robot.ROBOTMAP.storageSpeedUp);
    }

  }

  public void storageInSucks(boolean isForward) {
    System.out.println("balls moving");
    if (isForward) {
      if(previousIntake != beamI.get() && beamI.get()){
        Robot.ballCount++;
        this.STORAGE_MOTOR_GROUP_IN.set(-Robot.ROBOTMAP.storageSpeedUp);
      }
      else {
        previousIntake = beamI.get();
        this.STORAGE_MOTOR_GROUP_IN.set(Robot.ROBOTMAP.storageSpeedUp);
      }
        // this.STORAGE_MOTOR_GROUP_IN.set(Robot.ROBOTMAP.storageSpeedIn);
    } else {
        this.STORAGE_MOTOR_GROUP_IN.set(-Robot.ROBOTMAP.storageSpeedIn);
    }
  }
  
  // public void rollBallIn(){
  //   if(previousIntake != beamI.get() && beamI.get()){
  //       Robot.ballCount++;
  //       storageIn(true);
  //   }
  //     previousIntake = beamI.get();
  // }

  // public void rollBallOut(){
  //   if(previousIntake != beamS.get() && beamS.get()){
  //     storageRun(5);
  // }
  //   previousIntake2 = beamI.get();
  // }
  
  public boolean storageMotorStop() {
    this.STORAGE_MOTOR_GROUP_UP.set(0);
    this.STORAGE_MOTOR_GROUP_IN.set(0);
    return true;
  }

  // public void storageStop() {
  //   this.STORAGE_M_GROUP.set(0.0);
  // }

  public boolean isAlive() {
    return STORAGE_MOTOR_UP.isAlive() && STORAGE_MOTOR_IN.isAlive();
  }

  public void ping() {
  }

  public boolean stop() {
    this.STORAGE_MOTOR_GROUP_UP.stopMotor();
    this.STORAGE_MOTOR_GROUP_IN.stopMotor();
    return true;
  }

  public void shuffleBoard() {
    SmartDashboard.putBoolean("beam", this.beamI.get());
    SmartDashboard.putBoolean("upper beam (storage)", this.beamS.get());
  }

  // public void countStorage(){
  // if(previousStorage != beamS.get() && beamS.get()){
  //     Robot.ballCount++;
  //   }
  //   previousStorage = beamS.get();
  // }

}