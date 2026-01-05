package domain;

import java.sql.ResultSet;

import Dao.StaffDao;

/**
 * Represents a technician user in the system |
 * Représente un utilisateur technicien dans le système.
 * 
 * @author FIGUEIRAS Jossua
 */

public class Technician extends Staff{
    /**
     * Creates a Technician instance using explicit values |
     * Crée une instance Technician à partir de valeurs explicites.
     *
     * @param id the unique identifier of the technician |
     * l'identifiant unique du technicien
     * @param name the last name of the technician |
     * le nom de famille du technicien
     * @param firstname the first name of the technician |
     * le prénom du technicien
     * @param hash the hashed password |
     * le mot de passe chiffré
     * @param username the login username |
     * le nom d'utilisateur
     * @param available indicates if the technician account is active |
     * indique si le compte du technicien est actif
     */
	public Technician(int id, String name, String firstname, String hash, String username, boolean available) {
		super(id, name, firstname, hash, username, available);
	}
	 /**
     * Creates a Technician instance from a SQL result set |
     * Crée une instance Technician à partir d'un résultat SQL.
     *
     * @param sqlResult the SQL result set containing technician data |
     * le résultat SQL contenant les données du technicien
     */
	public Technician(ResultSet sqlResult){
		super(sqlResult);
	}

    /**
     * Retrieves a Technician by its unique identifier |
     * Récupère un technicien à partir de son identifiant unique.
     *
     * @param id the ID of the technician to retrieve |
     *           l'identifiant du technicien à récupérer
     * @return the Technician instance if found and valid,
     * or null if the staff member is not a technician |
     * l'instance Technician si elle est trouvée et valide,
     * ou null si le membre du personnel n'est pas un technicien
     */
	public static Technician getTechncianById(int id){
		Staff s = StaffDao.getStaffMember(id);
		if(s instanceof Technician){
			return (Technician)s;
		}else{
			return null;
		}
	}
}
