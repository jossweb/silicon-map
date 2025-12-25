package application.components.message;

import domain.Admin;
import domain.Message;
import domain.Staff;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MessageBubble extends VBox {
    private Message message;
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
