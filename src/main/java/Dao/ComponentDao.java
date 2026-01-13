package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import domain.Chassis;
import domain.Component;
import domain.Cpu;
import domain.Disk;
import domain.Gpu;
import domain.PowerSupply;
import domain.Ram;

/**
 * All SQL interactions related to the components 
 * 
 * @author FIGUEIRAS Jossua
 */
public abstract class ComponentDao {
    /**
     * Retrieving a component (in sql db) using it's ID
     * 
     * @param id The ID of the machine being searched 
     * 
     * @return The component found in the database
     */
    public static Component getComponentById(int id){
        try {
			Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT id, brand, model, machine_id, spec_value_primary, spec_value_secondary, type, status, ticket FROM components WHERE id = ?;");
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			
			if(result.next()) {
				switch (result.getString("type")) {
					case "CPU" : return new Cpu(result);
					case "GPU" : return new Gpu(result);
					case "RAM" : return new Ram(result);
					case "DISK" : return new Disk(result);
					case "Power_supply" : return new PowerSupply(result);
					case "Chassis" : return new Chassis(result);
					default : return null;
				}
			}
			return null;
        }catch(SQLException e){
            System.out.println("SQL ERROR ! /n explains :" + e);
        }
        return null;
    }
    /**
     * Retrieves all components from SQL
     * 
     * @return The ArrayList that stores all the components found in database
     */
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
                        compList.add(new Gpu(result));
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
    /**
     * Retrieves all components belonging to a machine passed 
     * as a parameter via the machine ID.
     * 
     * @param id the machine ID.
     * 
     * @return The ArrayList which stores all the components found in the database related to the machine
     */
    public static ArrayList<Component> getMachinesComponents(int id){
        ArrayList<Component> compList = new ArrayList<Component>();
        try {
			Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT id, brand, model, machine_id, spec_value_primary, spec_value_secondary, type, status, ticket FROM components WHERE machine_id = ?;");
            stmt.setInt(1, id);
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
    /**
     * Updates the component passed as a parameter in db 
     * 
     * @param c The Component to update
     */
    public static void update(Component c){
        try {
			Connection conn = SingleConnection.GetConnection();
			PreparedStatement stmt = conn.prepareStatement("UPDATE components SET brand = ?, model = ?, machine_id = ?, spec_value_primary = ?, spec_value_secondary = ?, type = ?, ticket = ? WHERE id = ?");
			stmt.setString(1, c.getBrand());
			stmt.setString(2, c.getModel());
            System.out.print("\n machine id = " + c.getMachineId() + "\n");
            if(c.getMachineId() == 0){
                stmt.setNull(3, Types.INTEGER);
            }else{
                stmt.setInt(3, c.getMachineId());
            }
            stmt.setInt(7, c.getTicketId());

            switch (c){
                case Chassis ch-> {
                    stmt.setInt(4, ch.getSizeU());
                    stmt.setNull(5, Types.INTEGER);
                    stmt.setString(6, "Chassis");
                }case Cpu cpu-> {
                    stmt.setInt(4, cpu.getNbCore());
                    stmt.setInt(5, cpu.getMaxRam());
                    stmt.setString(6, "Chassis");
                }case Disk disk-> {
                    stmt.setInt(4, disk.getSizeGo());
                    stmt.setNull(5, Types.INTEGER);
                    stmt.setString(6, "Chassis");
                }case Gpu gpu-> {
                    stmt.setInt(4, gpu.getVram());
                    stmt.setInt(5, gpu.getNbCore());
                    stmt.setString(6, "Chassis");
                }case PowerSupply ps-> {
                    stmt.setInt(4, ps.getPower());
                    stmt.setNull(5, Types.INTEGER);
                    stmt.setString(6, "Chassis");
                }case Ram ram-> {
                    stmt.setInt(4, ram.getSize_go());
                    stmt.setInt(5, ram.getVersion());
                    stmt.setString(6, "Chassis");
                }default -> throw new SQLException("Can't find type");
            }
            stmt.setInt(8, c.getId());
            stmt.executeUpdate();
        }catch (SQLException e) {
            System.out.println("SQL ERROR ! /n explains :" + e);
        }  
    }
}
