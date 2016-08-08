package redbacks.arachne.lib.motors;

import redbacks.arachne.core.CommandBase;
import edu.wpi.first.wpilibj.CANTalon;

/**
 * A replacement motor controller to enable automatic stopping of motors by commands when they finish.
 * 
 * @author Sean Zammit
 */
public class CtrlMotorList extends CtrlMotor
{
	/** The motor controller held inside this class. */
	public final CANTalon[] controllers;
	
	/**
	 * @param motor The motor controller held inside this class. This is the one that is redirected to whenever a method in this class is used.
	 */
	public CtrlMotorList(CANTalon... motors) {
		super(null);
		controllers = motors;
	}
	
	/**
	 * Used to set the value of the motor. Also tells the motor which command last set its speed.
	 * 
	 * @param outputValue The speed of the motor.
	 * @param command The command that last set the speed of the motor.
	 */
	public void set(double outputValue, CommandBase command) {
		for(CANTalon m : controllers) m.set(outputValue);
		lastCommand = command;
		speed = outputValue;
		if(!command.motorList.contains(this)) command.motorList.add(this);
	}
	
	public void set(double outputValue) {
		for(CANTalon m : controllers) m.set(outputValue);
		speed = outputValue;
	}
	
	/**
	 * Used to disable the motor when the command finishes.
	 */
	public void disable() {
		for(CANTalon m : controllers) m.set(0);
		speed = 0;
	}
	
	/**
	 * Used to set the motor to not be stopped when the command that set it ends.
	 * 
	 * @return This motor. Reason being that it allows this method to be used in the same line as the constructor.
	 */
	public CtrlMotorList setUncancellable() {
		shouldCancel = false;
		return this;		
	}
}
