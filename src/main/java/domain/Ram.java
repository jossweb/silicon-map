package domain;

public class Ram extends Component{
	private int size_go;
	private int version;
	public Ram(String brand, String model, Machine m, int size_go, int version) {
		super(brand, model, m);
		this.size_go = size_go;
		this.version = version;
	}
}
