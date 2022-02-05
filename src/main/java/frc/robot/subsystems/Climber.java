package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
// import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.Robot;
import frc.util.subsystems.MechanicalSubsystem;

public class Climber extends MechanicalSubsystem{

  //TODO:FIX EVERYTHING 

  public final Talon winch = new Talon(Robot.ROBOTMAP.WINCH_PORT); //?????

  public void rollWinch(boolean isFoward) {
    System.out.println("winching");
    if(isFoward) {
      this.winch.set(Robot.ROBOTMAP.climberSpeed);
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
    // TODO add values
    
}




}