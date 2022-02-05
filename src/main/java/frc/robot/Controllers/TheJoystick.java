package frc.robot.Controllers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class TheJoystick {

  private final Joystick joystick;

  public TheJoystick(Joystick joystick) {
    this.joystick = joystick;
  }

  public JoystickButton mapButton(int buttonNumber){
    return new JoystickButton(this.joystick, buttonNumber);
  }

}