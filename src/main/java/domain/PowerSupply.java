package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents a power supply component in a machine
 * 
 * @author FIGUEIRAS Jossua
 */

public class PowerSupply extends Component{
	private int power;
	/**
     * Creates a PowerSupply instance with explicit values
     *
     * @param id the unique identifier of the component
     * @param brand the brand of the power supply
     * @param model the model of the power supply
     * @param m the machine ID this component belongs to
     * @param tid the ticket ID associated with this component 
     * @param power the power of the supply in watts
     */
	public PowerSupply(int id, String brand, String model, int m, int tid, int power) {
		super(id, brand, model, m, tid);
		this.power = power;
	}
	/**
     * Creates a PowerSupply instance from a SQL result set
     *
     * @param result the SQL result set containing power supply data
     */
	public PowerSupply(ResultSet result){
		super(result);
		try{
			this.power = result.getInt("spec_value_primary");
		}catch(SQLException e){
			System.out.print("\nERROR : Can't create Power supply with SQL constructor. \n" + e + "\nDEBUG : Check columns' names ...");
		}
	}
	/**
     * Returns the power of this supply in watts
     *
     * @return the power in watts
     */
	public int getPower() {
		return power;
	}
}
