package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents a RAM component in a machine
 * 
 * @author FIGUEIRAS Jossua
 */

public class Ram extends Component{
	private int sizeGo;
	private int version;
	/**
     * Creates a Ram instance with explicit values
     *
     * @param id the unique identifier of the component
     * @param brand the brand of the RAM
     * @param model the model of the RAM
     * @param m the machine ID this RAM belongs to 
     * @param tid the ticket ID associated with this component
     * @param size_go the size of the RAM in GB 
     * @param version the RAM version/type (ex: DDR4, DDR5)
     */
	public Ram(int id, String brand, String model, int m, int tid, int size_go, int version) {
		super(id, brand, model, m, tid);
		this.sizeGo = size_go;
		this.version = version;
	}
	/**
     * Creates a Ram instance from a SQL result set 
     *
     * @param result the SQL result set containing RAM data
     */
	public Ram(ResultSet result){
		super(result);
		try{
			this.sizeGo = result.getInt("spec_value_primary");
			this.version = result.getInt("spec_value_secondary");
		}catch(SQLException e){
			System.out.print("\nERROR : Can't create Ram with SQL constructor. \n" + e + "\nDEBUG : Check columns' names ...");
		}
	}
	/**
     * Returns the size of the RAM in GB
     *
     * @return the size in GB
     */
	public int getSize_go() {
		return sizeGo;
	}
	/**
     * Returns the version/type of the RAM
     *
     * @return the version/type
     */
	public int getVersion() {
		return version;
	}
}
