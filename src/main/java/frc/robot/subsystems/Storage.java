package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Robot;
import frc.util.subsystems.MechanicalSubsystem;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class Storage extends MechanicalSubsystem {
    //intake motors 
    public final WPI_TalonSRX STORAGE_M_FRONT;
  
    // SPEED CONTROLLER GROUP
    public final MotorControllerGroup STORAGE_M_GROUP;

    public Storage() {
        // TALON SRX MOTOR CONTROLLER
        STORAGE_M_FRONT = new WPI_TalonSRX(Robot.ROBOTMAP.storageMotorPortOne);
    
        // SPEED CONTROLLER GROUP
        this.STORAGE_M_GROUP = new MotorControllerGroup(this.STORAGE_M_FRONT);
    
        configureMotors();
      }


      public void configureMotors() {
        STORAGE_M_FRONT.configFactoryDefault();
        STORAGE_M_FRONT.setNeutralMode(NeutralMode.Coast);
      }

     
      public void storageRun(boolean isForward){
        System.out.println("rooooolling motors");
        if (isForward) {
            this.STORAGE_M_GROUP.set(Robot.ROBOTMAP.intakeSpeed);
        } else {
            this.STORAGE_M_GROUP.set(-Robot.ROBOTMAP.intakeSpeed);
        }
      }

      public boolean storageMotorStop() {
        this.STORAGE_M_GROUP.stopMotor();
        return true;
      }

      public void storageStop() {
        this.STORAGE_M_GROUP.set(0.0);
      }

      public boolean isAlive() {
        return STORAGE_M_FRONT.isAlive();
      }
    
      public void ping() {
      }
    
      public boolean stop() {
        STORAGE_M_GROUP.stopMotor();
        return true;
      }
    
      public void shuffleBoard() {
      
      }


}