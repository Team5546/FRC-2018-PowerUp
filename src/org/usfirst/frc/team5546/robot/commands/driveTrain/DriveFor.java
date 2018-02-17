package org.usfirst.frc.team5546.robot.commands.driveTrain;

import org.usfirst.frc.team5546.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveFor extends Command {

	boolean finished = false;
	double distance = 0;
	
    public DriveFor(double feet, double speed) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
        this.distance = feet * Robot.driveTrain.DISTANCE_PER_FOOT;
        Robot.driveTrain.speed = speed;
        System.out.println(feet);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.rotate = false;
    	//Robot.driveTrain.encoderLeft.reset();
    	Robot.driveTrain.imu.reset();
    	Robot.driveTrain.getPIDController().setPID(4, 0.1, 0);
    	Robot.driveTrain.setSetpointRelative(distance);
    	System.out.println("Setpoint: " + Robot.driveTrain.getSetpoint());
    	System.out.println("Position: " + Robot.driveTrain.getPosition());
    	System.out.println("Position - Setpoint: " + (Robot.driveTrain.getPosition() - Robot.driveTrain.getSetpoint()));
    	Robot.driveTrain.setAbsoluteTolerance(100);
    	Robot.driveTrain.enable();
    	//System.out.println(Robot.driveTrain.getSetpoint());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.out.println("Executing DriveFor.");
    	//Robot.driveTrain.enable();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//return finished;
        return Robot.driveTrain.onTarget();// || Robot.oi.cancelGearBtn.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Finished DriveFor");
    	Robot.driveTrain.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
//public class DriveFor extends Command {
//
//	boolean finished = false;
//	double distance = 0;
//	
//    public DriveFor(double feet, double speed) {
//        // Use requires() here to declare subsystem dependencies
//        requires(Robot.driveTrain);
//        distance = feet * Robot.driveTrain.DISTANCE_PER_FOOT;
//        System.out.println(feet);
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    	Robot.driveTrain.encoderLeft.reset();
//    	System.out.println("Starting DriveFor.");
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    	Robot.driveTrain.driveArcade(0.5, 0);
//    	System.out.println(distance - Robot.driveTrain.encoderLeft.getDistance());
//    	if(Robot.driveTrain.encoderLeft.getDistance() >= distance) {
//    		System.out.println("Driving.");
//    		System.out.println(distance - Robot.driveTrain.encoderLeft.getDistance());
//    		Robot.driveTrain.driveTank(0, 0);
//    		finished = true;
//    	}
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//    	//return finished;
//    	Robot.driveTrain.driveTank(0, 0);
//        return finished;// || Robot.oi.cancelGearBtn.get();
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    	System.out.println("Finished DriveFor");
//    	//Robot.driveTrain.disable();
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    }
//}
//
