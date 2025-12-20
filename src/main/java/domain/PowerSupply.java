package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PowerSupply extends Component{
	private int power;
	public PowerSupply(int id, String brand, String model, int m, int tid, int power) {
		super(id, brand, model, m, tid);
		this.power = power;
	}
	public PowerSupply(ResultSet result){
		super(result);
		try{
			this.power = result.getInt("spec_value_primary");
		}catch(SQLException e){
			System.out.print("\nERROR : Can't create Power supply with SQL constructor. \n" + e + "\nDEBUG : Check columns' names ...");
		}
	}
}
