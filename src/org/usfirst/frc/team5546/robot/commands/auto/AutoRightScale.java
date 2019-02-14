package org.usfirst.frc.team5546.robot.commands.auto;

import org.usfirst.frc.team5546.robot.commands.driveTrain.DriveFor;
import org.usfirst.frc.team5546.robot.commands.driveTrain.RotateToAngle;
import org.usfirst.frc.team5546.robot.commands.elevator.ScaleUp;
import org.usfirst.frc.team5546.robot.commands.elevator.SwitchUp;
import org.usfirst.frc.team5546.robot.commands.manipulator.SuckIn;
import org.usfirst.frc.team5546.robot.commands.manipulator.SuckOut;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoRightScale extends CommandGroup {

    public AutoRightScale() {
    	addParallel(new SuckIn(.3));
    	addParallel(new SwitchUp());
    	addSequential(new DriveFor(1, .2));
    	addSequential(new DriveFor(16.5, .3));
    	addParallel(new ScaleUp());
    	addSequential(new RotateToAngle(-45, 0.6));
    	//addSequential(new DriveFor(-.3, .2));
    	addSequential(new WaitCommand(.3));
    	addParallel(new DriveFor(1, .1));
    	addSequential(new WaitCommand(3));
    	addSequential(new SuckOut(1));
    }
}
