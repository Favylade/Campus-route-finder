import java.util.Map;

public class RouteFinder {

    public static String findRoute(Location current, Location destination) {
        StringBuilder response = new StringBuilder();

        // 1️⃣ Both current and destination are bus stops → direct route
        if (current.isBusStop() && destination.isBusStop()) {
            response.append(" Enter a direct direct bus from ")
                    .append(current.getName())
                    .append(" to ")
                    .append(destination.getName())
                    .append(".");
            return response.toString();
        }

        // 2️⃣ Current is a bus stop, destination is NOT
        if (current.isBusStop() && !destination.isBusStop()) {
            Map<Location, Integer> nearestStops = LocationFinder.getNearestBusStops(destination);
            if (nearestStops.isEmpty()) {
                response.append("️No nearby bus stops found for ")
                        .append(destination.getName())
                        .append(".");
            } else {
                Map.Entry<Location, Integer> closest = nearestStops.entrySet().iterator().next();
                int minutes = closest.getValue();

                response.append("️There’s no bus stop at ")
                        .append(destination.getName())
                        .append(".\n");
                response.append("Enter a bus from ")
                        .append(current.getName())
                        .append(" to ")
                        .append(closest.getKey().getName())
                        .append(" and then walk ≈ ")
                        .append(minutes)
                        .append(" mins to your destination.");
            }
            return response.toString();
        }

        // 3️⃣ Current is NOT a bus stop, destination is a bus stop
        if (!current.isBusStop() && destination.isBusStop()) {
            Map<Location, Integer> nearestStops = LocationFinder.getNearestBusStops(current);
            if (nearestStops.isEmpty()) {
                response.append("️No nearby bus stops found for ")
                        .append(current.getName())
                        .append(".");
            } else {
                Map.Entry<Location, Integer> closest = nearestStops.entrySet().iterator().next();
                int minutes = closest.getValue();

                response.append("️There’s no bus stop at ")
                        .append(current.getName())
                        .append(".\n");
                response.append("Trek ≈ ")
                        .append(minutes)
                        .append("mins to ")
                        .append(closest.getKey().getName())
                        .append("and then take a direct bus to ")
                        .append(destination.getName())
                        .append(".");
            }
            return response.toString();
        }

        // 4️⃣ Both current and destination are NOT bus stops
        if (!current.isBusStop() && !destination.isBusStop()) {
            Map<Location, Integer> nearestCurrent = LocationFinder.getNearestBusStops(current);
            Map<Location, Integer> nearestDest = LocationFinder.getNearestBusStops(destination);

            if (nearestCurrent.isEmpty()) {
                response.append("️No nearby bus stops found for ")
                        .append(current.getName())
                        .append(".\n");
            }
            if (nearestDest.isEmpty()) {
                response.append("️No nearby bus stops found for ")
                        .append(destination.getName())
                        .append(".\n");
            }

            if (!nearestCurrent.isEmpty() && !nearestDest.isEmpty()) {
                Map.Entry<Location, Integer> closestCurrent = nearestCurrent.entrySet().iterator().next();
                int walkToCurrentStop = closestCurrent.getValue();

                Map.Entry<Location, Integer> closestDest = nearestDest.entrySet().iterator().next();
                int walkFromDestStop = closestDest.getValue();

                response.append("️There’s no bus stop at ")
                        .append(current.getName())
                        .append(".\n");
                response.append("Trek ≈ ")
                        .append(walkToCurrentStop)
                        .append("mins to ")
                        .append(closestCurrent.getKey().getName())
                        .append(".\n");
                response.append("Take a direct bus to ")
                        .append(closestDest.getKey().getName())
                        .append(".\n");
                response.append("Then walk ≈ ")
                        .append(walkFromDestStop)
                        .append("mins to your destination.");
            }

            return response.toString();
        }

        return response.toString();
    }
}
