package org.usfirst.frc.team5546.robot.commands.auto;

import org.usfirst.frc.team5546.robot.commands.driveTrain.DriveFor;
import org.usfirst.frc.team5546.robot.commands.driveTrain.RotateToAngle;
import org.usfirst.frc.team5546.robot.commands.elevator.SwitchDown;
import org.usfirst.frc.team5546.robot.commands.elevator.SwitchUp;
import org.usfirst.frc.team5546.robot.commands.manipulator.SuckIn;
import org.usfirst.frc.team5546.robot.commands.manipulator.SuckOut;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoCenterLeft extends CommandGroup {

    public AutoCenterLeft() {
    	addParallel(new SuckIn(.1));
    	addParallel(new SwitchUp());
    	addSequential(new DriveFor(4, .2));
    	addSequential(new RotateToAngle(-91, 0.6));
    	addSequential(new DriveFor(4.3, .2));
    	addSequential(new RotateToAngle(91, 0.6));
    	addParallel(new DriveFor(4.2, .2));
    	addSequential(new WaitCommand(2.5));
    	addSequential(new SuckOut(0.8));
    }
}
