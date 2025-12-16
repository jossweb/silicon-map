package domain;

import org.mindrot.jbcrypt.BCrypt;

import Dao.StaffDao;
import error.LoginError;
import error.UserNotFound;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import error.BadPassword;

public abstract class Staff {
	private int id;
	private String name;
	private String first_name;
	private String user_name;
	private String hashpass;
	private boolean available;
	
	public Staff(int id, String name, String firstname, String hashpass, String username, boolean available) {
		this.id = id;
		this.name = name;
		this.first_name = firstname;
		this.user_name = username;
		this.hashpass = hashpass;
		this.available = available;
	}
	public static String hashpass(String pass) {
		return BCrypt.hashpw(pass, BCrypt.gensalt(12));
	}
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
	public void AddMemberToDb() {
		StaffDao.createStaffMember(this);
	}
	public void DeleteMemberInDb() {
		StaffDao.deleteStaffFromDb(this);
	}
	public void setAvailable(boolean bool){
		StaffDao.changeStatus(bool, this.id);
	}
	public VBox staffBubble(){
		HBox nameContainer = new HBox(7);
		Label name = new Label(this.name);
		name.getStyleClass().add("subtitle");
		if(this instanceof Admin){
			SVGPath svg = new SVGPath();
			svg.setContent("M20 13c0 5-3.5 7.5-7.66 8.95a1 1 0 0 1-.67-.01C7.5 20.5 4 18 4 13V6a1 1 0 0 1 1-1c2 0 4.5-1.2 6.24-2.72a1.17 1.17 0 0 1 1.52 0C14.51 3.81 17 5 19 5a1 1 0 0 1 1 1z");
			svg.getStyleClass().add("gold-icon");
			nameContainer.getChildren().add(svg);
			name.getStyleClass().add("gold-text");
		}
		nameContainer.getChildren().add(name);

		Label firstName = new Label(this.first_name);
		firstName.getStyleClass().add("subsubtitle");

		HBox availbleContainer = new HBox(7);
		Circle circle = new Circle(7); 
		Label state = new Label("Online");
		if(this.available){
			circle.getStyleClass().add("green-dot");
		}else{
			state.setText("Offline");
			circle.getStyleClass().add("red-dot");
		}
		availbleContainer.getChildren().addAll(circle, state);
		VBox bubble = new VBox();
		bubble.getStyleClass().add("staff-bubble");
		bubble.getChildren().addAll(nameContainer, firstName, availbleContainer);
		bubble.setMaxWidth(Double.MAX_VALUE);
		return bubble;

	}
}
