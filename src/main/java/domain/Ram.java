package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents a RAM component in a machine |
 * Représente une barrette de RAM dans une machine.
 * 
 * @author FIGUEIRAS Jossua
 */

public class Ram extends Component{
	private int sizeGo;
	private int version;
	/**
     * Creates a Ram instance with explicit values |
     * Crée une instance Ram avec des valeurs explicites.
     *
     * @param id the unique identifier of the component |
     * l'identifiant unique du composant
     * @param brand the brand of the RAM |
     * la marque de la RAM
     * @param model the model of the RAM |
     * le modèle de la RAM
     * @param m the machine ID this RAM belongs to |
     * l'identifiant de la machine à laquelle appartient la RAM
     * @param tid the ticket ID associated with this component |
     * l'identifiant du ticket associé à ce composant
     * @param size_go the size of the RAM in GB |
     * la taille de la RAM en Go
     * @param version the RAM version/type (ex: DDR4, DDR5) |
     * la version/type de la RAM (ex : DDR4, DDR5)
     */
	public Ram(int id, String brand, String model, int m, int tid, int size_go, int version) {
		super(id, brand, model, m, tid);
		this.sizeGo = size_go;
		this.version = version;
	}
	/**
     * Creates a Ram instance from a SQL result set |
     * Crée une instance Ram à partir d'un résultat SQL.
     *
     * @param result the SQL result set containing RAM data |
     * le résultat SQL contenant les données de la RAM
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
     * Returns the size of the RAM in GB |
     * Renvoie la taille de la RAM en Go.
     *
     * @return the size in GB | la taille en Go
     */
	public int getSize_go() {
		return sizeGo;
	}
	/**
     * Returns the version/type of the RAM |
     * Renvoie la version/type de la RAM.
     *
     * @return the version/type | la version/type
     */
	public int getVersion() {
		return version;
	}
}
