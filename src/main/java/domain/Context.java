package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Dao.ComponentDao;
import Dao.MachineDao;
import Dao.ContextDao;
import Dao.TicketDao;
import Dao.StaffDao;
import type.Tuple;

/**
 * This class centralizes runtime data such as machines, components,
 * staff members, tickets, temperatures and loads 
 *
 * @author EVANGELISTA Thomas and FIGUEIRAS Jossua
 */

public class Context {
    private Map<Integer, ArrayList<Tuple<Integer, LocalDateTime>>> temp; // <Machine id, list <<temp °, date>>
    private Map<Integer, ArrayList<Tuple<Integer, LocalDateTime>>> load; // <Component id, <load %, date>>
    private ArrayList<Machine> listMachine;
    private ArrayList<Staff> listStaffMembers;
    private ArrayList<Ticket> listTickets;
    private ArrayList<Component> listComponents;


    /**
     * Creates an empty Context instance
     */
    public Context(){
        this.temp = new HashMap<>();
        this.load = new HashMap<>();
        this.listMachine = new ArrayList<>();
        this.listStaffMembers = new ArrayList<>();
        this.listComponents = new ArrayList<>();
    }
    /**
     * Updates temperature data from the database 
     */
    public void updateTemp(){
        this.temp = ContextDao.getRecentTemp();
    }

    /**
     * Updates load data from the database
     */
    public void updateLoad(){
        this.load = ContextDao.getRecentLoad();
    }

    /**
     * Updates the machine list 
     */
    public void updateMachinesList(){
        this.listMachine=MachineDao.getAllMachines();
    }
    /**
     * Updates the staff members list
     */
    public void updateStaffMembersList(){
        this.listStaffMembers = StaffDao.getAllStaffMembers();
    }

    /**
     * Updates the ticket list 
     */
    public void updateTicketList(){
        this.listTickets = TicketDao.getAllTicket();
    }
    /**
     * Updates the component list 
     */
    public void updateComponentList(){
        this.listComponents = ComponentDao.getAllComponents();
    }
        /**
     * Computes the average load of all components
     *
     * @return the average load value 
     */
    public Double getAvgLoad(){
        return this.load.values().stream()
            .mapToInt(list -> list.get(0).getFirst()) 
            .average()
            .orElse(0);
    }

    /**
     * Computes average temperatures for the latest inputs 
     *
     * @return a list of average temperature values with timestamps
     */
    public ArrayList<Tuple<Integer, LocalDateTime>> getAvgTempLastInputs() {
        ArrayList<Tuple<Integer, LocalDateTime>> result = new ArrayList<>();
        ArrayList<Tuple<Integer, LocalDateTime>> ref = this.temp.values().stream().findFirst().orElse(null);

        if (ref == null) {
            System.out.print("\nERROR : load values empty");
            return result;
        }

        for (int i = ref.size() - 1; i >= 0; i--) {

            int sum = 0;
            int count = 0;
            LocalDateTime date = ref.get(i).getSecond();

            for (ArrayList<Tuple<Integer, LocalDateTime>> list : this.temp.values()) {
                if (i < list.size()) {
                    sum += list.get(i).getFirst();
                    count++;
                }
            }
            int avg = 0;
            if (count != 0) {
                avg = sum / count;
            }
            result.add(new Tuple<>(avg, date));
        }

        return result;
    }
    /**
     * Computes average loads for the latest inputs
     *
     * @return a list of average load values with timestamps
     */
    public ArrayList<Tuple<Integer, LocalDateTime>> getAvgLoadLastInputs() {
        ArrayList<Tuple<Integer, LocalDateTime>> result = new ArrayList<>();
        ArrayList<Tuple<Integer, LocalDateTime>> ref = this.load.values().stream().findFirst().orElse(null);

        if (ref == null) {
            System.out.print("\nERROR : load values empty");
            return result;
        }

        for (int i = ref.size() - 1; i >= 0; i--) {

            int sum = 0;
            int count = 0;
            LocalDateTime date = ref.get(i).getSecond();

            for (ArrayList<Tuple<Integer, LocalDateTime>> list : this.load.values()) {
                if (i < list.size()) {
                    sum += list.get(i).getFirst();
                    count++;
                }
            }

            int avg = 0;
            if (count != 0) {
                avg = sum / count;
            }
            result.add(new Tuple<>(avg, date));
        }

        return result;
    }
    /**
     * Computes the average temperature of all machines
     *
     * @return the average temperature
     */
    public Double getAvgTemp() {
        return this.temp.values().stream()
            .mapToInt(list -> list.get(0).getFirst()) 
            .average()
            .orElse(0);
    }
    /**
     * Returns the machine list
     *
     * @return the list of machines
     */
    public ArrayList<Machine> getMachines(){
        return this.listMachine;
    }
    /**
     * Returns the staff members list
     *
     * @return the list of staff members
     */
    public ArrayList<Staff> getListStaffMembers(){
        return this.listStaffMembers;
   }
    /**
     * Returns the ticket list 
     *
     * @return the list of tickets
     */
    public ArrayList<Ticket> getListTickets(){
        return this.listTickets;
    }
    /**
     * Returns the component list
     *
     * @return the list of components
     */
    public ArrayList<Component> getListComponents(){
        return this.listComponents;
    }
    /**
     * Returns the temperature history
     *
     * @return the temperature map 
     */
    public Map<Integer, ArrayList<Tuple<Integer, LocalDateTime>>> getTempList(){
        return this.temp;
    }
}
