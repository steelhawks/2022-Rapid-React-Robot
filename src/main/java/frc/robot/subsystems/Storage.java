package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Robot;
import frc.util.subsystems.MechanicalSubsystem;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class Storage extends MechanicalSubsystem {
    //storage motors 
    public final WPI_TalonFX STORAGE_MOTOR_UP_ONE;
    public final WPI_TalonFX STORAGE_MOTOR_UP_TWO;
    public final WPI_TalonSRX STORAGE_MOTOR_IN;
  
    // SPEED CONTROLLER GROUP
    public final MotorControllerGroup STORAGE_MOTOR_GROUP_UP;
    public final MotorControllerGroup STORAGE_MOTOR_GROUP_IN;

    public Storage() {
        // TALON SRX MOTOR CONTROLLERS
        STORAGE_MOTOR_UP_ONE = new WPI_TalonFX(Robot.ROBOTMAP.storageMotorPortUpOne);
        STORAGE_MOTOR_UP_TWO = new WPI_TalonFX(Robot.ROBOTMAP.storageMotorPortUpTwo);
        STORAGE_MOTOR_IN = new WPI_TalonSRX(Robot.ROBOTMAP.storageMotorPortIn);
    
        // SPEED CONTROLLER GROUPS
        this.STORAGE_MOTOR_GROUP_UP = new MotorControllerGroup(this.STORAGE_MOTOR_UP_ONE, this.STORAGE_MOTOR_UP_TWO);
        this.STORAGE_MOTOR_GROUP_IN = new MotorControllerGroup(this.STORAGE_MOTOR_IN);

        this.STORAGE_MOTOR_UP_ONE.setInverted(false);
        this.STORAGE_MOTOR_UP_TWO.setInverted(true);
    
        configureMotors();
      }


      public void configureMotors() {
        STORAGE_MOTOR_UP_ONE.configFactoryDefault();
        STORAGE_MOTOR_IN.configFactoryDefault();

        STORAGE_MOTOR_UP_ONE.setNeutralMode(NeutralMode.Coast);
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

      public void storageTest(int motor) {
        if(motor == 0) {
          this.STORAGE_MOTOR_UP_ONE.set(Robot.ROBOTMAP.storageSpeedUp);
        } else {
          this.STORAGE_MOTOR_UP_TWO.set(Robot.ROBOTMAP.storageSpeedUp);
        }
      }

      public boolean storageMotorStop() {
        this.STORAGE_MOTOR_GROUP_UP.stopMotor();
        this.STORAGE_MOTOR_GROUP_IN.stopMotor();
        
        return true;
      }

      // public void storageStop() {
      //   this.STORAGE_M_GROUP.set(0.0);
      // }

      public boolean isAlive() {
        return STORAGE_MOTOR_UP_ONE.isAlive() && STORAGE_MOTOR_IN.isAlive();
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