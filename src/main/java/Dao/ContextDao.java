package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import type.Tuple;
import java.sql.SQLException;

/**
 * All SQL interactions related to the app context and app statistics
 * 
 * @author FIGUEIRAS Jossua
 */
public abstract class ContextDao {
    /**
     * Retrieves the last 5 temperature readings on a machine
     * 
     * @return A hashmap containing the machine id (key) and a list of Tuples temperature, date (value) 
     */
    public static HashMap<Integer, ArrayList<Tuple<Integer, LocalDateTime>>> getRecentTemp(){
        HashMap<Integer, ArrayList<Tuple<Integer, LocalDateTime>>> temps = new HashMap<>();
        try {
			Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT machine_id, temperature, date_time FROM ( SELECT machine_id, temperature, date_time, ROW_NUMBER() OVER ( PARTITION BY machine_id ORDER BY date_time DESC ) AS rn FROM temperature ) AS sub WHERE rn <= 5 ORDER BY machine_id, date_time DESC;");
			ResultSet result = stmt.executeQuery();

            while (result.next()) {
                int machineId = result.getInt("machine_id");
                int temperature = result.getInt("temperature");

                LocalDateTime dateTime = result.getTimestamp("date_time").toLocalDateTime();

                temps.computeIfAbsent(machineId, e-> new ArrayList<>())
                    .add(new Tuple<>(temperature, dateTime));
                }

        } catch (SQLException e) {
            System.out.println("SQL ERROR !\nexplains: " + e);
        }

        return temps;
    }
    /**
     * Retrieves the last 5 load % readings on a component
     * 
     * @return A hashmap containing the component id (key) and a list of Tuples load%, date (value)
     */
    public static HashMap<Integer, ArrayList<Tuple<Integer, LocalDateTime>>> getRecentLoad(){

        HashMap<Integer, ArrayList<Tuple<Integer, LocalDateTime>>> load = new HashMap<>();
        try {
            Connection conn = SingleConnection.GetConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT component_id, components_load, date_time FROM (SELECT component_id, components_load, date_time, ROW_NUMBER() OVER (PARTITION BY component_id ORDER BY date_time DESC) AS rn FROM component_load) AS sub WHERE rn <= 5 ORDER BY component_id, date_time DESC;");
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                int machineId = result.getInt("component_id");
                int temperature = result.getInt("components_load");

                LocalDateTime dateTime = result.getTimestamp("date_time").toLocalDateTime();

                load.computeIfAbsent(machineId, e-> new ArrayList<>()).add(new Tuple<>(temperature, dateTime));
                }
        }catch(SQLException e) {
            System.out.println("SQL ERROR ! /n explains :" + e);
        }
        return load;
    }
    /**
     * Saves component load to the database
     * 
     * @param map A hashmap containing the component id (key) and component load %
     */
    public static void sendHashMapLoad(HashMap<Integer, Integer> map){
        if(map.size()>1){
            try{
                Connection conn = SingleConnection.GetConnection();
                String request = "INSERT INTO component_load (component_id, components_load) VALUES ";
                for(int i=0; i<map.size(); i++){
                    if(i<map.size()-1){
                        request += "(?, ?), ";
                    }else{
                        request += "(?, ?);";
                    }
                }
                PreparedStatement stmt = conn.prepareStatement(request);

                int j = 0;
                for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    stmt.setInt(j*2 + 1, entry.getKey());
                    stmt.setInt(j*2 + 2, entry.getValue());
                    j++;
                }
                stmt.executeUpdate();
            }catch(SQLException e){
                System.out.println("SQL ERROR ! \n explains :" + e);
            }
        }
    }
    /**
     * Saves machine temp to the database 
     * 
     * @param map A hashmap containing the machine id (key) and machine temp (value)
     */
    public static void sendHashMapTemp(HashMap<Integer, Integer> map){
        if(map.size()>1){
            try{
                Connection conn = SingleConnection.GetConnection();
                String request = "INSERT INTO temperature (machine_id, temperature) VALUES ";
                for(int i=0; i<map.size(); i++){
                    if(i<map.size()-1){
                        request += "(?, ?), ";
                    }else{
                        request += "(?, ?);";
                    }
                }
                PreparedStatement stmt = conn.prepareStatement(request);

                int j = 0;
                for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    stmt.setInt(j*2 + 1, entry.getKey());
                    stmt.setInt(j*2 + 2, entry.getValue());
                    j++;
                }
                stmt.executeUpdate();
            }catch(SQLException e){
                System.out.println("SQL ERROR ! \n explains :" + e);
            }
        }
    }
}
