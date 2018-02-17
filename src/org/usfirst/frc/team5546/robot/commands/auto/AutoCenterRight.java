package org.usfirst.frc.team5546.robot.commands.auto;

import org.usfirst.frc.team5546.robot.commands.driveTrain.DriveFor;
import org.usfirst.frc.team5546.robot.commands.driveTrain.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoCenterRight extends CommandGroup {

    public AutoCenterRight() {
    	addSequential(new DriveFor(3, .2));
    	addSequential(new RotateToAngle(90));
    	addSequential(new DriveFor(3, .2));
    	addSequential(new RotateToAngle(-90));
    	addSequential(new DriveFor(5.92, .2));
    	System.out.println("Created Auto.");
    	//Change to elevator code
    }
}
