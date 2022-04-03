package frc.robot.commands.Vision;
import frc.robot.Robot;
import frc.util.Limelight;

import java.util.HashSet;
import java.util.Set;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;


public class VisionAdjustBall extends CommandBase {
    
  public VisionAdjustBall() {}

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
    Robot.DRIVETRAIN.adjustBall();
  }
    
  @Override
  public boolean isFinished() {
    return !Robot.DRIVETRAIN.ballUnaligned || !Limelight.hasValidTarget();
  }

  @Override
  public void end(boolean interrupted) {
    Robot.DRIVETRAIN.stop();
  }
    
}
