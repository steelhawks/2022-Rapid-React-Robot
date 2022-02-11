package frc.robot;

public class RobotMap 
{

  public RobotMap() {}
  
  /*Store instance variables, values, ports needed for the robot*/

  //Joystick Input Ports

  
  final int JoystickPortOne = 0;

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
  public final int storageMotorPortOne = 13;
  public final int storageMotorPortTwo = 15;
  public final double storageSpeedOne = 1.0;
  public final double storageSpeedTwo = 1.0;

  //Gyro
  public final double KP_GYRO = 0.008;

  //Intake Motor & solenoids
  public final int intakeMotorOnePort = 0;
  public final int intakeSoleLeftForward = 0;
  public final int intakeSoleLeftReverse = 0;
  public final int intakeSoleRightForward = 0;
  public final int intakeSoleRightReverse = 0;
  public final double intakeSpeed = 0.75;




  //Climber ??
    public final int WINCH_PORT = 3;
    public final int PIVOT_PORT = 8;
    public final double climberSpeed = 0.5;
    public final double pivotSpeed = 0.1;

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
