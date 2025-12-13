package domain;

public class Chassis extends Component{
	private int sizeU;
	public Chassis(String brand, String model, Machine m, int sizeU) {
		super(brand, model, m);
		this.sizeU = sizeU;
	}
}
