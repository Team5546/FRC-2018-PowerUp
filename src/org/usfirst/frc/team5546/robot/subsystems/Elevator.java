package org.usfirst.frc.team5546.robot.subsystems;

import org.usfirst.frc.team5546.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// RENAME THESE
	DoubleSolenoid switchSolenoid;
	DoubleSolenoid scaleSolenoid;
	
	public Elevator() {
		switchSolenoid = new DoubleSolenoid(RobotMap.STAGE1_FORWARD,RobotMap.STAGE1_REVERSE);
		scaleSolenoid = new DoubleSolenoid(RobotMap.STAGE2_FORWARD,RobotMap.STAGE2_REVERSE);
	}

    public void initDefaultCommand() {
    }
    
    public void switchUp() {
    	switchSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void scaleUp() {
    	scaleSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void switchDown() {
    	switchSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void scaleDown() {
    	scaleSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
}

