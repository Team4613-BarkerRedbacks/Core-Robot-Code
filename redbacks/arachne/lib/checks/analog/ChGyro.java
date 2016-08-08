package redbacks.arachne.lib.checks.analog;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import redbacks.arachne.lib.checks.Check.CheckAnalog;

/**
 * Checks whether a gyro has reached a specific value.
 * 
 * @author Sean Zammit
 */
public class ChGyro extends CheckAnalog
{
	Gyro sensor;
	
	/**
	 * @param value The number of degrees being checked for.
	 * @param sensor The sensor being checked.
	 */
	public ChGyro(double value, Gyro sensor) {
		this(value, sensor, true);
	}
	
	/**
	 * @param value The number of degrees being checked for.
	 * @param sensor The sensor being checked.
	 * @param isGreaterThan Whether the required reading is greater than the input.
	 */
	public ChGyro(double value, Gyro sensor, boolean isGreaterThan) {
		this(value, sensor, isGreaterThan, true);
	}

	/**
	 * @param value The number of degrees being checked for.
	 * @param sensor The sensor being checked.
	 * @param isGreaterThan Whether the required reading is greater than the input.
	 * @param useAbsoluteReading Whether the absolute analog reading should be used.
	 */
	public ChGyro(double value, Gyro sensor, boolean isGreaterThan, boolean useAbsoluteReading) {
		this(value, sensor, isGreaterThan, useAbsoluteReading, true);
	}

	/**
	 * @param value The number of degrees being checked for.
	 * @param sensor The sensor being checked.
	 * @param isGreaterThan Whether the required reading is greater than the input.
	 * @param useAbsoluteReading Whether the absolute analog reading should be used.
	 * @param shouldReset Whether whatever is returning an analog reading should be reset to 0 at the beginning of the check.
	 */
	public ChGyro(double value, Gyro sensor, boolean isGreaterThan, boolean useAbsoluteReading, boolean shouldReset) {
		super(value, isGreaterThan, useAbsoluteReading, shouldReset);
		this.sensor = sensor;
	}
	
	public double getAnalogValue() {
		return sensor.getAngle();
	}
	
	public void onStart() {
		if(this.shouldReset) sensor.reset();
	}
}
