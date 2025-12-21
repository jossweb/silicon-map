package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Ram extends Component{
	private int size_go;
	private int version;
	public Ram(int id, String brand, String model, int m, int tid, int size_go, int version) {
		super(id, brand, model, m, tid);
		this.size_go = size_go;
		this.version = version;
	}
	public Ram(ResultSet result){
		super(result);
		try{
			this.size_go = result.getInt("spec_value_primary");
			this.version = result.getInt("spec_value_secondary");
		}catch(SQLException e){
			System.out.print("\nERROR : Can't create Ram with SQL constructor. \n" + e + "\nDEBUG : Check columns' names ...");
		}
	}
	public int getSize_go() {
		return size_go;
	}
	public int getVersion() {
		return version;
	}
}
