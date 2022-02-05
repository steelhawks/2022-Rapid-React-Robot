package frc.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Controller {

  private final Joystick joystick;

  public Controller(Joystick joystick) {
    this.joystick = joystick;
  }


  //Used to give a JoystickButton for Commands in other Controller Files
  public JoystickButton mapButton(int buttonNumber){
    return new JoystickButton(this.joystick, buttonNumber);
  }

}