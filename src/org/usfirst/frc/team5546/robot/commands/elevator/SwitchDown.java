package org.usfirst.frc.team5546.robot.commands.elevator;

import org.usfirst.frc.team5546.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SwitchDown extends Command {

    public SwitchDown() {
        requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.switchButtonCount--;
    	if(Robot.switchButtonCount == 0) {
    		Robot.elevator.switchDown();
    	}
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