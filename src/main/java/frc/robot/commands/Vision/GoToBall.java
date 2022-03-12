package frc.robot.commands.Vision;
import frc.robot.Robot;
import frc.util.Limelight;

import java.util.HashSet;
import java.util.Set;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;


public class GoToBall extends CommandBase {
    
  public GoToBall() {}

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
    // Robot.VISION.setPipelineColor();
    Limelight.setPipeline(Robot.VISION.getBallPipeline());  

    Robot.DRIVETRAIN.goToBall();
  }
    
  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {}
    
}
