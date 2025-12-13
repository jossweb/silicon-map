package domain;

public class Gpu extends Component{
	private int vram;
	private int nb_core;
	public Gpu(String brand, String model, Machine m, int vram, int nb_core) {
		super(brand, model, m);
		this.vram = vram;
		this.nb_core = nb_core;
	}
}
