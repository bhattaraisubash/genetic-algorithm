package np.com.bsubash.ga.binary;

/**
 * @author subash
 * @since Mar 27, 2021
 */
public class Gene {
	private int number;

	public Gene(int number) {
		this.number = number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return String.valueOf(this.getNumber());
	}
}
