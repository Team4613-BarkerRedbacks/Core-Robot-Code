package redbacks.arachne.lib.checks;

/**
 * A check which always returns false. However, when used in an action it will be counted as true if there is another action in the sequence.
 * To create a check which is always false, use {@link ChFalse ChFalse}.
 * 
 * @author Sean Zammit
 */
public class ChQueue extends Check
{
	public boolean isDone() {
		return false;
	}
}
