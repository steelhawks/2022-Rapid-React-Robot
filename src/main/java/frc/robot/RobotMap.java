package frc.robot;

public class RobotMap 
{

  public RobotMap() {}
  
  /*Store instance variables, values, ports needed for the robot*/

  //Joystick Input Ports

  
  final int joystickPortOne = 0;

  //DRIVETRAIN Motor ID
  public final int drivetrainLeftMotorPortOne = 1;
  public final int drivetrainLeftMotorPortTwo = 2;
  public final int drivetrainLeftMotorPortThree = 3;
  
  public final int drivetrainRightMotorPortOne = 4;
  public final int drivetrainRightMotorPortTwo = 5;
  public final int drivetrainRightMotorPortThree = 6;

  public final int drivetrainSolenoidPortOn = 0; //make sure it is right
  public final int drivetrainSolenoidPortOff = 1;



  //Storage % Shooter Motor ID
  public final int storageMotorPortUpOne = 10;
  public final int storageMotorPortUpTwo = 9;
  public final int storageMotorPortIn = 13;
  public final double storageSpeedUp = 0.75;
  public final double storageSpeedIn = 1;

  //Gyro
  public final double KP_GYRO = 0.008;

  //Intake Motor & solenoids
  public final int intakeMotorOnePort = 11;
  public final int intakeSoleForward = 2;
  public final int intakeSoleReverse = 3;
  public final double intakeSpeed = 0.75;

  //Climber ??
    public final int climberLeftPort = 12;
    public final int climberRightPort = 14;
    public final int climberSoleLeftForward = 4;
    public final int climberSoleLeftReverse = 5;
    public final int climberSoleRightForward = 6;
    public final int climberSoleRightReverse = 7;
    public final double climberSpeed = 0.5;
    

  /*****
   Constructor method
   *****/

  //  public int getLeftMotorPortOne()
  //  {
  //    return this.drivetrainLeftMotorPortOne; 
  //  }
  //  public int getLeftMotorPortTwo()
  //  {
  //    return this.drivetrainLeftMotorPortTwo; 
  //  }
  //  public int getLeftMotorPortThree()
  //  {
  //    return this.drivetrainLeftMotorPortThree; 
  //  }
  //  public int getRigtMotorPortOne()
  //  {
  //    return this.drivetrainRightMotorPortOne; 
  //  }
  //  public int getRightMotorPortTwo()
  //  {
  //    return this.drivetrainLeftMotorPortTwo;  
  //  }
  //  public int getRightMotorPortThree()
  //  {
  //    return this.drivetrainRightMotorPortThree; 
  //  }
  //  public int getJoystickPortOne()
  //  {
  //    return this.JoystickPortOne; 
  //  }
  //  public double getKPGyro()
  // {
  //   return this.KP_GYRO;
  // }
  // public double getIntakeSpeed() {
  //   return this.intakeSpeed;
  // }

  // public int getWinchPort() {
  //   return this.WINCH_PORT;
  // }

  // public double getClimberSpeed() {
  //   return this.climberSpeed;
  // }
}
