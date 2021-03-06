package phoenixTeam.event;

/**
 * @author Strikingwolf
 */
public class CancellableBase implements Cancellable {
	protected boolean cancelled = false;

	@Override
	public boolean cancelled() {
		return this.cancelled;
	}

	@Override
	public boolean cancel() {
		this.cancelled = true;
		return true;
	}
}
