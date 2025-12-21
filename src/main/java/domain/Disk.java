package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Disk extends Component{
	private int size_go;
	public Disk(int id, String brand, String model, int m, int tid, int size_go) {
		super(id, brand, model, m, tid);
		this.size_go = size_go;
	}
	public Disk(ResultSet result){
		super(result);
		try{
			this.size_go = result.getInt("spec_value_primary");
		}catch(SQLException e){
			System.out.print("\nERROR : Can't create Disk with SQL constructor. \n" + e + "\nDEBUG : Check columns' names ...");
		}
	}
	public int getSize_go() {
		return size_go;
	}
}
