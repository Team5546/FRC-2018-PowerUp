package org.usfirst.frc.team5546.robot.commands.auto;

import org.usfirst.frc.team5546.robot.commands.driveTrain.DriveFor;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftNone extends CommandGroup {

    public AutoLeftNone() {
        addSequential(new DriveFor(12, .25));
    }
}
