package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents a CPU component
 *
 * @author FIGUEIRAS Jossua
 */

public class Cpu extends Component{
	private int nbCore;
	private int maxRam;
	/**
     * Creates a CPU instance using explicit values
     *
     * @param id the unique identifier of the CPU
     * @param brand the CPU manufacturer 
     * @param model the CPU model 
     * @param m the associated machine ID
     * @param tid the associated ticket ID
     * @param nb_core the number of CPU cores
     * @param max_ram the maximum supported RAM
     */
	public Cpu(int id, String brand, String model, int m, int tid, int nb_core, int max_ram) {
		super(id, brand, model, m, tid);
		this.nbCore = nb_core;
		this.maxRam = max_ram;
	}
	/**
     * Creates a CPU instance from a SQL result set 
	 * 
     * @param result the SQL result set containing CPU data
     */
	public Cpu(ResultSet result){
		super(result);
		try{
			this.nbCore = result.getInt("spec_value_primary");
			this.maxRam = result.getInt("spec_value_secondary");
		}catch(SQLException e){
			System.out.print("\nERROR : Can't create Cpu with SQL constructor. \n" + e + "\nDEBUG : Check columns' names ...");
		}
	}
	/**
	 * Returns the number of CPU cores
	 *
	 * @return the number of cores
	 */
	public int getNbCore() {
		return nbCore;
	}
	/**
     * Returns the maximum supported RAM
     *
     * @return the maximum RAM value
     */
	public int getMaxRam() {
		return maxRam;
	}
}
