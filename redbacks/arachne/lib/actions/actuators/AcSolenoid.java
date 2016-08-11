package redbacks.arachne.lib.actions.actuators;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChTime;
import redbacks.arachne.lib.checks.ChTrue;

/**
 * Holds all solenoid actions.
 * 
 * @author Sean Zammit
 */
public class AcSolenoid
{
	/**
	 * Action class used to set the value of a single solenoid.
	 * This command ends immediately, and sets the value of the solenoid on completion.
	 */
	public static class Single extends Action
	{
		private Solenoid solenoid;
		private boolean position;
		
		/**
		 * @param solenoid The single solenoid being set by this command.
		 * @param position The position the solenoid should be set to.
		 */
		public Single(Solenoid solenoid, boolean position) {
			super(new ChTrue());
			this.solenoid = solenoid;
			this.position = position;
		}
		
		public void onFinish() {
			solenoid.set(position);
		}
	}
	
	/**
	 * Action class used to set the value of a double solenoid.
	 * This command takes 0.1 seconds to complete. It sets the value of the solenoid at the beginning of the command, and turns it off at the end.
	 */
	public static class Double extends Action
	{
		private DoubleSolenoid solenoid;
		private Value position;
		
		/**
		 * @param solenoid The double solenoid being set by this command.
		 * @param position The position as a DoubleSolenoid.Value that this solenoid should be set to.
		 */
		public Double(DoubleSolenoid solenoid, Value position) {
			super(new ChTime(0.1D));
			this.solenoid = solenoid;
			this.position = position;
		}
		
		public void onStart() {
			solenoid.set(position);
		}
		
		public void onFinish() {
			solenoid.set(Value.kOff);
		}
	}
}
