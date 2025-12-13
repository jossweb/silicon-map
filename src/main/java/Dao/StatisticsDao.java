package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.HashMap;
import type.Tuple;
import java.sql.Timestamp;
import java.sql.SQLException;

public class StatisticsDao {
    public static HashMap<Integer, Tuple<Integer, LocalDateTime>> getRecentTemp(){
        System.out.print("\n Hello temp \n");
        try {
			Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT machine_id, temperature, date_time FROM ( SELECT machine_id, temperature, date_time, ROW_NUMBER() OVER (PARTITION BY machine_id ORDER BY date_time DESC) AS rn FROM temperature ) AS sub WHERE rn = 1;");
			ResultSet result = stmt.executeQuery();

            HashMap<Integer, Tuple<Integer, LocalDateTime>> temps = new HashMap<>();

            while(result.next()){
                Timestamp ts = result.getTimestamp("date_time");
                temps.put(result.getInt("machine_id"), new Tuple(result.getInt("temperature"), ts.toLocalDateTime()));
            }
            return temps;
        }catch(SQLException e) {
			System.out.println("SQL ERROR ! /n explains :" + e);
		}
        System.out.print("\nNULL\n");
        return null;
    }
    public static HashMap<Integer, Tuple<Integer, LocalDateTime>> getRecentLoad(){
        try {
			Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT component_id, components_load, date_time FROM ( SELECT component_id, components_load, date_time, ROW_NUMBER() OVER (PARTITION BY component_id ORDER BY date_time DESC) AS rn FROM component_load ) AS sub WHERE rn = 1;");
			ResultSet result = stmt.executeQuery();

            HashMap<Integer, Tuple<Integer, LocalDateTime>> temps = new HashMap<>();

            while(result.next()){
                Timestamp ts = result.getTimestamp("date_time");
                temps.put(result.getInt("component_id"), new Tuple(result.getInt("components_load"), ts.toLocalDateTime()));
            }
            return temps;
        }catch(SQLException e) {
			System.out.println("SQL ERROR ! /n explains :" + e);
		}
        return null;
    }
}
