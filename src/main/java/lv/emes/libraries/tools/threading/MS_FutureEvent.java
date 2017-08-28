package lv.emes.libraries.tools.threading;

/**
 * A thread event that is going to happen after some short time measured in milliseconds.
 * Most common use cases are user actions or operation with GUI that has to be done in some
 * known time.
 * <p>Class is made so that it could be easily used like builder and there is no need to create
 * local variable for that.
 * Just create new instance, use setters to configure time till execution and action on execution!
 * Optionally actions on exceptions can be set to define, what will happen if exception occurs
 * during event execution.
 * <p>Public methods:
 * <ul>
 * <li>schedule</li>
 * </ul>
 * <p>Setters:
 * <ul>
 * <li>withTimeTillExecution</li>
 * <li>withTimeout</li>
 * <li>withAction</li>
 * <li>withActionOnInterruptedException</li>
 * <li>withActionOnGeneralException</li>
 * </ul>
 *
 * @author eMeS
 * @version 1.0.
 */
public class MS_FutureEvent {

    private WorkerThread worker = new WorkerThread().withThreadName("MS_FutureEvent");

    /**
     * Sets time till execution (default is 0 meaning that event will execute instantly).
     *
     * @param milliseconds non-negative value defining amount of time till the execution of
     *                     defined actions.
     * @return reference to an event itself.
     */
    public MS_FutureEvent withTimeTillExecution(long milliseconds) {
        worker.executeAfter = milliseconds;
        return this;
    }

    /**
     * Sets timeout vale for execution of event's action.
     *
     * @param timeout non-negative value defining maximal amount of time that
     *                execution should take before it will be interrupted.
     * @return reference to an event itself.
     */
    public MS_FutureEvent withTimeout(long timeout) {
        worker.withTimeout(timeout);
        return this;
    }

    /**
     * Sets actions that has to be performed on this event.
     * @param action an preferable lambda expression defining actions to be performed on execution.
     * @return reference to an event itself.
     */
    public MS_FutureEvent withAction(IFuncEvent action) {
        worker.actionOnExecution = action;
        return this;
    }

    /**
     * @param action an preferable lambda expression defining actions to be performed on
     *               interrupted thread exception.
     * @return reference to an event itself.
     */
    public MS_FutureEvent withActionOnInterruptedException(IFuncOnInterruptedException action) {
        worker.onInterruption = action;
        return this;
    }

    /**
     * @param action an preferable lambda expression defining actions to be performed on
     *               exception while executing event's action.
     * @return reference to an event itself.
     */
    public MS_FutureEvent withActionOnGeneralException(IFuncOnSomeException action) {
        worker.onExecution = action;
        return this;
    }

    /**
     * Creates new thread and starts time countdown till event execution then executes event.
     */
    public void schedule() {
        worker.start();
    }

    private static class WorkerThread extends MS_Thread<WorkerThread> {

        private long executeAfter = 0;
        private IFuncEvent actionOnExecution = null;
        private IFuncOnInterruptedException onInterruption = null;
        private IFuncOnSomeException onExecution = null;

        @Override
        protected void doOnExecution() {
            try {
                Thread.sleep(executeAfter);
                // essence of this kind of event is to run actual action only after sleep
                if (actionOnExecution != null) {
                    try {
                        actionOnExecution.execute();
                    } catch (Exception e) {
                        if (onExecution != null)
                            onExecution.doOnError(e);
                    }
                }
            } catch (InterruptedException e) {
                if (onInterruption != null)
                    onInterruption.doOnError(e);
            }
        }

        @Override
        public WorkerThread getThis() {
            return this;
        }
    }
}