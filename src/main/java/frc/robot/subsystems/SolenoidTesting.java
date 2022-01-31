package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.MotorSafety;

public class SolenoidTesting extends SubsystemBase {

    private final PneumaticsModuleType type = PneumaticsModuleType.CTREPCM;

    public DoubleSolenoid solenoidone;
    public DoubleSolenoid solenoidtwo;

    public SolenoidTesting(){
        this.solenoidone = new DoubleSolenoid(type, 0, 1);
        this.solenoidtwo = new DoubleSolenoid(type, 2, 3);
    }

    public void toggleSolenoid() {
        if (this.solenoidone.get().equals(DoubleSolenoid.Value.kForward)) {
            extendSolenoid();
        } else {
            retractSolenoid();
        }
      }
    
      public void extendSolenoid() {
        this.solenoidone.set(DoubleSolenoid.Value.kForward);
        this.solenoidtwo.set(DoubleSolenoid.Value.kForward);
      }
    
      public void retractSolenoid() {
        this.solenoidone.set(DoubleSolenoid.Value.kReverse);
        this.solenoidtwo.set(DoubleSolenoid.Value.kReverse);
      }

}
