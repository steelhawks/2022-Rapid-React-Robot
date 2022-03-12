package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Robot;
import frc.util.subsystems.MechanicalSubsystem;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class Storage extends MechanicalSubsystem {
    //storage motors 
    public final WPI_TalonFX STORAGE_MOTOR_UP;
    public final WPI_TalonSRX STORAGE_MOTOR_IN;
  
    // SPEED CONTROLLER GROUP
    public final MotorControllerGroup STORAGE_MOTOR_GROUP_UP;
    public final MotorControllerGroup STORAGE_MOTOR_GROUP_IN;

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
            this.STORAGE_MOTOR_GROUP_UP.set(-Robot.ROBOTMAP.storageSpeedUp);
        } else {
            this.STORAGE_MOTOR_GROUP_UP.set(Robot.ROBOTMAP.storageSpeedUp);
        }
      }

      public void storageIn(boolean isForward) {
        System.out.println("balls moving");
        if (isForward) {
            this.STORAGE_MOTOR_GROUP_IN.set(Robot.ROBOTMAP.storageSpeedIn);
        } else {
            this.STORAGE_MOTOR_GROUP_IN.set(-Robot.ROBOTMAP.storageSpeedIn);
        }
      }

      public boolean storageUpStop() {
        this.STORAGE_MOTOR_GROUP_UP.stopMotor();
        return true;
      }

      public boolean storageInStop() {
        this.STORAGE_MOTOR_GROUP_IN.stopMotor();
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
      
      }


}