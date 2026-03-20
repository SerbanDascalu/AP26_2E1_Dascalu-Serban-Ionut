import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class City
{
    private String name;
    private List<Intersection> intersections;
    private List<Street> streets;

    public City(String name)
    {
        this.name = name;
        intersections = new ArrayList<>();
        streets = new ArrayList<>();
    }

    public void addIntersection(Intersection intersection)
    {
        intersections.add(intersection);
    }

    public void addStreet(Street street)
    {
        streets.add(street);
    }

    public List<Intersection> getIntersections()
    {
        return intersections;
    }

    public List<Street> getStreets()
    {
        return streets;
    }

    public int getDistance(Intersection a, Intersection b)
    {
        for (Street street : streets)
        {
            if ((street.getFirst() == a && street.getSecond() == b) ||
                    (street.getFirst() == b && street.getSecond() == a))
            {
                return street.getLength();
            }
        }

        return Integer.MAX_VALUE;
    }

    public List<Street> queryStreets(int minLength)
    {
        Map<Intersection, Long> count = streets.stream()
                .flatMap(street -> List.of(street.getFirst(), street.getSecond()).stream())
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()));

        return streets.stream()
                .filter(street -> street.getLength() > minLength)
                .filter(street -> count.get(street.getFirst()) >= 3 || count.get(street.getSecond()) >= 3)
                .toList();
    }

    public List<Route> findRoutes(Intersection start, Intersection target, int howMany)
    {
        List<Route> routes = new ArrayList<>();
        List<Intersection> currentPath = new ArrayList<>();
        Set<Intersection> visited = new HashSet<>();

        dfs(start, target, visited, currentPath, 0, routes);

        routes.sort((a, b) -> a.getCost() - b.getCost());

        if (routes.size() > howMany)
        {
            return new ArrayList<>(routes.subList(0, howMany));
        }

        return routes;
    }

    private void dfs(Intersection current, Intersection target, Set<Intersection> visited, List<Intersection> currentPath, int currentCost, List<Route> routes)
    {
        visited.add(current);
        currentPath.add(current);

        if (current == target)
        {
            routes.add(new Route(new ArrayList<>(currentPath), currentCost));
        }
        else
        {
            for (Street street : streets)
            {
                Intersection next = null;

                if (street.getFirst() == current)
                {
                    next = street.getSecond();
                }
                else if (street.getSecond() == current)
                {
                    next = street.getFirst();
                }

                if (next != null && !visited.contains(next))
                {
                    dfs(next, target, visited, currentPath, currentCost + street.getLength(), routes);
                }
            }
        }

        currentPath.remove(currentPath.size() - 1);
        visited.remove(current);
    }

    @Override
    public String toString()
    {
        return "City: " + name;
    }
}