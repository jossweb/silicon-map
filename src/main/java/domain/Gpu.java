package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Gpu extends Component{
	private int vram;
	private int nb_core;
	public Gpu(int id, String brand, String model, int m, int tid, int vram, int nb_core) {
		super(id, brand, model, m, tid);
		this.vram = vram;
		this.nb_core = nb_core;
	}
	public Gpu(ResultSet result){
		super(result);
		try{
			this.vram = result.getInt("spec_value_primary");
			this.nb_core = result.getInt("spec_value_secondary");
		}catch(SQLException e){
			System.out.print("\nERROR : Can't create Gpu with SQL constructor. \n" + e + "\nDEBUG : Check columns' names ...");
		}
	}
}
