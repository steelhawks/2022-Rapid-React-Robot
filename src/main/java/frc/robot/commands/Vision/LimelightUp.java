package frc.robot.commands.Vision;
import frc.robot.Robot;
import java.util.HashSet;
import java.util.Set;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;


public class LimelightUp extends CommandBase {
    
  public LimelightUp() {}

  @Override
  public Set<Subsystem> getRequirements() 
  {
    Set<Subsystem> list = new HashSet<Subsystem>();
    list.add(Robot.DRIVETRAIN);
    return list;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    Robot.VISION.switchToHubPipeline();
    Robot.VISION.faceLimelightUp();
  }
    
  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {}
    
}
