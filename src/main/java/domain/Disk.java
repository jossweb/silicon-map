package domain;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Represents a storage disk component |
 * Représente un composant de stockage de type disque.
 *
 * @author FIGUEIRAS Jossua
 */

public class Disk extends Component{
	private int sizeGo;
	/**
     * Creates a Disk instance using explicit values |
     * Crée une instance de Disk à partir de valeurs explicites.
     *
     * @param id the unique identifier of the disk |
     * l'identifiant unique du disque
     * @param brand the disk manufacturer |
     * le fabricant du disque
     * @param model the disk model |
     * le modèle du disque
     * @param m the associated machine ID |
     * l'identifiant de la machine associée
     * @param tid the associated ticket ID |
     * l'identifiant du ticket associé
     * @param size_go the disk capacity in gigabytes |
     * la capacité du disque en gigaoctets
     */
	public Disk(int id, String brand, String model, int m, int tid, int size_go) {
		super(id, brand, model, m, tid);
		this.sizeGo = size_go;
	}
	/**
     * Creates a Disk instance from a SQL result set |
     * Crée une instance de Disk à partir d'un résultat SQL.
     *
     * @param result the SQL result set containing disk data |
     * le résultat SQL contenant les données du disque
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
     * Returns the disk capacity in gigabytes |
     * Retourne la capacité du disque en gigaoctets.
     *
     * @return the disk size in GB |
     * la taille du disque en Go
     */
	public int getSizeGo() {
		return sizeGo;
	}
}
