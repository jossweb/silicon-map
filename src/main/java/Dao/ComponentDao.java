package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Chassis;
import domain.Component;
import domain.Cpu;
import domain.Disk;
import domain.PowerSupply;
import domain.Ram;

public abstract class ComponentDao {
    public static ArrayList<Component> getAllComponents(){
        ArrayList<Component> compList = new ArrayList<Component>();
        try {
			Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT id, brand, model, machine_id, spec_value_primary, spec_value_secondary, type, status, ticket FROM components;");
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                switch (result.getString("type")) {
                    case "CPU":
                        compList.add(new Cpu(result));
                        break;
                    case "RAM":
                        compList.add(new Ram(result));
                        break;
                    case "DISK":
                        compList.add(new Disk(result));
                        break;
                    case "Power_supply":
                        compList.add(new PowerSupply(result));
                        break;
                    case "Chassis":
                        compList.add(new Chassis(result));
                        break;
                    case "GPU":
                        compList.add(new Chassis(result));
                        break;
                    default:
                        break;
                }
            }

        }catch(SQLException e){
            System.out.println("SQL ERROR ! /n explains :" + e);
        }
        return compList;
        
    }

}
