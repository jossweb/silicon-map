package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents a CPU component |
 * Représente un composant CPU.
 *
 * @author FIGUEIRAS Jossua
 */

public class Cpu extends Component{
	private int nbCore;
	private int maxRam;
	/**
     * Creates a CPU instance using explicit values |
     * Crée une instance de CPU à partir de valeurs explicites.
     *
     * @param id the unique identifier of the CPU |
     * l'identifiant unique du CPU
     * @param brand the CPU manufacturer |
     * le fabricant du CPU
     * @param model the CPU model |
     * le modèle du CPU
     * @param m the associated machine ID |
     * l'identifiant de la machine associée
     * @param tid the associated ticket ID |
     * l'identifiant du ticket associé
     * @param nb_core the number of CPU cores |
     * le nombre de cœurs du processeur
     * @param max_ram the maximum supported RAM |
     * la quantité maximale de RAM supportée
     */
	public Cpu(int id, String brand, String model, int m, int tid, int nb_core, int max_ram) {
		super(id, brand, model, m, tid);
		this.nbCore = nb_core;
		this.maxRam = max_ram;
	}
	/**
     * Creates a CPU instance from a SQL result set |
     * Crée une instance de CPU à partir d'un résultat SQL.
     *
     * @param result the SQL result set containing CPU data |
     * le résultat SQL contenant les données du CPU
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
	 * Returns the number of CPU cores |
	 * Retourne le nombre de cœurs du processeur.
	 *
	 * @return the number of cores |
	 * le nombre de cœurs
	 */
	public int getNbCore() {
		return nbCore;
	}
	/**
     * Returns the maximum supported RAM |
     * Retourne la quantité maximale de RAM supportée.
     *
     * @return the maximum RAM value |
     * la valeur maximale de RAM
     */
	public int getMaxRam() {
		return maxRam;
	}
}
