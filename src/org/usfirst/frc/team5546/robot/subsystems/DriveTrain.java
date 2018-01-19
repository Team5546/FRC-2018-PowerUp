package org.usfirst.frc.team5546.robot.subsystems;

import org.usfirst.frc.team5546.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveTrain extends Subsystem {

    VictorSP leftFront, leftBack, rightFront, rightBack;
    
    SpeedControllerGroup left, right;
    
    DifferentialDrive drive;
	
	public DriveTrain() {
		leftFront = new VictorSP(RobotMap.LEFT_FRONT_MOTOR);
		leftBack = new VictorSP(RobotMap.LEFT_BACK_MOTOR);
		rightFront = new VictorSP(RobotMap.RIGHT_FRONT_MOTOR);
		rightBack = new VictorSP(RobotMap.RIGHT_BACK_MOTOR);
		
		left = new SpeedControllerGroup(leftFront, leftBack);
		right = new SpeedControllerGroup(rightFront, rightBack);
		
		drive = new DifferentialDrive(left, right);
	}
	
	public void driveTank(double leftSpeed, double rightSpeed) {
		drive.tankDrive(leftSpeed, rightSpeed);
	}
	
	public void driveArcade(double moveValue, double rotateValue) {
		drive.arcadeDrive(moveValue, rotateValue);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

