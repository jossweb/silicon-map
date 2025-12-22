package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import type.Tuple;
import java.sql.Timestamp;
import java.sql.SQLException;

public abstract class ContextDao {
    public static HashMap<Integer, ArrayList<Tuple<Integer, LocalDateTime>>> getRecentTemp(){
        try {
			Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT machine_id, temperature, date_time FROM ( SELECT machine_id, temperature, date_time, ROW_NUMBER() OVER ( PARTITION BY machine_id ORDER BY date_time DESC ) AS rn FROM temperature ) AS sub WHERE rn <= 10 ORDER BY machine_id, date_time DESC;");
			ResultSet result = stmt.executeQuery();

            HashMap<Integer, ArrayList<Tuple<Integer, LocalDateTime>>> temps = new HashMap<>();

            while (result.next()) {
                int machineId = result.getInt("machine_id");
                int temperature = result.getInt("temperature");

                LocalDateTime dateTime = result.getTimestamp("date_time").toLocalDateTime();

                temps.computeIfAbsent(machineId, e-> new ArrayList<>())
                    .add(new Tuple<>(temperature, dateTime));
                }
            return temps;

        } catch (SQLException e) {
            System.out.println("SQL ERROR !\nexplains: " + e);
        }

        return new HashMap<>();
    }
    public static HashMap<Integer, Tuple<Integer, LocalDateTime>> getRecentLoad(){
        try {
			Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT component_id, components_load, date_time FROM ( SELECT component_id, components_load, date_time, ROW_NUMBER() OVER (PARTITION BY component_id ORDER BY date_time DESC) AS rn FROM component_load ) AS sub WHERE rn = 1;");
			ResultSet result = stmt.executeQuery();

            HashMap<Integer, Tuple<Integer, LocalDateTime>> temps = new HashMap<>();

            while(result.next()){
                Timestamp ts = result.getTimestamp("date_time");
                temps.put(result.getInt("component_id"), new Tuple<Integer, LocalDateTime>(result.getInt("components_load"), ts.toLocalDateTime()));
            }
            return temps;
        }catch(SQLException e) {
			System.out.println("SQL ERROR ! /n explains :" + e);
		}
        return null;
    }
}
