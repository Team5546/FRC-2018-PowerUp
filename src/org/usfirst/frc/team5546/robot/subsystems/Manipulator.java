package org.usfirst.frc.team5546.robot.subsystems;

import org.usfirst.frc.team5546.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class Manipulator extends Subsystem {

    // Declare motor controllers and drive train
	VictorSP left, right;
	DifferentialDrive drive;
	
	public Manipulator() {
		left = new VictorSP(RobotMap.MANIPULATOR_LEFT);
		right = new VictorSP(RobotMap.MANIPULATOR_RIGHT);
		
		drive = new DifferentialDrive(left, right);
		// Define motor controllers and drive train using RobotMap.(insert name here)
	}

    public void initDefaultCommand() {
    	
    }
    
    public void suckIn(double speed) {
    	drive.tankDrive(-speed, -speed);
    	// Drive Reverse
    }
    
    public void suckOut(double speed) {
    	drive.tankDrive(speed, speed);
    	// Drive Forward
    }
}

