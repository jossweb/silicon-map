package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Disk extends Component{
	private int sizeGo;
	public Disk(int id, String brand, String model, int m, int tid, int size_go) {
		super(id, brand, model, m, tid);
		this.sizeGo = size_go;
	}
	public Disk(ResultSet result){
		super(result);
		try{
			this.sizeGo = result.getInt("spec_value_primary");
		}catch(SQLException e){
			System.out.print("\nERROR : Can't create Disk with SQL constructor. \n" + e + "\nDEBUG : Check columns' names ...");
		}
	}
	public int getSizeGo() {
		return sizeGo;
	}
}
