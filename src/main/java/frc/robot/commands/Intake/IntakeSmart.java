package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class IntakeSmart extends CommandBase {

    

    public IntakeSmart() {
        addRequirements(Robot.STORAGE);
    }

    @Override
    public void initialize() {
        
    }
    
    @Override
    public void execute() {
        /*

        TWO CONDITIONS FOR IF BALL IS IN STORAGE OR NOT: STARTS OFF EMPTY

        if (!Robot.ballInStorage) {
            

        } else {


        }

        if(!Robot.STORAGE.beamI.get()){ //if ball in Intake Beams
    
            Robot.INTAKE.spinRoller(false);
            Robot.STORAGE.storageIn(true);  
    
            if(!Robot.ballInStorage) {
                Robot.STORAGE.storageRunSlow(false);
            }
        }
        
        else { //if ball NOT in intake beams
            
            Robot.ballInStorage = true;
    
            Robot.STORAGE.STORAGE_MOTOR_UP_ONE.set(0);
            Robot.STORAGE.STORAGE_MOTOR_UP_TWO.set(0);
                       
            Robot.INTAKE.spinRoller(false);
            Robot.STORAGE.storageIn(true);  
        }


        */

        if(!Robot.STORAGE.beamI.get()){ //if ball in Intake Beams

            Robot.INTAKE.spinRoller(false);
            Robot.STORAGE.storageIn(true); 
            
          

            if(!Robot.ballInStorage) {
                Robot.STORAGE.storageRunSlow(false);
                if(!Robot.ballInStorage) {
                    Robot.ballInStorage = true;
                }
                


                // if(!Robot.STORAGE.beamS.get()){
                //     Robot.STORAGE.STORAGE_MOTOR_UP_ONE.set(0);
                //     Robot.STORAGE.STORAGE_MOTOR_UP_TWO.set(0);
                // }
                // else{
                // }
            }
        }
        
        else { //if ball NOT in intake beams
            
            

            Robot.STORAGE.STORAGE_MOTOR_UP_ONE.set(0);
            Robot.STORAGE.STORAGE_MOTOR_UP_TWO.set(0);
                       
            Robot.INTAKE.spinRoller(false);
            Robot.STORAGE.storageIn(true); 

            // if(!Robot.STORAGE.beamI.get()) {
            //     Robot.STORAGE.STORAGE_MOTOR_IN.set(0);
            // }
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        Robot.INTAKE.stopRoll();
        Robot.STORAGE.storageMotorStop();
    }
}