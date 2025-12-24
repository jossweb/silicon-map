package application.components.staff;

import domain.Context;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class StaffCard extends ScrollPane{
    public StaffCard(Context context){ 
        GridPane container = new GridPane(10, 10);
        context.updateStaffMembersList();

        int row = 0;
        int col = 0;
        for(int i = 0; i<context.getListStaffMembers().size(); i++){
            container.add(context.getListStaffMembers().get(i).staffBubble(), col, row);

            if(col==1){
                col=0;
                row++;
            }else{
                col++;
            }
        }
        ColumnConstraints constraint = new ColumnConstraints();
        constraint.setHgrow(Priority.ALWAYS);
        constraint.setFillWidth(true);
        container.getColumnConstraints().addAll(constraint, constraint);

        this.setContent(container);
        this.setFitToWidth(true);  
        this.setFitToHeight(false);
        this.setPannable(true);

    }
}
