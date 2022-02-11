package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Robot;
import frc.util.subsystems.MechanicalSubsystem;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class Storage extends MechanicalSubsystem {
    //intake motors 
    public final WPI_TalonSRX STORAGE_M_FRONT;
    public final WPI_TalonSRX STORAGE_M_BACK;
  
  
    // SPEED CONTROLLER GROUP
    public final MotorControllerGroup STORAGE_M_GROUP;

    public Storage() {
        // TALON SRX MOTOR CONTROLLER
        STORAGE_M_FRONT = new WPI_TalonSRX(Robot.ROBOTMAP.storageMotorPortOne);
        STORAGE_M_BACK = new WPI_TalonSRX(Robot.ROBOTMAP.storageMotorPortTwo);
    
        // SPEED CONTROLLER GROUP
        this.STORAGE_M_GROUP = new MotorControllerGroup(this.STORAGE_M_BACK, this.STORAGE_M_FRONT);

        STORAGE_M_BACK.setInverted(true);
    
        configureMotors();
      }


      public void configureMotors() {
        STORAGE_M_FRONT.configFactoryDefault();
        STORAGE_M_BACK.configFactoryDefault();
        STORAGE_M_FRONT.setNeutralMode(NeutralMode.Coast);
        STORAGE_M_BACK.setNeutralMode(NeutralMode.Coast);
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
        return STORAGE_M_FRONT.isAlive() && STORAGE_M_BACK.isAlive();
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