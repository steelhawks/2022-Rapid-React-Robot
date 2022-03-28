package frc.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class RobotMap 
{
  public RobotMap() {}
  
  /*Store instance variables, values, ports needed for the robot*/


  public final String pathName = "deploy";
  public final String deployDirectory = "C:/Code/2020AutonBot/deploy";
  public SequentialCommandGroup autonCommands = new SequentialCommandGroup();
  public ArrayList<String> paths = new ArrayList<String>();

  //Joystick Input Ports

  final int joystickPortOne = 0;

  //DRIVETRAIN Motor ID
  public final int drivetrainLeftMotorPortOne = 1;
  public final int drivetrainLeftMotorPortTwo = 2;
  public final int drivetrainLeftMotorPortThree = 3;
  
  public final int drivetrainRightMotorPortOne = 4;
  public final int drivetrainRightMotorPortTwo = 5;
  public final int drivetrainRightMotorPortThree = 6;

  public final int drivetrainSolenoidPortOn = 1; //make sure it is right
  public final int drivetrainSolenoidPortOff = 0;
 
  //Storage % Shooter Motor ID
  public final int storageMotorPortTop = 21; //shooter
  public final int storageMotorPortBot = 12; //shooter
  public final int storageMotorPortIn = 11; //sushi rollers


  //BEAMS
  public final int beamBreakerPortIntake = 1;
  public final int beamBreakerPortStorage = 3;


  //60 percent 2.25 inch green compliant wheels
  public final double storageSpeedUpSlow = 0.47; //shooting speed original .55
  public final double storageSpeedUpFast = .55; // 1 top speed

  public final double storageSpeedIn = 1; //sushi roller speed

  //Gyro
  public final double KP_GYRO = 0.008;

  //Intake Motor & solenoids
  public final int intakeMotorOnePort = 7;
  public final int intakeSoleForward = 3;
  public final int intakeSoleReverse = 2;
  public final double intakeSpeed = 0.65;

  //Climber ??
    public final int climberLeftPort = 25;
    public final int climberRightPort = 10;
    public final int climberSoleLeftForward = 5;
    public final int climberSoleLeftReverse = 4;
    public final int climberSoleRightForward = 7;
    public final int climberSoleRightReverse = 6;
    public final double climberSpeed = 1;
    public final double climberSpeedSlow = .35; //0.65 is 189
    public final double climbRobotSpeed = 0.3;

    //Vision
    public final int LimelightMotorPort = 7;
    public final int beamBreakerPortOne = 1;
    public final int beamBreakerPortTwo = 4;

    public final int leftLimit = 8;
    public final int rightLimit = 7;
    public final int isClimbForwardLimit = 6;
    

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
