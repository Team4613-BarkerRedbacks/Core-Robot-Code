package redbacks.arachne.lib.actions;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import redbacks.arachne.core.ArachneRobot;
import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.checks.ChTrue;
import redbacks.arachne.lib.commands.CommandBase;

/**
 * Holds all actions that interrupt other functions.
 * 
 * @author Sean Zammit
 */
public class AcInterrupt
{
	/**
	 * Cancels the operation of a single action in a specified command.
	 * 
	 * @author Sean Zammit
	 */
	public static class KillAction extends Action
	{
		CommandBase target;

		/**
		 * Constructor for an action which interrupts the current action in a specified command.
		 * 
		 * @param commandToProgress The command which will be manually progressed to the next action.
		 */
		public KillAction(CommandBase commandToProgress) {
			super(new ChTrue());
			target = commandToProgress;
		}

		public void onFinish() {
			target.progressActionSequence();
		}
	}

	/**
	 * Cancels the operation of a specified command.
	 * 
	 * @author Sean Zammit
	 */
	public static class KillCommand extends Action
	{
		CommandBase target;

		/**
		 * Constructor for an action which interrupts a specified command.
		 * 
		 * @param commandToKill The command which will be cancelled.
		 */
		public KillCommand(CommandBase commandToKill) {
			super(new ChTrue());
			target = commandToKill;
		}

		public void onFinish() {
			target.cancel();
		}
	}

	/**
	 * Cancels the operation of all running commands.
	 * 
	 * @author Sean Zammit
	 */
	public static class KillAllCommands extends Action
	{
		/**
		 * Constructor for an action which interrupts all commands.
		 */
		public KillAllCommands() {
			super(new ChTrue());
		}

		public void onFinish() {
			Scheduler.getInstance().removeAll();
		}
	}

	/**
	 * Cancels the operation of whatever command is running on a specified subsystem.
	 * 
	 * @author Sean Zammit
	 */
	public static class KillSubsystem extends Action
	{
		SubsystemBase target;

		/**
		 * Constructor for an action which interrupts the current command in a specified subsystem.
		 * 
		 * @param subsystemToReset The subsystem to cancel the current command on.
		 */
		public KillSubsystem(SubsystemBase subsystemToReset) {
			super(new ChTrue());
			target = subsystemToReset;
		}

		public void onFinish() {
			if(target.getCurrentCommand() != null) target.getCurrentCommand().cancel();
		}
	}

	/**
	 * Cancels the operation of all commands that have a dedicated subsystem.
	 * 
	 * @author Sean Zammit
	 */
	public static class KillAllSubsystems extends Action
	{
		/**
		 * Constructor for an action which interrupts all subsystems.
		 */
		public KillAllSubsystems() {
			super(new ChTrue());
		}

		public void onFinish() {
			for(Subsystem subsystem : ArachneRobot.subsystemList)
				if(subsystem.getCurrentCommand() != null) subsystem.getCurrentCommand().cancel();
		}
	}
}
