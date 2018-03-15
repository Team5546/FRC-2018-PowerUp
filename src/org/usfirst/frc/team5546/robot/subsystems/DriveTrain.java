package org.usfirst.frc.team5546.robot.subsystems;

import org.usfirst.frc.team5546.robot.ADIS16448_IMU;
import org.usfirst.frc.team5546.robot.Robot;
import org.usfirst.frc.team5546.robot.RobotMap;
import org.usfirst.frc.team5546.robot.commands.driveTrain.Drive;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveTrain extends PIDSubsystem {
	
	public ADIS16448_IMU imu;
	public Encoder encoderLeft;
	public AnalogInput ultrasonic;
	
	public final double DISTANCE_PER_FOOT = 1287;
	
	public boolean rotate = false;
	boolean inverted;
	public boolean squaredInputs = false;

    VictorSP leftFront, leftBack, rightFront, rightBack;
    
    SpeedControllerGroup left, right;
    
    DifferentialDrive drive;
    
	public double spd;
	public double distanceToPeg = 0;
	public double centeringDirection = 1;
	public double centerOffset = 0;
	public double distanceBack = 0;
	
	public DriveTrain() {
		super(4, 0.1, 0);
    	setAbsoluteTolerance(20);
		leftFront = new VictorSP(RobotMap.LEFT_FRONT_MOTOR);
		leftBack = new VictorSP(RobotMap.LEFT_BACK_MOTOR);
		rightFront = new VictorSP(RobotMap.RIGHT_FRONT_MOTOR);
		rightBack = new VictorSP(RobotMap.RIGHT_BACK_MOTOR);
		
		left = new SpeedControllerGroup(leftFront, leftBack);
		right = new SpeedControllerGroup(rightFront, rightBack);
		
		drive = new DifferentialDrive(left, right);
		
		imu = new ADIS16448_IMU(); 
		encoderLeft = new Encoder(0, 1);
    	ultrasonic = new AnalogInput(0);
    	
    	spd = 0;
	}
	
	public void driveTank(double leftSpeed, double rightSpeed) {
		setInverted();
		double l, r;
		if(inverted) {
			l = -rightSpeed;
			r = -leftSpeed;
		} else {
			l = leftSpeed;
			r = rightSpeed;
		}
		drive.tankDrive(l, r);
	}
	
	public void driveTankSquared(double leftSpeed, double rightSpeed) {
		setInverted();
		double l, r;
		if(inverted) {
			l = -rightSpeed;
			r = -leftSpeed;
		} else {
			l = leftSpeed;
			r = rightSpeed;
		}
		drive.tankDrive(l, r, true);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new Drive());
    }
    
    public void setInverted() {
    	inverted = Robot.driveTrainInverted;
    }
    
    protected double returnPIDInput() {
        if (rotate) {
        	return imu.getAngleZ();
        } else {
        	return encoderLeft.getDistance();
        }
    }

    protected void usePIDOutput(double output) {
    	if (rotate) {
    		drive.tankDrive(output * spd, -output * spd);
    	} else if(squaredInputs) {
    		drive.tankDrive(output * spd * 2.5, output * spd * 2.5, true);
    	} else {
    		drive.tankDrive(output * spd * 2.5, output * spd * 2.5);
    	}
    }
}

