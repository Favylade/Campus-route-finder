

import java.sql.*;
import java.util.*;

public class LocationFinder {

    public static Location getLocationByName(String name) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM locations WHERE name = ?"
            );
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Location(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBoolean("is_bus_stop")
                );
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public static Map<Location, Integer> getNearestBusStops(Location loc) {
        Map<Location, Integer> nearest = new HashMap<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT n.bus_stop_id, n.walking_distance, l.name, l.is_bus_stop " +
                "FROM nearest_bus_stops n " +
                "JOIN locations l ON n.bus_stop_id = l.id " +
                "WHERE n.location_id = ?"
            );
            stmt.setInt(1, loc.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Location busStop = new Location(
                    rs.getInt("bus_stop_id"),
                    rs.getString("name"),
                    rs.getBoolean("is_bus_stop")
                );
                nearest.put(busStop, rs.getInt("walking_distance"));
            }
        } catch (SQLException e) {
        }
        return nearest;
    }
}
