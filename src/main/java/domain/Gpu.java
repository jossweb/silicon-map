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
     * Creates a GPU instance using explicit values |
     * Crée une instance de GPU à partir de valeurs explicites.
     *
     * @param id the unique identifier of the GPU |
     * l'identifiant unique du GPU
     * @param brand the manufacturer brand |
     * la marque du fabricant
     * @param model the model reference |
     * la référence du modèle
     * @param m the ID of the machine using this GPU |
     * l'identifiant de la machine utilisant ce GPU
     * @param tid the associated ticket ID |
     * l'identifiant du ticket associé
     * @param vram the amount of video memory in MB |
     * la quantité de mémoire vidéo en Mo
     * @param nb_core the number of GPU cores |
     * le nombre de cœurs du GPU
     */
	public Gpu(int id, String brand, String model, int m, int tid, int vram, int nb_core) {
		super(id, brand, model, m, tid);
		this.vram = vram;
		this.nbCore = nb_core;
	}
	/**
     * Creates a GPU instance from a SQL result set |
     * Crée une instance de GPU à partir d'un résultat SQL.
     *
     * @param result the SQL result set containing GPU data |
     * le résultat SQL contenant les données du GPU
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
     * Returns the amount of video memory |
     * Retourne la quantité de mémoire vidéo.
     *
     * @return the VRAM size in MB |
     * la taille de la VRAM en Mo
     */
	public int getVram() {
		return vram;
	}
	/**
     * Returns the number of GPU cores |
     * Retourne le nombre de cœurs du GPU.
     *
     * @return the number of cores |
     * le nombre de cœurs
     */
	public int getNbCore() {
		return nbCore;
	}
}
