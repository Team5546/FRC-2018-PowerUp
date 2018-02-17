package org.usfirst.frc.team5546.robot.commands.compressor;

import org.usfirst.frc.team5546.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopCompressor extends Command {

    public StopCompressor() {
        requires(Robot.compressor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.compressor.setEnable(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}