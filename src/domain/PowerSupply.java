package domain;

public class PowerSupply extends Component{
	private int power;
	public PowerSupply(String brand, String model, Machine m, int power) {
		super(brand, model, m);
		this.power = power;
	}
}
