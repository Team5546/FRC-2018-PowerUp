package org.usfirst.frc.team5546.robot.commands.driveTrain;

import org.usfirst.frc.team5546.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateToAngle extends Command {
	
	double setpoint = 0;
	double offset = 3.0;
	
	double speed;
	
    public RotateToAngle(double setpoint, double s) {
    	this.setpoint = setpoint;
        requires(Robot.driveTrain);
        speed = s;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.enable();
    	Robot.driveTrain.imu.reset();
    	Robot.driveTrain.rotate = true;
    	Robot.driveTrain.spd = speed;
    	Robot.driveTrain.getPIDController().setPID(0.10, 0.1, 0.3);
    	Robot.driveTrain.setAbsoluteTolerance(offset);
    	Robot.driveTrain.setSetpointRelative(setpoint);
    	System.out.println(setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//SmartDashboard.putNumber("setpoint", Robot.driveTrain.getSetpoint());
    	//SmartDashboard.putNumber("imu", Robot.driveTrain.imu.getAngleZ());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveTrain.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Finished Rotate");
    	Robot.driveTrain.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.disable();
    }
//	Boolean finished = false;
//	
//	double angle;
//	double beginAngle;
//	
//	int targetAngle;
//	int direction;
//	int angle_int;
//	int angle_int_relative;
//	
//    public RotateToAngle(int _targetAngle) {
//        // Use requires() here to declare subsystem dependencies
//        // eg. requires(chassis);
//    	requires(Robot.driveTrain);
//    	targetAngle = _targetAngle;
//    	beginAngle = Robot.driveTrain.imu.getAngleZ();
//    	//Robot.imu.reset();
//    	finished = false;
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    	beginAngle = Robot.driveTrain.imu.getAngleZ();
//    	System.out.println(targetAngle);
//    	targetAngle += (int) Robot.driveTrain.imu.getAngleZ();
//    	System.out.println(targetAngle);
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    	angle = Robot.driveTrain.imu.getAngleZ();
//    	angle_int = (int) angle;
//    	angle_int = angle_int % 360;
//    	//System.out.println(angle_int);
//    	if(angle_int < targetAngle) {
//    		direction = 1;
//    	} else {
//    		direction = -1;
//    	}
//    	Robot.driveTrain.driveTank(direction*0.5, direction*-0.5);
//    	//System.out.println(targetAngle - angle_int);
//    	if(direction == 1 && angle_int >= targetAngle) {
//    		//System.out.println("done right.");
//    		Robot.driveTrain.driveTank(0, 0);
//    		finished = true;
//    	} else if(direction == -1 && angle_int <= targetAngle) {
//    		Robot.driveTrain.driveTank(0, 0);
//    		finished = true;
//    	}
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//    	beginAngle = 0;
//    	//Robot.imu.reset();
//    	//Robot.driveTrain.stop();
//        return finished;
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    	System.out.println("RotateToAngle Finished.");
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    }
}
