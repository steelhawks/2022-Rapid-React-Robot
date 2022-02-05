package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class RetractSolenoid extends CommandBase {
    public RetractSolenoid() {
        addRequirements(Robot.INTAKE);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        Robot.INTAKE.retract();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
    }
}
