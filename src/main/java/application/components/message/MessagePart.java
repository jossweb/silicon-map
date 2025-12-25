package application.components.message;

import java.util.ArrayList;

import domain.Message;
import domain.Staff;
import domain.Ticket;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MessagePart extends ScrollPane {
    private VBox container;
    private Ticket ticket;
    private Staff loguser;

    public MessagePart(Ticket t, Staff logUser){
        this.ticket = t;
        this.loguser = logUser;
        this.container = new VBox(5);

        ArrayList<Message> messageList = Message.getForTicketId(this.ticket.getId());

        if(messageList.size()==0){
            this.container.getChildren().add(new Label("no messages available"));
        }else{
            for(Message message : messageList){
                this.container.getChildren().add(new MessageBubble(message, this.loguser));
            }   
        }
        this.setContent(this.container);
        this.getStyleClass().add("chat-part");
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.setFitToWidth(true);
    }
    public void refresh(){
        this.container.getChildren().clear();

        ArrayList<Message> messageList = Message.getForTicketId(this.ticket.getId());

        if(messageList.size()==0){
            this.container.getChildren().add(new Label("no messages available"));
        }else{
            for(Message message : messageList){
                this.container.getChildren().add(new MessageBubble(message, this.loguser));
            }   
        }
    }
}
