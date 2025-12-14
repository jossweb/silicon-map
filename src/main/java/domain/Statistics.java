package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Dao.MachineDao;
import Dao.StatisticsDao;
import type.Tuple;

public class Statistics {
    private Map<Integer, Tuple<Integer, LocalDateTime>> temp; // <Machine id, <temp °, date>
    private Map<Integer, Tuple<Integer, LocalDateTime>> load; // <Component id, <load %, date>>
    private ArrayList<Machine> listMachine;

    public Statistics(){
        this.temp = new HashMap<>();
        this.load = new HashMap<>();
        this.listMachine = new ArrayList<>();
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
    public synchronized Double GetAvgLoad(){
        return this.load.values().stream()
            .mapToInt(Tuple::getFirst)
            .average()
            .orElse(0);
    }
    public synchronized Double GetAvgTemp(){
        return this.temp.values().stream()
            .mapToInt(Tuple::getFirst)
            .average()
            .orElse(0);
    }
    public ArrayList<Machine> getMachines(){
        return this.listMachine;
    }
}
