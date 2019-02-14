package org.usfirst.frc.team5546.robot.subsystems;

import org.usfirst.frc.team5546.robot.Robot;
import org.usfirst.frc.team5546.robot.RobotMap;
import org.usfirst.frc.team5546.robot.commands.winch.Lift;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class Winch extends Subsystem {

	VictorSP left;
	VictorSP right;
	DifferentialDrive winch;
	
	public Winch() {
		left = new VictorSP(RobotMap.WINCH_LEFT);
		right = new VictorSP(RobotMap.WINCH_RIGHT);
		
		winch = new DifferentialDrive(left, right);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new Lift());
    }
    
    public void run(double speed) {
    	//System.out.println(Robot.winchSafety);
    	if(!Robot.winchSafety) {
    		winch.tankDrive(speed, speed, true);
    	}
    }
    
    public void setSafety(boolean s) {
    	Robot.winchSafety = s;
    }
}

