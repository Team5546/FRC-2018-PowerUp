/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5546.robot;

import org.usfirst.frc.team5546.robot.commands.auto.AutoCenterRight;
import org.usfirst.frc.team5546.robot.commands.driveTrain.DriveFor;
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
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	public Joystick leftStick = new Joystick(0);
	public Joystick rightStick = new Joystick(1);
	public Joystick launchpad = new Joystick(2);
	public Joystick xbox = new Joystick(3);
	
	public Button turnRight = new JoystickButton(launchpad, 7);
	public Button driveForward = new JoystickButton(launchpad, 4);
	public Button elevator = new JoystickButton(launchpad, 6);
	public Button winchSafety = new JoystickButton(launchpad, 2);
	
	public Button suckIn = new JoystickButton(rightStick, 1);
	public Button suckOut = new JoystickButton(leftStick, 1);
	
	public Button leftSwitch = new JoystickButton(rightStick, 3);
	public Button rightSwitch = new JoystickButton(rightStick, 4);
	public Button leftScale = new JoystickButton(leftStick, 3);
	public Button rightScale = new JoystickButton(leftStick, 4);
	
	public OI() {
		turnRight.whenReleased(new AutoCenterRight());
		
		//gearGrab.whenReleased(new TestChute());
		driveForward.whenReleased(new DriveFor(1, 1));
		
		elevator.whenPressed(new ScaleUp());
		
		winchSafety.whenPressed(new DisableSafety());
		winchSafety.whenReleased(new EnableSafety());
		
		suckIn.whileActive(new SuckIn(1));
		suckOut.whileActive(new SuckOut(0.8));
		
		leftSwitch.whenPressed(new SwitchUp());
		rightSwitch.whenPressed(new SwitchUp());
		leftSwitch.whenReleased(new SwitchDown());
		rightSwitch.whenReleased(new SwitchDown());
		
		leftScale.whenPressed(new ScaleUp());
		rightScale.whenPressed(new ScaleUp());
		leftScale.whenReleased(new ScaleDown());
		rightScale.whenReleased(new ScaleDown());
	}
}
