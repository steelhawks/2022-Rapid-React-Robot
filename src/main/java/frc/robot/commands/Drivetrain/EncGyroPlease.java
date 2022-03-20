package frc.robot.commands.Drivetrain;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 *
 */
public class EncGyroPlease extends CommandBase {
	
	double leftDistance = 0;
	double rightDistance = 0;
	
    public EncGyroPlease(double leftDist, double rightDist) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	addRequirements(Robot.DRIVETRAIN);
    	leftDistance = leftDist;
    	rightDistance = rightDist;
    }
    // Called just before this Command runs the first time
    public void initialize() {
    	Robot.DRIVETRAIN.ultraBool = false;
    	Robot.DRIVETRAIN.GYRO.reset();
    	Robot.DRIVETRAIN.GYRO.zeroYaw();
    	Robot.DRIVETRAIN.leftEnc.reset();
    	Robot.DRIVETRAIN.rightEnc.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
    	Robot.DRIVETRAIN.EncGyroForward(leftDistance, rightDistance);
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
        return Robot.DRIVETRAIN.ultraBool;
    }

    // Called once after isFinished returns true
    public void end() {
    	Robot.DRIVETRAIN.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    public void interrupted() {
    }
}