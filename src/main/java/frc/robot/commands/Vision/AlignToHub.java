package frc.robot.commands.Vision;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
import frc.util.Limelight;

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
    Limelight.setPipeline(Robot.VISION.getHubPipeline());
    Robot.VISION.switchToHubPipeline();
    
    Robot.DRIVETRAIN.rotateToHub();
    Robot.DRIVETRAIN.straightHubTest(); //Danger
  }
    
  @Override
  public boolean isFinished() {
    return Robot.DRIVETRAIN.angleInRange;
  }

  @Override
  public void end(boolean interrupted) {}
    
}
