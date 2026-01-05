package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import Dao.ComponentDao;

/**
 * Represents a generic hardware component
 * This abstract class is the base for all specific components |
 * Représente un composant matériel générique.
 * Cette classe abstraite est la base de tous les composants spécifiques.
 * 
 * @author FIGUEIRAS Jossua
 */

public abstract class Component {
	private int id;
	private String brand;
	private String model;
	private int machineId;
	private int ticketId;
	
	/**
     * Creates a Component instance using explicit values |
     * Crée une instance Component à partir de valeurs explicites.
     *
     * @param id the unique identifier of the component |
     * l'identifiant unique du composant
     * @param brand the manufacturer brand |
     * la marque du fabricant
     * @param model the model reference |
     * la référence du modèle
     * @param mid the identifier of the associated machine |
     * l'identifiant de la machine associée
     * @param tid the identifier of the associated ticket |
     * l'identifiant du ticket associé
     */
	public Component(int id, String brand, String model, int mid, int tid) {
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.machineId = mid;
		this.ticketId = tid;
	}
	/**
     * Creates a Component instance from a SQL result set |
     * Crée une instance Component à partir d'un résultat SQL.
     *
     * @param result the SQL result set containing component data |
     * le résultat SQL contenant les données du composant
     */
	public Component(ResultSet result){
		try{
			this.id = result.getInt("id");
			this.brand = result.getString("brand");
			this.model = result.getString("model");
			this.machineId = result.getInt("machine_id");
			this.ticketId = result.getInt("ticket");
		}catch(SQLException e){
			System.out.print("\nERROR : Can't create Component with SQL constructor. \n" + e + "\nDEBUG : Check columns' names ...");
		}
	}
	/**
     * Returns the unique identifier of the component |
     * Retourne l'identifiant unique du composant.
     *
     * @return the component ID |
     * l'identifiant du composant
     */
	public int getId() {
		return id;
	}
	/**
     * Returns the manufacturer brand |
     * Retourne la marque du fabricant.
     *
     * @return the brand name |
     * la marque
     */
	public String getBrand() {
		return brand;
	}
	/**
     * Returns the model reference |
     * Retourne la référence du modèle.
     *
     * @return the model name |
     * le modèle
     */
	public String getModel() {
		return model;
	}
	/**
     * Returns the associated machine identifier |
     * Retourne l'identifiant de la machine associée.
     *
     * @return the machine ID |
     * l'identifiant de la machine
     */
	public int getMachineId() {
		return machineId;
	}
	/**
     * Returns the associated ticket identifier |
     * Retourne l'identifiant du ticket associé.
     *
     * @return the ticket ID |
     * l'identifiant du ticket
     */
	public int getTicketId() {
		return ticketId;
	}
	/**
     * Updates the associated ticket identifier |
     * Met à jour l'identifiant du ticket associé.
     *
     * @param ticketId the new ticket identifier |
     * le nouvel identifiant du ticket
     */
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	/**
     * Persists the component updates to the database |
     * Enregistre les modifications du composant dans la base de données.
    */
	public void updateComponent(){
		ComponentDao.update(this);
	}
	/**
     * Returns the component type as a string |
     * Retourne le type du composant sous forme de chaîne.
     *
     * @param alternative the fallback value if the type is unknown |
     * la valeur alternative si le type est inconnu
     * @return the component type name |
     * le nom du type de composant
     */
	public String whoami(String alternative){
		if(this instanceof Chassis)
			return "Chassis";
		else if(this instanceof Cpu)
			return "Cpu";
		else if(this instanceof Disk)
			return "Disk";
		else if(this instanceof Gpu)
			return "Gpu";
		else if(this instanceof Ram)
			return "Ram";
		else if(this instanceof PowerSupply)
			return "Power Supply";
		else 
			return alternative;
	}
	/**
     * Retrieves a component by its unique identifier |
     * Récupère un composant à partir de son identifiant unique.
     *
     * @param id the ID of the component to retrieve |
     * l'identifiant du composant à récupérer
     * @return the component instance if found |
     * l'instance du composant si elle est trouvée
     */
	public static Component getById(int id){
		return ComponentDao.getComponentById(id);
	}
	/**
     * Compares two components based on their identifiers |
     * Compare deux composants en fonction de leur identifiant.
     *
     * @param c1 the first component |
     * le premier composant
     * @param c2 the second component |
     * le second composant
     * @return true if both components have the same ID |
     * true si les deux composants ont le même identifiant
     */
	public static boolean compareComponentById(Component c1, Component c2){
		if(c1.getId() == c2.getId()){
			return true;
		}else{
			return false;
		}
	}
}
