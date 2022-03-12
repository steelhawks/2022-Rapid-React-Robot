package frc.robot.commands.Vision;
import frc.robot.Robot;

import java.util.HashSet;
import java.util.Set;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;


public class AlignToHub extends CommandBase {

  public AlignToHub() {}

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
    Robot.DRIVETRAIN.rotateToHub();
    Robot.DRIVETRAIN.straightHub();
  }
    
  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {}
    
}
