package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import Dao.StaffDao;
import error.LoginError;
import error.UserNotFound;
import error.BadPassword;

/**
 * Represents a staff member of the system |
 * Représente un membre du personnel dans le système.
 * 
 * @author FIGUEIRAS Jossua
 */

public abstract class Staff {
	private int id;
	private String name;
	private String first_name;
	private String user_name;
	private String hashpass;
	private boolean available;
	
	/**
     * Creates a Staff instance with explicit values |
     * Crée une instance Staff avec des valeurs explicites.
     *
     * @param id the unique identifier of the staff member |
     * l'identifiant unique du membre du personnel
     * @param name the last name of the staff member |
     * le nom de famille du membre du personnel
     * @param firstname the first name of the staff member |
     * le prénom du membre du personnel
     * @param hashpass the hashed password |
     * le mot de passe chiffré
     * @param username the login username |
     * le nom d'utilisateur
     * @param available indicates if the account is active |
     * indique si le compte est actif
     */
	public Staff(int id, String name, String firstname, String hashpass, String username, boolean available) {
		this.id = id;
		this.name = name;
		this.first_name = firstname;
		this.user_name = username;
		this.hashpass = hashpass;
		this.available = available;
	}
	 /**
     * Creates a Staff instance from a SQL result set |
     * Crée une instance Staff à partir d'un résultat SQL.
     *
     * @param sqlResult the SQL result set containing staff data |
     *                  le résultat SQL contenant les données du membre du personnel
     */
	public Staff(ResultSet sqlResult){
		try{
			this.id = sqlResult.getInt("id");
			this.name = sqlResult.getString("name");
			this.first_name = sqlResult.getString("first_name");
			this.user_name = sqlResult.getString("user_name");
			this.hashpass = sqlResult.getString("hashpass");
			this.available = sqlResult.getBoolean("available");
		}catch(SQLException e){
			System.out.print("\nERROR : Can't create Staff with SQL constructor. \n" + e + "\nDEBUG : Check columns' names ...");
		}

	}
	/**
     * Hashes a plain password using BCrypt |
     * Chiffre un mot de passe en clair avec BCrypt.
     *
     * @param pass the plain password |
     *             le mot de passe en clair
     * 
	 * @return the hashed password |
     *         le mot de passe chiffré
     */
	public static String hashpass(String pass) {
		return BCrypt.hashpw(pass, BCrypt.gensalt(12));
	}
	/**
     * Checks login credentials and returns the Staff member |
     * Vérifie les identifiants et retourne le membre du personnel.
     *
     * @param username the login username |
     *                 le nom d'utilisateur
     * @param pass the plain password |
     *             le mot de passe en clair
     * @return the Staff instance if credentials are correct |
     *         l'instance Staff si les identifiants sont corrects
     * @throws LoginError if login fails (user not found or bad password) |
     *                    lance une exception si la connexion échoue (utilisateur non trouvé ou mot de passe incorrect)
     */
	public static Staff checkPass(String username, String pass) throws LoginError{
		//return the staff member if pass and username are correct 
		// else null
		Staff userFromDb = StaffDao.getStaffMember(username);
		if(userFromDb != null){
			if(BCrypt.checkpw(pass, userFromDb.hashpass))
				return userFromDb;

			throw new BadPassword();
		}
		throw new UserNotFound();
	}
	public String getName() {
		return name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public String getHashpass() {
		return hashpass;
	}
	public int getId() {
		return id;
	}

    /**
     * Adds the staff member to the database |
     * Ajoute le membre du personnel dans la base de données.
     */
	public void AddMemberToDb() {
		StaffDao.createStaffMember(this);
	}
	/**
     * Deletes the staff member from the database |
     * Supprime le membre du personnel de la base de données.
     */
	public void DeleteMemberInDb() {
		StaffDao.deleteStaffFromDb(this);
	}
	/**
     * Updates the availability status of the staff member |
     * Met à jour le statut de disponibilité du membre du personnel.
     *
     * @param bool true if available, false otherwise |
     * true si disponible, faux sinon
     */
	public void setAvailable(boolean bool){
		StaffDao.changeStatus(bool, this.id);
	}
	public boolean getAvailable(){
		return this.available;
	}
	/**
     * Retrieves a staff member by its ID |
     * Récupère un membre du personnel par son identifiant.
     *
     * @param id the unique identifier of the staff member |
     * l'identifiant unique du membre du personnel
     * 
	 * @return the Staff instance |
     * l'instance Staff
     */
	public static Staff getUserById(int id){
		return StaffDao.getStaffMember(id);
	}
}
