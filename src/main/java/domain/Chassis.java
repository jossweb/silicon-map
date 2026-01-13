package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents a chassis component in the system
 * 
 * @author FIGUEIRAS Jossua
 */

public class Chassis extends Component{
	private int sizeU;
	/**
     * Creates a Chassis instance using explicit values
     *
     * @param id the unique identifier of the chassis
     * @param brand the manufacturer brand
     * @param model the model reference
     * @param m the identifier of the machine associated with this chassis 
     * @param tid the type identifier of the component 
     * @param sizeU the rack size in units (U)
     */
	public Chassis(int id, String brand, String model, int m, int tid, int sizeU) {
		super(id, brand, model, m, tid);
		this.sizeU = sizeU;
	}
	/**
     * Creates a Chassis instance from a SQL result set
     *
     * @param result the SQL result set containing chassis data
     */
	public Chassis(ResultSet result){
		super(result);
		try{
			this.sizeU = result.getInt("spec_value_primary");
		}catch(SQLException e){
			System.out.print("\nERROR : Can't create Chassis with SQL constructor. \n" + e + "\nDEBUG : Check columns' names ...");
		}
	}
	/**
     * Returns the rack size of the chassis
     *
     * @return the rack size in U 
     */
	public int getSizeU() {
		return sizeU;
	}
}
