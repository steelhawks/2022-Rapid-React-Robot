package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class StorageTest extends SubsystemBase {

    public final WPI_TalonSRX UP_M_LEFT;
    public final WPI_TalonSRX UP_M_RIGHT;
    
    //SPARK MAX RIGHT MOTOR
    public final WPI_TalonSRX DOWN_M_LEFT;
    public final WPI_TalonSRX DOWN_M_RIGHT;
  
  
    // SPEED CONTROLLER GROUP
    public final MotorControllerGroup UP_M_GROUP;
    public final MotorControllerGroup DOWN_M_GROUP;

    public StorageTest() {
        // TALON SRX MOTOR CONTROLLER
        this.UP_M_LEFT = new WPI_TalonSRX(Robot.ROBOTMAP.getLeftMotorPortUp());
        this.UP_M_RIGHT = new WPI_TalonSRX(Robot.ROBOTMAP.getRightMotorPortUp());
        this.DOWN_M_LEFT = new WPI_TalonSRX(Robot.ROBOTMAP.getLeftMotorPortDown());
        this.DOWN_M_RIGHT = new WPI_TalonSRX(Robot.ROBOTMAP.getRightMotorPortDown());
    
        // SPEED CONTROLLER GROUP
        this.UP_M_GROUP = new MotorControllerGroup(this.UP_M_LEFT, this.UP_M_RIGHT);
        this.DOWN_M_GROUP = new MotorControllerGroup(this.DOWN_M_LEFT, this.DOWN_M_RIGHT);

        // this.UP_M_RIGHT.setInverted(true);
        // this.DOWN_M_GROUP.setInverted(true);
        // this.DOWN_M_RIGHT.setInverted(true);
    
        configureMotors();
      }


      public void configureMotors() {
        this.UP_M_LEFT.configFactoryDefault();
        this.UP_M_RIGHT.configFactoryDefault();
        this.DOWN_M_LEFT.configFactoryDefault();
        this.DOWN_M_RIGHT.configFactoryDefault();
        this.UP_M_LEFT.setNeutralMode(NeutralMode.Coast);
        this.UP_M_RIGHT.setNeutralMode(NeutralMode.Coast);
        this.DOWN_M_LEFT.setNeutralMode(NeutralMode.Coast);
        this.DOWN_M_RIGHT.setNeutralMode(NeutralMode.Coast);
      }

      public void rollUp(boolean isForward) {
        System.out.println("rolling up motors");
        if (isForward) {
          this.UP_M_GROUP.set(Robot.ROBOTMAP.getintakeSpeed());
        } else {
          this.UP_M_GROUP.set(-Robot.ROBOTMAP.getintakeSpeed());
        }
      }

      public void rollDown(boolean isForward){
        System.out.println("roolling down motors");
        if (isForward) {
            this.DOWN_M_GROUP.set(Robot.ROBOTMAP.getintakeSpeed());
        } else {
            this.DOWN_M_GROUP.set(-Robot.ROBOTMAP.getintakeSpeed());
        }
      }

      public boolean stopUp() {
        this.UP_M_GROUP.stopMotor();
        return true;
      }

      public boolean stopDown() {
        this.DOWN_M_GROUP.stopMotor();
        return true;
      }

      public void stopClimb() {
        this.UP_M_GROUP.set(0.0);
        this.DOWN_M_GROUP.set(0.0);
      }


}
