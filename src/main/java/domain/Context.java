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
 * staff members, tickets, temperatures and loads |
 * Cette classe centralise les données d'exécution telles que les machines,
 * composants, membres du personnel, tickets, températures et charges.
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
     * Creates an empty Context instance |
     * Crée une instance Context vide.
     */
    public Context(){
        this.temp = new HashMap<>();
        this.load = new HashMap<>();
        this.listMachine = new ArrayList<>();
        this.listStaffMembers = new ArrayList<>();
        this.listComponents = new ArrayList<>();
    }
    /**
     * Updates temperature data from the database |
     * Met à jour les données de température depuis la base de données.
     */
    public void updateTemp(){
        this.temp = ContextDao.getRecentTemp();
    }

    /**
     * Updates load data from the database |
     * Met à jour les données de charge depuis la base de données.
     */
    public void updateLoad(){
        this.load = ContextDao.getRecentLoad();
    }

    /**
     * Updates the machine list |
     * Met à jour la liste des machines.
     */
    public void updateMachinesList(){
        this.listMachine=MachineDao.getAllMachines();
    }
    /**
     * Updates the staff members list |
     * Met à jour la liste des membres du personnel.
     */
    public void updateStaffMembersList(){
        this.listStaffMembers = StaffDao.getAllStaffMembers();
    }

    /**
     * Updates the ticket list |
     * Met à jour la liste des tickets.
     */
    public void updateTicketList(){
        this.listTickets = TicketDao.getAllTicket();
    }
    /**
     * Updates the component list |
     * Met à jour la liste des composants.
     */
    public void updateComponentList(){
        this.listComponents = ComponentDao.getAllComponents();
    }
        /**
     * Computes the average load of all components |
     * Calcule la charge moyenne de tous les composants.
     *
     * @return the average load value |
     *         la valeur moyenne de charge
     */
    public Double getAvgLoad(){
        return this.load.values().stream()
            .mapToInt(list -> list.get(0).getFirst()) 
            .average()
            .orElse(0);
    }

    /**
     * Computes average temperatures for the latest inputs |
     * Calcule les températures moyennes pour les dernières valeurs enregistrées.
     *
     * @return a list of average temperature values with timestamps |
     *         une liste de températures moyennes avec leurs dates
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
     * Computes average loads for the latest inputs |
     * Calcule les charges moyennes pour les dernières valeurs enregistrées.
     *
     * @return a list of average load values with timestamps |
     * une liste de charges moyennes avec leurs dates
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
     * Computes the average temperature of all machines |
     * Calcule la température moyenne de toutes les machines.
     *
     * @return the average temperature |
     * la température moyenne
     */
    public Double getAvgTemp() {
        return this.temp.values().stream()
            .mapToInt(list -> list.get(0).getFirst()) 
            .average()
            .orElse(0);
    }
    /**
     * Returns the machine list |
     * Retourne la liste des machines.
     *
     * @return the list of machines |
     * la liste des machines
     */
    public ArrayList<Machine> getMachines(){
        return this.listMachine;
    }
    /**
     * Returns the staff members list |
     * Retourne la liste des membres du personnel.
     *
     * @return the list of staff members |
     *         la liste des membres du personnel
     */
    public ArrayList<Staff> getListStaffMembers(){
        return this.listStaffMembers;
   }
    /**
     * Returns the ticket list |
     * Retourne la liste des tickets.
     *
     * @return the list of tickets |
     * la liste des tickets
     */
    public ArrayList<Ticket> getListTickets(){
        return this.listTickets;
    }
    /**
     * Returns the component list |
     * Retourne la liste des composants.
     *
     * @return the list of components |
     * la liste des composants
     */
    public ArrayList<Component> getListComponents(){
        return this.listComponents;
    }
    /**
     * Returns the temperature history |
     * Retourne l'historique des températures.
     *
     * @return the temperature map |
     * la map des températures
     */
    public Map<Integer, ArrayList<Tuple<Integer, LocalDateTime>>> getTempList(){
        return this.temp;
    }
}
