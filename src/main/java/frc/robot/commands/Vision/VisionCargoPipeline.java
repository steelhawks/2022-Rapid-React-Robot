package frc.robot.commands.Vision;

import java.util.HashSet;
import java.util.Set;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;

public class VisionCargoPipeline extends CommandBase {

  public VisionCargoPipeline() {}

  @Override
  public Set<Subsystem> getRequirements() {
    Set<Subsystem> list = new HashSet<Subsystem>();
    list.add(Robot.VISION);
    return list;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    Robot.VISION.setPipelineColor();
    System.out.println("executing pipeline change");
  } 
  
  @Override
  public boolean isFinished() 
  {
    return true;
  }

  @Override
  public void end(boolean interrupted) {}
}
