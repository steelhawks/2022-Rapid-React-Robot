/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Storage;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;
import frc.robot.subsystems.StorageTest;

public class StorageRollUp extends CommandBase {

  private final StorageTest m_intakeTest;

  public StorageRollUp(StorageTest subsystem) {
    m_intakeTest = subsystem;
    addRequirements(m_intakeTest);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    Robot.INTAKETEST.rollUp(true);
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      Robot.INTAKETEST.stopUp();
    }
  }
}