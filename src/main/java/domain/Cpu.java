package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Cpu extends Component{
	private int nb_core;
	private int max_ram;
	public Cpu(int id, String brand, String model, int m, int tid, int nb_core, int max_ram) {
		super(id, brand, model, m, tid);
		this.nb_core = nb_core;
		this.max_ram = max_ram;
	}
	public Cpu(ResultSet result){
		super(result);
		try{
			this.nb_core = result.getInt("spec_value_primary");
			this.max_ram = result.getInt("spec_value_secondary");
		}catch(SQLException e){
			System.out.print("\nERROR : Can't create Cpu with SQL constructor. \n" + e + "\nDEBUG : Check columns' names ...");
		}
	}
}
