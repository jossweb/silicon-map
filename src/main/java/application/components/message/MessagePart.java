package application.components.message;

import java.util.ArrayList;

import application.interfaces.refreshable;
import domain.Message;
import domain.Staff;
import domain.Ticket;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * class that manages the display of messages
 * 
 * @author FIGUEIRAS Jossua
 */
public class MessagePart extends ScrollPane implements refreshable{
    private VBox container;
    private Ticket ticket;
    private Staff loguser;

    /** 
     * @param t the ticket whose messages are displayed
     * @param logUser the currently logged-in user
     */
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
    /**
     * refreshes messages on display
     */
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
