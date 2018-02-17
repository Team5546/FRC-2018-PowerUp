package org.usfirst.frc.team5546.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PneumaticCompressor extends Subsystem {

    Compressor compressor;
    
    public PneumaticCompressor() {
    	compressor = new Compressor(0);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setEnable(boolean enable) {
    	if(enable) compressor.start();
    	else compressor.stop();
    }
}

