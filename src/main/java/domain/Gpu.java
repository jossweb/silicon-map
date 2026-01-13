package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents a graphics processing unit component |
 * Représente un composant de type GPU.
 *
 * @author FIGUEIRAS Jossua
 */

public class Gpu extends Component{
	private int vram;
	private int nbCore;
	/**
     * Creates a GPU instance using explicit values 
     *
     * @param id the unique identifier of the GPU
     * @param brand the manufacturer brand
     * @param model the model reference
     * @param m the ID of the machine using this GPU
     * @param tid the associated ticket ID
     * @param vram the amount of video memory in MB
     * @param nb_core the number of GPU cores 
     */
	public Gpu(int id, String brand, String model, int m, int tid, int vram, int nb_core) {
		super(id, brand, model, m, tid);
		this.vram = vram;
		this.nbCore = nb_core;
	}
	/**
     * Creates a GPU instance from a SQL result set
     *
     * @param result the SQL result set containing GPU data
     */
	public Gpu(ResultSet result){
		super(result);
		try{
			this.vram = result.getInt("spec_value_primary");
			this.nbCore = result.getInt("spec_value_secondary");
		}catch(SQLException e){
			System.out.print("\nERROR : Can't create Gpu with SQL constructor. \n" + e + "\nDEBUG : Check columns' names ...");
		}
	}
	/**
     * Returns the amount of video memory
     *
     * @return the VRAM size in MB
     */
	public int getVram() {
		return vram;
	}
	/**
     * Returns the number of GPU cores
     *
     * @return the number of cores
     */
	public int getNbCore() {
		return nbCore;
	}
}
