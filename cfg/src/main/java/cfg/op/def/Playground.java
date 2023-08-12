package cfg.op.def;


/**
 * @author dizisa
 *
 */
public class Playground {

	/**
	 * @param input
	 * @return
	 */
	public int calculate(int input) {
		if (input > 1) {
			
			return (input--) * calculate(input);
		}
		return 1;
	}

}
