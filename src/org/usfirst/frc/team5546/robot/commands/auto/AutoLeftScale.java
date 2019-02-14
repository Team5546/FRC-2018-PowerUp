package org.usfirst.frc.team5546.robot.commands.auto;

import org.usfirst.frc.team5546.robot.commands.driveTrain.DriveFor;
import org.usfirst.frc.team5546.robot.commands.driveTrain.DriveForSquared;
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
public class AutoLeftScale extends CommandGroup {

    public AutoLeftScale() {
    	addParallel(new SuckIn(.3));
    	addParallel(new SwitchUp());
    	addSequential(new DriveFor(1, .2));
    	addSequential(new DriveFor(19.4, .3));
    	addParallel(new ScaleUp());
    	addSequential(new RotateToAngle(45, 0.6));
    	//addSequential(new DriveFor(-.3, .2));
    	//addParallel(new DriveFor(1, .2));
    	addSequential(new WaitCommand(4));
    	addSequential(new SuckOut(1));
    }
}
