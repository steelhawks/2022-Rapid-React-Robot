package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Storage extends SubsystemBase {

    public final WPI_TalonSRX INTAKE_FRONT_M;
    public final WPI_TalonSRX INTAKE_BACK_M;
  
  
    // SPEED CONTROLLER GROUP
    public final MotorControllerGroup FRONT_M_GROUP;
    public final MotorControllerGroup BACK_M_GROUP;

    public Storage() {
        // TALON SRX MOTOR CONTROLLER
        this.INTAKE_FRONT_M = new WPI_TalonSRX(Robot.ROBOT_MAP.frontIntakeMotor);
        this.INTAKE_BACK_M = new WPI_TalonSRX(Robot.ROBOT_MAP.backIntakeMotor);
    
        // SPEED CONTROLLER GROUP
        this.FRONT_M_GROUP = new MotorControllerGroup(this.INTAKE_FRONT_M);
        this.BACK_M_GROUP = new MotorControllerGroup(this.INTAKE_BACK_M);

        this.BACK_M_GROUP.setInverted(true);
    
        configureMotors();
      }


      public void configureMotors() {
        this.INTAKE_BACK_M.configFactoryDefault();
        this.INTAKE_FRONT_M.configFactoryDefault();
        this.INTAKE_BACK_M.setNeutralMode(NeutralMode.Coast);
        this.INTAKE_FRONT_M.setNeutralMode(NeutralMode.Coast);
      }

     
      public void startIntake(boolean isForward){
        System.out.println("roolling down motors");
        if (isForward) {
            this.FRONT_M_GROUP.set(Robot.ROBOT_MAP.intakeSpeed);
            this.BACK_M_GROUP.set(Robot.ROBOT_MAP.intakeSpeed);
        } else {
            this.FRONT_M_GROUP.set(-Robot.ROBOT_MAP.intakeSpeed);
            this.BACK_M_GROUP.set(-Robot.ROBOT_MAP.intakeSpeed);
        }
      }

      public boolean stopIntake() {
        this.FRONT_M_GROUP.stopMotor();
        this.BACK_M_GROUP.stopMotor();
        return true;
      }

      public void stopClimb() {
        this.FRONT_M_GROUP.set(0.0);
        this.BACK_M_GROUP.set(0.0);
      }


}