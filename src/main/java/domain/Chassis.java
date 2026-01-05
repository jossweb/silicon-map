package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents a chassis component in the system |
 * Représente un composant de type châssis dans le système.
 * 
 * @author FIGUEIRAS Jossua
 */

public class Chassis extends Component{
	private int sizeU;
	/**
     * Creates a Chassis instance using explicit values |
     * Crée une instance Chassis à partir de valeurs explicites.
     *
     * @param id the unique identifier of the chassis |
     * l'identifiant unique du châssis
     * @param brand the manufacturer brand |
     * la marque du fabricant
     * @param model the model reference |
     * la référence du modèle
     * @param m the identifier of the machine associated with this chassis |
     * l'identifiant de la machine associée à ce châssis
     * @param tid the type identifier of the component |
     * l'identifiant du type de composant
     * @param sizeU the rack size in units (U) |
     * la taille du châssis en unités de rack (U)
     */
	public Chassis(int id, String brand, String model, int m, int tid, int sizeU) {
		super(id, brand, model, m, tid);
		this.sizeU = sizeU;
	}
	/**
     * Creates a Chassis instance from a SQL result set |
     * Crée une instance Chassis à partir d'un résultat SQL.
     *
     * @param result the SQL result set containing chassis data |
     * le résultat SQL contenant les données du châssis
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
     * Returns the rack size of the chassis |
     * Retourne la taille du châssis en unités de rack.
     *
     * @return the rack size in U |
     * la taille du châssis en U
     */
	public int getSizeU() {
		return sizeU;
	}
}
