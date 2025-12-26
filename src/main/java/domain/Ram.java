package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Ram extends Component{
	private int sizeGo;
	private int version;
	public Ram(int id, String brand, String model, int m, int tid, int size_go, int version) {
		super(id, brand, model, m, tid);
		this.sizeGo = size_go;
		this.version = version;
	}
	public Ram(ResultSet result){
		super(result);
		try{
			this.sizeGo = result.getInt("spec_value_primary");
			this.version = result.getInt("spec_value_secondary");
		}catch(SQLException e){
			System.out.print("\nERROR : Can't create Ram with SQL constructor. \n" + e + "\nDEBUG : Check columns' names ...");
		}
	}
	public int getSize_go() {
		return sizeGo;
	}
	public int getVersion() {
		return version;
	}
}
