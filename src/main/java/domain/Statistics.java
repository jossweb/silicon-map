package domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import Dao.StatisticsDao;
import type.Tuple;

public class Statistics {
    private Map<Integer, Tuple<Integer, LocalDateTime>> temp; // <Machine id, <temp °, date>
    private Map<Integer, Tuple<Integer, LocalDateTime>> load; // <Component id, <load %, date>>

    public Statistics(){
        this.temp = new HashMap<>();
        this.load = new HashMap<>();
    }
    public void updateTemp(){
        this.temp = StatisticsDao.getRecentTemp();
    }
    public void updateLoad(){
        this.load = StatisticsDao.getRecentLoad();
    }
    public void testClass(int idMachine, int component_id){
        System.out.print("\n--temp : " + this.temp.get(idMachine).getFirst() + "\n");
        System.out.print("--load : " + this.load.get(idMachine).getFirst() + "\n");
    }
}
