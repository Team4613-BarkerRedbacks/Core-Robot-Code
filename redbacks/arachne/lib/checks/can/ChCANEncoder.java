package redbacks.arachne.lib.checks.can;

import redbacks.arachne.lib.checks.Check.CheckAnalog;
import redbacks.arachne.lib.sensors.CANEncoder;

/**
 * Checks whether an encoder wired into a CAN controller has reached a specific value.
 * 
 * @author Sean Zammit
 */
public class ChCANEncoder extends CheckAnalog
{
	CANEncoder sensor;
	
	/**
	 * @param value The number of encoder pulses being checked for.
	 * @param sensor The sensor being checked.
	 */
	public ChCANEncoder(double value, CANEncoder sensor) {
		this(value, sensor, true);
	}
	
	/**
	 * @param value The number of encoder pulses being checked for.
	 * @param sensor The sensor being checked.
	 * @param isGreaterThan Whether the required reading is greater than the input.
	 */
	public ChCANEncoder(double value, CANEncoder sensor, boolean isGreaterThan) {
		this(value, sensor, isGreaterThan, true);
	}

	/**
	 * @param value The number of encoder pulses being checked for.
	 * @param sensor The sensor being checked.
	 * @param isGreaterThan Whether the required reading is greater than the input.
	 * @param useAbsoluteReading Whether the absolute analog reading should be used.
	 */
	public ChCANEncoder(double value, CANEncoder sensor, boolean isGreaterThan, boolean useAbsoluteReading) {
		this(value, sensor, isGreaterThan, useAbsoluteReading, true);
	}

	/**
	 * @param value The number of encoder pulses being checked for.
	 * @param sensor The sensor being checked.
	 * @param isGreaterThan Whether the required reading is greater than the input.
	 * @param useAbsoluteReading Whether the absolute analog reading should be used.
	 * @param shouldReset Whether whatever is returning an analog reading should be reset to 0 at the beginning of the check.
	 */
	public ChCANEncoder(double value, CANEncoder sensor, boolean isGreaterThan, boolean useAbsoluteReading, boolean shouldReset) {
		super(value, isGreaterThan, useAbsoluteReading, shouldReset);
		this.sensor = sensor;
	}
	
	public double getAnalogValue() {
		return sensor.get();
	}
	
	public void onStart() {
		if(this.shouldReset) sensor.reset();
	}
}