package frc.robot.commands.Vision;
import frc.robot.Robot;

import java.util.HashSet;
import java.util.Set;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;


public class AutonPickUpBall2 extends CommandBase {
    
  public AutonPickUpBall2() {}

  @Override
  public Set<Subsystem> getRequirements() 
  {
    Set<Subsystem> list = new HashSet<Subsystem>();
    list.add(Robot.VISION);
    return list;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    // Robot.VISION.autonPickUpBall2();
  }
    
  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {}
    
}
