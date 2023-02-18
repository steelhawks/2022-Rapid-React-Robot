package frc.robot.commands.Climber;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.ElevatorLevels;

import java.util.HashSet;
import java.util.Set;

public class ElevatorCommand extends CommandBase {

  private ElevatorLevels level;
  private PIDController setter;

  public ElevatorCommand(ElevatorLevels level) {
    this.level = level;

    // setter = new PIDController(0.05, 0.001, 0.01); // 0.05, 0, 0.01     0.04, 0.001245, 0.01075
    setter = new PIDController(0.8, 0, 0);
    setter.setTolerance(0.1);
    setter.setSetpoint(level.getEncoderVal());
    addRequirements(Robot.CLIMBER);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double leftEncoder = Robot.CLIMBER.getEncoderVals()[0];
    double rightEncoder = Robot.CLIMBER.getEncoderVals()[1];

    double leftSpeed = setter.calculate(leftEncoder, level.getEncoderVal());
    double rightSpeed = setter.calculate(rightEncoder, level.getEncoderVal());

    if (level == ElevatorLevels.LOW) {
      leftSpeed = -0.7;
      rightSpeed = -0.7;
    }

    double[] speed = {-leftSpeed, -rightSpeed};
    Robot.CLIMBER.moveToPosition(speed);
  }
    
  @Override
  public boolean isFinished() {

    //Actual Thing
    // return Math.abs(error) > 0 && Math.abs(error) < 0.75; 
    // return balancer.atSetpoint();
    System.out.println("FINISHED");

    if (level == ElevatorLevels.LOW) {
      return !Robot.CLIMBER.rightUpLimit.get() && !Robot.CLIMBER.leftUpLimit.get();
    }
    return setter.atSetpoint();
  }

  @Override
  public void end(boolean interrupted) {
      Robot.CLIMBER.stop();
  }
}