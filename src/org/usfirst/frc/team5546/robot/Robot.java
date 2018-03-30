/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5546.robot;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team5546.robot.commands.auto.AutoCenterLeft;
import org.usfirst.frc.team5546.robot.commands.auto.AutoCenterRight;
import org.usfirst.frc.team5546.robot.commands.auto.AutoLeftLeft;
import org.usfirst.frc.team5546.robot.commands.auto.AutoLeftNone;
import org.usfirst.frc.team5546.robot.commands.auto.AutoLeftScale;
import org.usfirst.frc.team5546.robot.commands.auto.AutoRightNone;
import org.usfirst.frc.team5546.robot.commands.auto.AutoRightRight;
import org.usfirst.frc.team5546.robot.commands.auto.AutoRightScale;
import org.usfirst.frc.team5546.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5546.robot.subsystems.Elevator;
import org.usfirst.frc.team5546.robot.subsystems.Manipulator;
import org.usfirst.frc.team5546.robot.subsystems.PneumaticCompressor;
import org.usfirst.frc.team5546.robot.subsystems.Winch;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
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
	public static boolean driveTrainInverted = false;

	Command autoCommand;

	public static SendableChooser<String> autoChooser = new SendableChooser<String>();
	public static SendableChooser<String> priorityChooser = new SendableChooser<String>();
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		
		autoChooser.addDefault("Center", "Center");
		autoChooser.addObject("Left", "Left");
		autoChooser.addObject("Right", "Right");
		SmartDashboard.putData("Position", autoChooser);
		
		priorityChooser.addDefault("Switch", "Switch");
		priorityChooser.addObject("Scale", "Scale");
		priorityChooser.addObject("SWITCH ONLY", "SWITCH ONLY");
		priorityChooser.addObject("DRIVE FORWARD", "DRIVE FORWARD");
		priorityChooser.addObject("don't move", "don't move");
		SmartDashboard.putData("Priority", priorityChooser);
		
		elevator.switchDown();
		elevator.scaleDown();
		
		//CameraServer.getInstance().startAutomaticCapture();
		new Thread(() -> {
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			camera.setResolution(640,  480);
			
			CvSink cvSink = CameraServer.getInstance().getVideo();
			CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
			
			Mat source = new Mat();
			Mat output = new Mat();
			
			while(!Thread.interrupted()) {
				cvSink.grabFrame(source);
				Imgproc.cvtColor(source, output, Imgproc.COLOR_BayerBG2BGR);
				outputStream.putFrame(output);
			}
		}).start();
		
		SmartDashboard.putData(new AutoLeftScale());
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
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		String position = autoChooser.getSelected();
		String priority = priorityChooser.getSelected();
		
		// Set auto command based on game data
        if(gameData.length() > 0) {
        	if(priority == "Switch") {
        		if(position == "Left") {
        			if(gameData.charAt(0) == 'L') {
        				autoCommand = new AutoLeftLeft();
        			} else {
        				if(gameData.charAt(1) == 'L') {
        					autoCommand = new AutoLeftScale();
        				} else {
        					autoCommand = new AutoLeftNone();
        				}
        			}
        		} else if(position == "Right") {
        			if(gameData.charAt(0) == 'R') {
        				autoCommand = new AutoRightRight();
        			} else {
        				if(gameData.charAt(1) == 'R') {
        					autoCommand = new AutoRightScale();
        				} else {
        					autoCommand = new AutoRightNone();
        				}
        			}
        		} else {
        			if(gameData.charAt(0) == 'R') {
        				autoCommand = new AutoCenterRight();
        			} else {
        				autoCommand = new AutoCenterLeft();
        			}
        		}
        	} else if(priority == "Scale") {
        		if(position == "Left") {
        			if(gameData.charAt(1) == 'L') {
        				autoCommand = new AutoLeftScale();
        			} else {
        				if(gameData.charAt(0) == 'L') {
        					autoCommand = new AutoLeftLeft();
        				} else {
        					autoCommand = new AutoLeftNone();
        				}
        			}
        		} else if(position == "Right") {
        			if(gameData.charAt(1) == 'R') {
        				autoCommand = new AutoRightScale();
        			} else {
        				if(gameData.charAt(0) == 'R') {
        					autoCommand = new AutoRightRight();
        				} else {
        					autoCommand = new AutoRightNone();
        				}
        			}
        		} else {
        			if(gameData.charAt(0) == 'R') {
        				autoCommand = new AutoCenterRight();
        			} else {
        				autoCommand = new AutoCenterLeft();
        			}
        		}
        	} else if(priority == "SWITCH ONLY") {
        		if(position == "Left") {
        			if(gameData.charAt(0) == 'L') {
        				autoCommand = new AutoLeftLeft();
        			} else {
        				autoCommand = new AutoLeftNone();
        			}
        		} else if(position == "Right") {
        			if(gameData.charAt(0) == 'R') {
        				autoCommand = new AutoRightRight();
        			} else {
        				autoCommand = new AutoRightNone();
        			}
        		} else if(position == "Center") {
        			if(gameData.charAt(0) == 'L') {
        				autoCommand = new AutoCenterLeft();
        			} else {
        				autoCommand = new AutoCenterRight();
        			}
        		}
        	} else {
        		if(position == "Left") {
        			autoCommand = new AutoLeftNone();
        		} else if(position == "Right") {
        			autoCommand = new AutoRightNone();
        		} else if(position == "Center") {
        			if(gameData.charAt(0) == 'L') {
        				autoCommand = new AutoCenterLeft();
        			} else {
        				autoCommand = new AutoCenterRight();
        			}
        		}
        	}
        }
        if(priority != "don't move") autoCommand.start();
        
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
		if (autoCommand != null) {
			autoCommand.cancel();
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
