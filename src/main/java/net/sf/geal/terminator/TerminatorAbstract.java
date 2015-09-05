package net.sf.geal.terminator;

public abstract class TerminatorAbstract implements TerminatorEvolution {

	@Override
	public synchronized void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	private boolean enabled = true;

	@Override
	public synchronized boolean isEnabled() {
		return enabled;
	}

}
