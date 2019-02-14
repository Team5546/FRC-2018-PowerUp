/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5546.robot;

import org.usfirst.frc.team5546.robot.commands.auto.AutoCenterRight;
import org.usfirst.frc.team5546.robot.commands.driveTrain.DriveFor;
import org.usfirst.frc.team5546.robot.commands.driveTrain.SetBackwards;
import org.usfirst.frc.team5546.robot.commands.driveTrain.SetForwards;
import org.usfirst.frc.team5546.robot.commands.elevator.ScaleDown;
import org.usfirst.frc.team5546.robot.commands.elevator.ScaleUp;
import org.usfirst.frc.team5546.robot.commands.elevator.SwitchDown;
import org.usfirst.frc.team5546.robot.commands.elevator.SwitchUp;
import org.usfirst.frc.team5546.robot.commands.manipulator.SuckIn;
import org.usfirst.frc.team5546.robot.commands.manipulator.SuckOut;
import org.usfirst.frc.team5546.robot.commands.winch.DisableSafety;
import org.usfirst.frc.team5546.robot.commands.winch.EnableSafety;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick leftStick = new Joystick(0);
	public Joystick rightStick = new Joystick(1);
	public Joystick launchpad = new Joystick(2);
	public Joystick xbox = new Joystick(3);
	
	public Button turnRight = new JoystickButton(launchpad, 7);
	public Button driveForward = new JoystickButton(launchpad, 4);
	public Button elevator = new JoystickButton(launchpad, 6);
	
	public Button winchSafety = new JoystickButton(xbox, 5);
	
	public Button switchToggle = new JoystickButton(rightStick, 1);
	public Button scaleToggle = new JoystickButton(leftStick, 1);
	
	public Button leftOut = new JoystickButton(leftStick, 3);
	public Button rightOut = new JoystickButton(leftStick, 4);
	
	public Button leftIn = new JoystickButton(rightStick, 3);
	public Button rightIn = new JoystickButton(rightStick, 4);
	
	public Button driveForwards = new JoystickButton(leftStick, 2);
	public Button driveBackwards = new JoystickButton(rightStick, 2);
	
	public OI() {
		turnRight.whenReleased(new AutoCenterRight());
		
		//gearGrab.whenReleased(new TestChute());
		driveForward.whenReleased(new DriveFor(1, 1));
		
		elevator.whenPressed(new ScaleUp());
		
		winchSafety.whenPressed(new DisableSafety());
		winchSafety.whenReleased(new EnableSafety());
		
		switchToggle.whenPressed(new SwitchUp());;
		scaleToggle.whenPressed(new ScaleUp());
		switchToggle.whenReleased(new SwitchDown());
		scaleToggle.whenReleased(new ScaleDown());
	
		leftOut.whileActive(new SuckOut(1));
		rightOut.whileActive(new SuckOut(1));
		leftIn.whileActive(new SuckIn(.8));
		rightIn.whileActive(new SuckIn(.8));
		
		driveForwards.whenPressed(new SetForwards());
		driveBackwards.whenPressed(new SetBackwards());
	}
}
