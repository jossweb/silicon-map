package application.components.message;

import domain.Admin;
import domain.Message;
import domain.Staff;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Creates the message display bubble |
 * Crée la bulle d'affichage du message
 * 
 * @author FIGUEIRAS Jossua
 */
public class MessageBubble extends VBox {
    private Message message;
    /**
     * @param m the message to display
     * @param logUser the currently logged-in user
     */
    public MessageBubble(Message m, Staff logUser){
        this.message = m;
        Label author = new Label(this.message.getAuthor().getName() + " " + this.message.getAuthor().getFirst_name());
        author.getStyleClass().add("bold");

        Label message = new Label(this.message.getContent());
        message.setWrapText(true);

        if(logUser.getId() == this.message.getAuthor().getId()){
            author.setText("You");
        }
        if(this.message.getAuthor() instanceof Admin){
             this.getStyleClass().add("message-admin");
             author.setText(author.getText() + " - admin");
        }else{
            this.getStyleClass().add("message-technician");
        }
        this.getChildren().addAll(author, message);
        this.setSpacing(10);
    }
}
