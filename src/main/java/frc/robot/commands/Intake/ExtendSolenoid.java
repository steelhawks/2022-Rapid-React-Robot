package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ExtendSolenoid extends CommandBase {
    public ExtendSolenoid() {
        addRequirements(Robot.INTAKE);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        Robot.INTAKE.extend();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
    }
}
