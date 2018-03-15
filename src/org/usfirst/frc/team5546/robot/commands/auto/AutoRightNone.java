package org.usfirst.frc.team5546.robot.commands.auto;

import org.usfirst.frc.team5546.robot.commands.driveTrain.DriveFor;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightNone extends CommandGroup {

    public AutoRightNone() {
        addSequential(new DriveFor(12, .25));
    }
}
