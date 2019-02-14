package org.usfirst.frc.team5546.robot.subsystems;

import org.usfirst.frc.team5546.robot.RobotMap;
import org.usfirst.frc.team5546.robot.commands.manipulator.Run;

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
	
	double deadzone = 0.3;
	double maxspeed = .75;
	
	public Manipulator() {
		left = new VictorSP(RobotMap.MANIPULATOR_LEFT);
		right = new VictorSP(RobotMap.MANIPULATOR_RIGHT);
		
		//left.setInverted(true);
		right.setInverted(true);
		
		drive = new DifferentialDrive(left, right);
		// Define motor controllers and drive train using RobotMap.(insert name here)
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new Run());
    }
    
    public void suckIn(double speed) {
    	drive.tankDrive(speed, -speed);
    	// Drive Reverse
    }
    
    public void suckOut(double speed) {
    	drive.tankDrive(-speed, speed);
    	// Drive Forward
    }
    
    public void run(double leftSpeed, double rightSpeed) {
    	double l, r;
    	if(leftSpeed >= deadzone) {
    		l = (maxspeed/(1-deadzone))*(Math.abs(leftSpeed)-deadzone);
    	} else if(leftSpeed <= -deadzone){
    		l = (maxspeed/(1-deadzone))*(Math.abs(leftSpeed)-deadzone);
    		l *= -1;
    	} else {
    		l = 0;
    	}
    	
    	if(rightSpeed >= deadzone) {
    		r = (maxspeed/(1-deadzone))*(Math.abs(rightSpeed)-deadzone);
    	} else if(rightSpeed <= -deadzone){
    		r = (maxspeed/(1-deadzone))*(Math.abs(rightSpeed)-deadzone);
    		r *= -1;
    	} else {
    		r = 0;
    	}
    	
    	drive.tankDrive(l, r, true);
    }
}

