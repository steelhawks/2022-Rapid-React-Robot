package frc.robot.commands.Climber;
// import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
// import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;

public class LeftClimberUp extends CommandBase {

    public LeftClimberUp() {
        addRequirements(Robot.CLIMBER);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        Robot.CLIMBER.leftClimberRoll(false);; //down
    }

    @Override
    public boolean isFinished() {
        return false;
    }
      
    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            Robot.CLIMBER.stop();
        }
    }
}