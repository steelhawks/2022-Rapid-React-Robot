package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.util.LEDColor;
import frc.util.LEDMode;

public class LedCommand extends CommandBase {

  private LEDColor color;
  private LEDMode mode;
  private final double pulseInterval = 0.1;

  public LedCommand(LEDColor color, LEDMode mode) {
    this.color = color;
    this.mode = mode;

    addRequirements(Robot.led);
  }

  @Override
  public void initialize() {
    if (mode != LEDMode.RAINBOW) {
      Robot.led.setColor(color);
    }
  }

  @Override
  public void execute() {

    if (mode == LEDMode.PULSE) {
      Robot.led.pulse(color, pulseInterval);
    }
    else if (mode == LEDMode.STATIC) {
      Robot.led.setColor(color);

    }
    else if (mode == LEDMode.RAINBOW) {
      Robot.led.rainbow();
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    if (mode == LEDMode.STATIC) {
      return true;
    }
    else {
      return false;
    }
  }
}
