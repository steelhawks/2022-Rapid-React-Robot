/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Pathcorder;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

public class EndRecording extends CommandBase {
  public EndRecording() {
    addRequirements(Robot.DRIVETRAIN);
  }

  @Override
  public void initialize() {
    System.out.println("Ending recording...");
    Robot.RECORDER.isRecording = false;
  }
  @Override
  public void execute() {}

  @Override
  public boolean isFinished() 
  {
    return true;
  }

  @Override
  public void end(boolean interrupted) 
  {
    Robot.RECORDER.exportPath(Robot.ROBOTMAP.pathName);    
  }
}


