package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents a power supply component in a machine |
 * Représente une alimentation électrique dans une machine.
 * 
 * @author FIGUEIRAS Jossua
 */

public class PowerSupply extends Component{
	private int power;
	/**
     * Creates a PowerSupply instance with explicit values |
     * Crée une instance PowerSupply avec des valeurs explicites.
     *
     * @param id the unique identifier of the component |
     * l'identifiant unique du composant
     * @param brand the brand of the power supply |
     * la marque de l'alimentation
     * @param model the model of the power supply |
     * le modèle de l'alimentation
     * @param m the machine ID this component belongs to |
     * l'identifiant de la machine à laquelle le composant appartient
     * @param tid the ticket ID associated with this component |
     * l'identifiant du ticket associé à ce composant
     * @param power the power of the supply in watts |
     * la puissance de l'alimentation en watts
     */
	public PowerSupply(int id, String brand, String model, int m, int tid, int power) {
		super(id, brand, model, m, tid);
		this.power = power;
	}
	/**
     * Creates a PowerSupply instance from a SQL result set |
     * Crée une instance PowerSupply à partir d'un résultat SQL.
     *
     * @param result the SQL result set containing power supply data |
     * le résultat SQL contenant les données de l'alimentation
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
     * Returns the power of this supply in watts |
     * Renvoie la puissance de cette alimentation en watts.
     *
     * @return the power in watts | la puissance en watts
     */
	public int getPower() {
		return power;
	}
}
