package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Robot;
import frc.util.subsystems.MechanicalSubsystem;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class Storage extends MechanicalSubsystem {
    //storage motors 
    public final WPI_TalonFX STORAGE_M_UP;
    public final WPI_TalonSRX STORAGE_M_IN;
  
    // SPEED CONTROLLER GROUP
    public final MotorControllerGroup STORAGE_M_GROUP_UP;
    public final MotorControllerGroup STORAGE_M_GROUP_IN;

    public Storage() {
        // TALON SRX MOTOR CONTROLLERS
        STORAGE_M_UP = new WPI_TalonFX(Robot.ROBOTMAP.storageMotorPortUp);
        STORAGE_M_IN = new WPI_TalonSRX(Robot.ROBOTMAP.storageMotorPortIn);
    
        // SPEED CONTROLLER GROUPS
        this.STORAGE_M_GROUP_UP = new MotorControllerGroup(this.STORAGE_M_UP);
        this.STORAGE_M_GROUP_IN = new MotorControllerGroup(this.STORAGE_M_IN);
    
        configureMotors();
      }


      public void configureMotors() {
        STORAGE_M_UP.configFactoryDefault();
        STORAGE_M_IN.configFactoryDefault();

        STORAGE_M_UP.setNeutralMode(NeutralMode.Coast);
        STORAGE_M_IN.setNeutralMode(NeutralMode.Coast);
      }

     
      public void storageRun(boolean isForward){
        System.out.println("rooooolling motors");
        if (isForward) {
            this.STORAGE_M_GROUP_UP.set(-Robot.ROBOTMAP.storageSpeedUp);
        } else {
            this.STORAGE_M_GROUP_UP.set(Robot.ROBOTMAP.storageSpeedUp);
        }
      }

      public void storageIn(boolean isForward) {
        System.out.println("balls moving");
        if (isForward) {
            this.STORAGE_M_GROUP_IN.set(Robot.ROBOTMAP.storageSpeedIn);
        } else {
            this.STORAGE_M_GROUP_IN.set(-Robot.ROBOTMAP.storageSpeedIn);
        }
      }

      public boolean storageMotorStop() {
        this.STORAGE_M_GROUP_UP.stopMotor();
        this.STORAGE_M_GROUP_IN.stopMotor();
        return true;
      }

      // public void storageStop() {
      //   this.STORAGE_M_GROUP.set(0.0);
      // }

      public boolean isAlive() {
        return STORAGE_M_UP.isAlive() && STORAGE_M_IN.isAlive();
      }
    
      public void ping() {
      }
    
      public boolean stop() {
        STORAGE_M_GROUP_UP.stopMotor();
        STORAGE_M_GROUP_IN.stopMotor();
        return true;
      }
    
      public void shuffleBoard() {
      
      }


}