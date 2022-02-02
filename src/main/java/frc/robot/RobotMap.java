package frc.robot;

public class RobotMap 
{
  /*Store instance variables, values, ports needed for the robot*/

  //Motor Ports
  private final int MOTOR_PORT = 2;

  //climbtest
  private final double motorspeed = 1;

  //Input Ports
  private final int JOYSTICK_PORT_ONE = 0;

  /*****
   * Constructor methods
   *****/
  public RobotMap() {}

  /*****
   * Getter methods
   *****/
  public double getintakeSpeed(){
    return this.motorspeed;
  }

  public int getRightMotorPortUp()
  {
    return this.MOTOR_PORT;
  }

  public int getJoystickPortOne()
  {
    return this.JOYSTICK_PORT_ONE;
  }

}
