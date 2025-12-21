package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Chassis extends Component{
	private int sizeU;
	public Chassis(int id, String brand, String model, int m, int tid, int sizeU) {
		super(id, brand, model, m, tid);
		this.sizeU = sizeU;
	}
	public Chassis(ResultSet result){
		super(result);
		try{
			this.sizeU = result.getInt("spec_value_primary");
		}catch(SQLException e){
			System.out.print("\nERROR : Can't create Chassis with SQL constructor. \n" + e + "\nDEBUG : Check columns' names ...");
		}
	}
	public int getSizeU() {
		return sizeU;
	}
}
