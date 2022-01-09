/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util;

public class Waiter {
  private int counter;
  private int refreshTime;
  private boolean isExpired;

  public Waiter(int refreshTime) {
    this.counter = 0;
    this.refreshTime = refreshTime;
  }

  public void increment() {
    this.counter++;
    if(this.counter >= this.refreshTime) {
      this.isExpired = true;
    }
  }

  public boolean getIsExpired() {
    return this.isExpired;
  }

  public void reset() {
    this.counter = 0;
    this.isExpired = false;
  }
}