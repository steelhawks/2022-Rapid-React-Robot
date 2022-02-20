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

  public final int drivetrainSolenoidLeftPortOn = 0; //make sure it is right
  public final int drivetrainSolenoidLeftPortOff = 1;
  public final int drivetrainSolenoidRightPortOn = 2;
  public final int drivetrainSolenoidRightPortOff = 3;


  //Storage % Shooter Motor ID
  public final int storageMotorPortUp = 7;
  public final int storageMotorPortIn = 13;
  public final double storageSpeedUp = 0.75;
  public final double storageSpeedIn = 1;

  //Gyro
  public final double KP_GYRO = 0.008;

  //Intake Motor & solenoids
  public final int intakeMotorOnePort = 11;
  public final int intakeSoleLeftForward = 0;
  public final int intakeSoleLeftReverse = 1;
  public final int intakeSoleRightForward = 2;
  public final int intakeSoleRightReverse = 3;
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
