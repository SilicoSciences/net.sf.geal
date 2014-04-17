package net.sf.geal.terminator;

import net.sf.geal.GeneticAlgorithm;
import net.sf.kerner.utils.progress.ProgressMonitor;

public class TerminatorManual implements TerminatorEvolution, ProgressMonitor {

    private final ProgressMonitor delegate;

    public TerminatorManual(final ProgressMonitor delegate) {
        this.delegate = delegate;
    }

    @Override
    public void finished() {
        delegate.finished();
    }

    @Override
    public boolean isCancelled() {
        return delegate.isCancelled();
    }

    @Override
    public void notifySubtask(final String name) {
        delegate.notifySubtask(name);
    }

    @Override
    public void setCancelled(final boolean cancelled) {
        delegate.setCancelled(cancelled);

    }

    @Override
    public void started(final int totalWorkload) {
        delegate.started(totalWorkload);
    }

    @Override
    public synchronized Boolean visit(final GeneticAlgorithm element) {
        return delegate.isCancelled();
    }

    @Override
    public void worked() {
        delegate.worked();

    }

    @Override
    public void worked(final int i) {
        delegate.worked(i);

    }

}
