package frc.robot;

public class RobotMap 
{
  /*Store instance variables, values, ports needed for the robot*/

  //Left Motor Ports
  public final int leftMotorOne = 1;
  public final int leftMotorTwo = 2;
  public final int leftMotorThree = 3;

  //Right Motor Ports
  public final int rightMotorOne = 4;
  public final int rightMotorTwo = 5;
  public final int rightMotorThree = 6;

  //Gear Shift Solenoids
  public final int driveSolenoidOn = 0;
  public final int driveSolenoidOff = 1;

  //Storage Motors
  public final int frontIntakeMotor = 7;
  public final int backIntakeMotor = 8;

  //Storage Speed
  public final double intakeSpeed = 1;


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

  public int getLeftMotorOne()
  {
    return this.leftMotorOne;
  }

  public int getLeftMotorTwo()
  {
    return this.leftMotorTwo;
  }

  public int getLeftMotorThree()
  {
    return this.leftMotorThree;
  }

  public int getRightMotorOne()
  {
    return this.rightMotorOne;
  }

  public int getRightMotorTwo()
  {
    return this.rightMotorTwo;
  }

  public int getRightMotorThree()
  {
    return this.rightMotorThree;
  }

  public int getJoystickPortOne()
  {
    return this.JOYSTICK_PORT_ONE;
  }

}
