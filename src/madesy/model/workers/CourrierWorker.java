package madesy.model.workers;


/**
 * Worker process used to simulate basic duties of a courier,
 * which are dispatching and taking a picking.
 */
public class CourrierWorker extends BaseWorker {

	public CourrierWorker(int sleepTime) {
		super(sleepTime);
	}

	@Override
	public void doWork() {
	}

}
