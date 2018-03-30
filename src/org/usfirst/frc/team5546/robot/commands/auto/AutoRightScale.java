package org.usfirst.frc.team5546.robot.commands.auto;

import org.usfirst.frc.team5546.robot.commands.driveTrain.DriveFor;
import org.usfirst.frc.team5546.robot.commands.driveTrain.RotateToAngle;
import org.usfirst.frc.team5546.robot.commands.elevator.ScaleUp;
import org.usfirst.frc.team5546.robot.commands.elevator.SwitchUp;
import org.usfirst.frc.team5546.robot.commands.manipulator.SuckOut;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoRightScale extends CommandGroup {

	double speed = 0.25;
	double lowSpeed = speed - 0.05;
    public AutoRightScale() {
    	//addParallel(new SuckIn(.3));
    	addParallel(new SwitchUp());
    	addSequential(new DriveFor(1, lowSpeed));
    	addSequential(new DriveFor(19.5, speed));
    	addParallel(new ScaleUp());
    	addSequential(new DriveFor(1, lowSpeed));
    	addSequential(new RotateToAngle(-45, 0.6));
    	//addSequential(new WaitCommand(.3));
    	//addParallel(new DriveFor(1, speed));
    	addSequential(new WaitCommand(3));
    	addSequential(new SuckOut(1));
    }
}
