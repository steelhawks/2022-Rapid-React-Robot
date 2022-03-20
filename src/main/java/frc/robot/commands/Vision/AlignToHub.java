package frc.robot.commands.Vision;
import frc.robot.Robot;
import frc.util.Limelight;

import java.util.HashSet;
import java.util.Set;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.Subsystem;


public class AlignToHub extends CommandBase {

  public  AlignToHub() {}

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
    
    
    // Robot.VISION.switchToHubPipeline();
    Robot.DRIVETRAIN.rotateToHub();
    Robot.DRIVETRAIN.straightHubTest();
    // Robot.DRIVETRAIN.goToHub();
  }
    
  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {}
    
}
