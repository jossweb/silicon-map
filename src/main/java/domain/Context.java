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

public class Context {
    private Map<Integer, ArrayList<Tuple<Integer, LocalDateTime>>> temp; // <Machine id, list <<temp °, date>>
    private Map<Integer, ArrayList<Tuple<Integer, LocalDateTime>>> load; // <Component id, <load %, date>>
    private ArrayList<Machine> listMachine;
    private ArrayList<Staff> listStaffMembers;
    private ArrayList<Ticket> listTickets;
    private ArrayList<Component> listComponents;

    public Context(){
        this.temp = new HashMap<>();
        this.load = new HashMap<>();
        this.listMachine = new ArrayList<>();
        this.listStaffMembers = new ArrayList<>();
        this.listComponents = new ArrayList<>();
    }
    public void updateTemp(){
        this.temp = ContextDao.getRecentTemp();
    }
    public void updateLoad(){
        this.load = ContextDao.getRecentLoad();
    }
    public void updateMachinesList(){
        this.listMachine=MachineDao.getAllMachines();
    }
    public void updateStaffMembersList(){
        this.listStaffMembers = StaffDao.getAllStaffMembers();
    }
    public void updateTicketList(){
        this.listTickets = TicketDao.getAllTicket();
    }
    public void updateComponentList(){
        this.listComponents = ComponentDao.getAllComponents();
    }
    public Double getAvgLoad(){
        return this.load.values().stream()
            .mapToInt(list -> list.get(0).getFirst()) 
            .average()
            .orElse(0);
    }
    public ArrayList<Tuple<Integer, LocalDateTime>> getAvgTempLastInputs() {
        ArrayList<Tuple<Integer, LocalDateTime>> result = new ArrayList<>();
        ArrayList<Tuple<Integer, LocalDateTime>> ref = this.temp.values().stream().findFirst().orElse(null);

        if (ref == null) {
            System.out.print("\nERROR : load values empty");
            return result;
        }

        for (int i = 0; i < ref.size(); i++) {

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
    public ArrayList<Tuple<Integer, LocalDateTime>> getAvgLoadLastInputs() {
        ArrayList<Tuple<Integer, LocalDateTime>> result = new ArrayList<>();
        ArrayList<Tuple<Integer, LocalDateTime>> ref = this.load.values().stream().findFirst().orElse(null);

        if (ref == null) {
            System.out.print("\nERROR : load values empty");
            return result;
        }

        for (int i = 0; i < ref.size(); i++) {

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
    public Double getAvgTemp() {
        return this.temp.values().stream()
            .mapToInt(list -> list.get(0).getFirst()) 
            .average()
            .orElse(0);
    }
    public ArrayList<Machine> getMachines(){
        return this.listMachine;
    }
    public ArrayList<Staff> getListStaffMembers(){
        return this.listStaffMembers;
   }
   public ArrayList<Ticket> getListTickets(){
        return this.listTickets;
   }
   public ArrayList<Component> getListComponents(){
        return this.listComponents;
   }
   public Map<Integer, ArrayList<Tuple<Integer, LocalDateTime>>> getTempList(){
        return this.temp;
   }
}
