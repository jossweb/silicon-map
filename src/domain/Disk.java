package domain;

public class Disk extends Component{
	private int size_go;
	public Disk(String brand, String model, Machine m, int size_go) {
		super(brand, model, m);
		this.size_go = size_go;
	}
}
