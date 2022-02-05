package frc.robot.commands.Climber;
// import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
// import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;

public class RollWinch extends CommandBase {

    public RollWinch() {
        addRequirements(Robot.CLIMBER);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        Robot.CLIMBER.rollWinch(true);
    }
    
        @Override
        public boolean isFinished() {
          return true;
        }
      
        @Override
        public void end(boolean interrupted) {
          if (interrupted) {
            Robot.CLIMBER.stop();
        }
    }
}