package org.usfirst.frc.team5546.robot.commands.auto;

import org.usfirst.frc.team5546.robot.commands.driveTrain.DriveFor;
import org.usfirst.frc.team5546.robot.commands.driveTrain.RotateToAngle;
import org.usfirst.frc.team5546.robot.commands.elevator.SwitchUp;
import org.usfirst.frc.team5546.robot.commands.manipulator.SuckOut;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoCenterRight extends CommandGroup {

    public AutoCenterRight() {
    	//addParallel(new SuckIn(.3));
    	addParallel(new SwitchUp());
    	addSequential(new DriveFor(4, .2));
    	addSequential(new RotateToAngle(91, 0.6));
    	addSequential(new DriveFor(3.9, .2));
    	addSequential(new RotateToAngle(-95, 0.6));
    	addParallel(new DriveFor(4.2, .2));
    	addSequential(new WaitCommand(2.5));
    	addSequential(new SuckOut(0.8));
    }
}
