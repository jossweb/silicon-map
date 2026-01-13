package domain;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Represents a storage disk component
 *
 * @author FIGUEIRAS Jossua
 */

public class Disk extends Component{
	private int sizeGo;
	/**
     * Creates a Disk instance using explicit values 
     *
     * @param id the unique identifier of the disk
     * @param brand the disk manufacturer 
     * @param model the disk model
     * @param m the associated machine ID
     * @param tid the associated ticket ID
     * @param size_go the disk capacity in gigabytes
     */
	public Disk(int id, String brand, String model, int m, int tid, int size_go) {
		super(id, brand, model, m, tid);
		this.sizeGo = size_go;
	}
	/**
     * Creates a Disk instance from a SQL result set
     *
     * @param result the SQL result set containing disk data
     */
	public Disk(ResultSet result){
		super(result);
		try{
			this.sizeGo = result.getInt("spec_value_primary");
		}catch(SQLException e){
			System.out.print("\nERROR : Can't create Disk with SQL constructor. \n" + e + "\nDEBUG : Check columns' names ...");
		}
	}
	/**
     * Returns the disk capacity in gigabytes
     *
     * @return the disk size in GB
     */
	public int getSizeGo() {
		return sizeGo;
	}
}
