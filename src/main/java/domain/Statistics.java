package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Dao.ComponentDao;
import Dao.MachineDao;
import Dao.StatisticsDao;
import Dao.TicketDao;
import Dao.StaffDao;
import type.Tuple;

public class Statistics {
    private Map<Integer, Tuple<Integer, LocalDateTime>> temp; // <Machine id, <temp °, date>
    private Map<Integer, Tuple<Integer, LocalDateTime>> load; // <Component id, <load %, date>>
    private ArrayList<Machine> listMachine;
    private ArrayList<Staff> listStaffMembers;
    private ArrayList<Ticket> listTickets;
    private ArrayList<Component> listComponents;

    public Statistics(){
        this.temp = new HashMap<>();
        this.load = new HashMap<>();
        this.listMachine = new ArrayList<>();
        this.listStaffMembers = new ArrayList<>();
        this.listComponents = new ArrayList<>();
    }
    public synchronized void updateTemp(){
        this.temp = StatisticsDao.getRecentTemp();
    }
    public synchronized void updateLoad(){
        this.load = StatisticsDao.getRecentLoad();
    }
    public synchronized void updateMachinesList(){
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
    public synchronized Double getAvgLoad(){
        return this.load.values().stream()
            .mapToInt(Tuple::getFirst)
            .average()
            .orElse(0);
    }
    public synchronized Double getAvgTemp(){
        return this.temp.values().stream()
            .mapToInt(Tuple::getFirst)
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
}
