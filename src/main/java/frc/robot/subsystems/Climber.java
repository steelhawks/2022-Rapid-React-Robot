package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
// import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.util.subsystems.MechanicalSubsystem;

public class Climber extends MechanicalSubsystem{

  public final Talon winch = new Talon(Robot.ROBOTMAP.getWinchPort()); //?????

  public void rollWinch(boolean isFoward) {
    System.out.println("winching");
    if(isFoward) {
      this.winch.set(Robot.ROBOTMAP.getClimberSpeed());
      System.out.println("foward");
    }
  }
  public void unrollWinch(boolean isFoward) {
    if(isFoward == false) {
      this.winch.set(-Robot.ROBOTMAP.climberSpeed);
      System.out.println("backwards");
    }
  }
  
  public boolean stop() {
    this.winch.stopMotor();
    return true;
  }

  @Override
  public void ping() {}

  @Override
  public boolean isAlive() {
    return false;
  }
@Override
public void shuffleBoard() {
    // TODO Auto-generated method stub
    
}




}