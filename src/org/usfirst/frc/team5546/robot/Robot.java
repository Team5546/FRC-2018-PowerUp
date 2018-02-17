/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5546.robot;

import org.usfirst.frc.team5546.robot.commands.auto.AutoCenterRight;
import org.usfirst.frc.team5546.robot.commands.driveTrain.RotateToAngle;
import org.usfirst.frc.team5546.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5546.robot.subsystems.Elevator;
import org.usfirst.frc.team5546.robot.subsystems.Manipulator;
import org.usfirst.frc.team5546.robot.subsystems.PneumaticCompressor;
import org.usfirst.frc.team5546.robot.subsystems.Winch;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	public static DriveTrain driveTrain = new DriveTrain();
	public static PneumaticCompressor compressor = new PneumaticCompressor();
	public static Elevator elevator = new Elevator();
	public static Winch winch = new Winch();
	public static Manipulator manipulator = new Manipulator();
	public static OI oi;
	
	//AnalogInput ai = new AnalogInput(0);
	
	String gameData;
	
	public static boolean winchSafety = true;
	
	public static int switchButtonCount = 0;
	public static int scaleButtonCount = 0;

	Command m_autonomousCommand;
	public static SendableChooser<String> driveChooser = new SendableChooser<String>();
	
	public static SendableChooser<Command> autoChooser = new SendableChooser<Command>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		
		driveChooser.addDefault("Tank", "Tank");
		driveChooser.addObject("Arcade", "Arcade");
		SmartDashboard.putData("Drive mode", driveChooser);
		
		autoChooser.addDefault("Center", new AutoCenterRight());
		SmartDashboard.putData("Auto", autoChooser);
		
		SmartDashboard.putData(new RotateToAngle(90));
		elevator.switchDown();
		elevator.scaleDown();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		//SmartDashboard.putData(ai);
		SmartDashboard.putNumber("imu", driveTrain.imu.getAngleZ());
		// Set game data
		gameData = DriverStation.getInstance().getGameSpecificMessage();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		
		// Set game data
//		gameData = DriverStation.getInstance().getGameSpecificMessage();
//		System.out.println(gameData);
//		// Set auto command based on game data
//        if(gameData.length() > 0) {
//		  if(gameData.charAt(0) == 'L') {
//			//Put left auto code here
//		  } else {
//			//Put right auto code here
//			  System.out.println("Should run auto right.");
//			  
//			  m_autonomousCommand = new AutoCenterRight();
//		  }
//        }
		
		m_autonomousCommand = autoChooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
        m_autonomousCommand.start();
        
        SmartDashboard.putData("Scheduler", Scheduler.getInstance());
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
