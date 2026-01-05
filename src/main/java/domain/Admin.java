package domain;

import java.sql.ResultSet;

import Dao.StaffDao;

/**
 * Represents an administrator user in the system |
 * Représente un utilisateur administrateur dans le système.
 * 
 * @author FIGUEIRAS Jossua
 */

public class Admin extends Staff {
	/**
     * Creates an Admin instance using explicit values |
     * Crée une instance Admin à partir de valeurs explicites.
     *
     * @param id the unique identifier of the admin |
     * l'identifiant unique de l'administrateur
     * @param name the last name of the admin |
     * le nom de famille de l'administrateur
     * @param firstname the first name of the admin |
     * le prénom de l'administrateur
     * @param hash the hashed password |
     * le mot de passe chiffré
     * @param username the login username |
     * le nom d'utilisateur
     * @param available indicates if the admin account is active |
     * indique si le compte administrateur est actif
     */
	public Admin(int id, String name, String firstname, String hash, String username, boolean available) {
		super(id, name, firstname, hash, username, available);
	}
     /**
     * Creates an Admin instance from a SQL result set |
     * Crée une instance Admin à partir d'un résultat SQL.
     *
     * @param sqlResult the SQL result set containing admin data |
     * le résultat SQL contenant les données de l'administrateur
     */
	public Admin(ResultSet sqlResult){
		super(sqlResult);
	}
     /**
     * Retrieves an Admin by its unique identifier |
     * Récupère un administrateur à partir de son identifiant unique.
     *
     * @param id the ID of the admin to retrieve |
     * l'identifiant de l'administrateur à récupérer
     * @return the Admin instance if found and valid 
     * or null if the staff member is not an admin|
     * l'instance Admin si elle est trouvée et valide,
     * ou null si le membre du personnel n'est pas un administrateur
     */
	public static Admin getAdminById(int id){
		Staff s = StaffDao.getStaffMember(id);
		if(s instanceof Admin){
			return (Admin)s;
		}else{
			return null;
		}
	}
}
