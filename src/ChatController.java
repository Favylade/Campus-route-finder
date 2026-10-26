
import java.sql.*;

public class ChatController {

    private enum ChatState {
        ASK_NAME, ASK_DESTINATION, ASK_CURRENT, SHOW_ROUTE
    }
    private ChatState state = ChatState.ASK_NAME;

    private String username;
    private Location destination;
    private Location current;

    public String handleUserInput(String input) {
        switch (state) {
            case ASK_NAME -> {
                username = input.trim();
                boolean exists = userExists(username);
                if (!exists) {
                    saveUser(username);
                    state = ChatState.ASK_DESTINATION;
                    return "Hi " + username + "! 👋 Where are you going today?";
                } else {
                    state = ChatState.ASK_DESTINATION;
                    return "Welcome back, " + username + "! 👋 Where are you going today?";
                }
            }
            case ASK_DESTINATION -> {
                destination = LocationFinder.getLocationByName(input.trim());
                if (destination == null) {
                    return "Sorry, I couldn’t find that location.";
                }
                state = ChatState.ASK_CURRENT;
                return "Got it! And where are you right now?";
            }
            case ASK_CURRENT -> {
                current = LocationFinder.getLocationByName(input.trim());
                if (current == null) {
                    return "Sorry, I couldn’t find that current location.";
                }
                state = ChatState.SHOW_ROUTE;
                return RouteFinder.findRoute(current, destination);
            }
            default -> {
                state = ChatState.ASK_DESTINATION;
                return "Would you like to find another route?";
            }
        }
    }

    private boolean userExists(String name) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM users WHERE name = ?"
            );
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
        }
        return false;
    }

    private void saveUser(String name) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO users (name) VALUES (?)"
            );
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
