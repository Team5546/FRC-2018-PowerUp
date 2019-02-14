package org.usfirst.frc.team5546.robot.commands.driveTrain;

import org.usfirst.frc.team5546.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForSquared extends Command {
	boolean finished = false;
	double distance = 0;
	double speed;
	
    public DriveForSquared(double feet, double _speed) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
        this.distance = feet * Robot.driveTrain.DISTANCE_PER_FOOT;
        speed = _speed;
        System.out.println(feet);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.rotate = false;
    	Robot.driveTrain.squaredInputs = true;
    	Robot.driveTrain.encoderLeft.reset();
    	Robot.driveTrain.imu.reset();
    	Robot.driveTrain.getPIDController().setPID(4, 0.1, 0);
    	Robot.driveTrain.setSetpointRelative(distance);
    	Robot.driveTrain.spd = speed;
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
