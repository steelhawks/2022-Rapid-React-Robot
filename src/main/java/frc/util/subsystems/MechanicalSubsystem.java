/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public abstract class MechanicalSubsystem extends SubsystemBase {
  /** Stops the subsystem. @return True if stopped */
  abstract public boolean stop();

  /** Pings the subsystem. */
  abstract public void ping();

  /** Checks if the subsystem is functioning properly. @return True if functioning */
  abstract public boolean isAlive();

  /** Print info to Smart Dashboard */
  abstract public void smartDashboard();
}