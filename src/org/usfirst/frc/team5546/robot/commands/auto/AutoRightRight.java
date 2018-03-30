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
public class AutoRightRight extends CommandGroup {

    public AutoRightRight() {
        //addParallel(new SuckIn(.3));
        addParallel(new SwitchUp());
        addSequential(new DriveFor(11.5, .25));
        addSequential(new RotateToAngle(-89, .6));
        addParallel(new DriveFor(2.5, .2));
        addSequential(new WaitCommand(1));
        addSequential(new SuckOut(0.7));
    }
}
