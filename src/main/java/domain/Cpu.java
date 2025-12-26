package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Cpu extends Component{
	private int nbCore;
	private int maxRam;
	public Cpu(int id, String brand, String model, int m, int tid, int nb_core, int max_ram) {
		super(id, brand, model, m, tid);
		this.nbCore = nb_core;
		this.maxRam = max_ram;
	}
	public Cpu(ResultSet result){
		super(result);
		try{
			this.nbCore = result.getInt("spec_value_primary");
			this.maxRam = result.getInt("spec_value_secondary");
		}catch(SQLException e){
			System.out.print("\nERROR : Can't create Cpu with SQL constructor. \n" + e + "\nDEBUG : Check columns' names ...");
		}
	}
	public int getNbCore() {
		return nbCore;
	}
	public int getMaxRam() {
		return maxRam;
	}
}
