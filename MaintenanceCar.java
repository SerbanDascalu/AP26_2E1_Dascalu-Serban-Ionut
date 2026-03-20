import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MaintenanceCar
{
    private City city;

    public MaintenanceCar(City city)
    {
        this.city = city;
    }

    public Route findMaintenanceRoute(Intersection start)
    {
        List<Intersection> nodes = city.getIntersections();
        int n = nodes.size();

        if (n == 0)
        {
            return new Route(new ArrayList<>(), 0);
        }

        List<int[]> mstEdges = buildMST();
        List<List<Intersection>> tree = buildTree(mstEdges);

        List<Intersection> order = new ArrayList<>();
        Set<Intersection> visited = new HashSet<>();

        dfsPreorder(start, tree, visited, order);

        if (!order.isEmpty() && order.get(order.size() - 1) != start)
        {
            order.add(start);
        }

        int totalCost = 0;
        for (int i = 0; i < order.size() - 1; i++)
        {
            totalCost += city.getDistance(order.get(i), order.get(i + 1));
        }

        return new Route(order, totalCost);
    }

    private List<int[]> buildMST()
    {
        List<Intersection> nodes = city.getIntersections();
        int n = nodes.size();

        boolean[] used = new boolean[n];
        int[] minDist = new int[n];
        int[] parent = new int[n];

        for (int i = 0; i < n; i++)
        {
            minDist[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }

        minDist[0] = 0;

        for (int step = 0; step < n; step++)
        {
            int u = -1;

            for (int i = 0; i < n; i++)
            {
                if (!used[i] && (u == -1 || minDist[i] < minDist[u]))
                {
                    u = i;
                }
            }

            used[u] = true;

            for (int v = 0; v < n; v++)
            {
                if (!used[v])
                {
                    int dist = city.getDistance(nodes.get(u), nodes.get(v));
                    if (dist < minDist[v])
                    {
                        minDist[v] = dist;
                        parent[v] = u;
                    }
                }
            }
        }

        List<int[]> edges = new ArrayList<>();

        for (int i = 1; i < n; i++)
        {
            edges.add(new int[]{parent[i], i});
        }

        return edges;
    }

    private List<List<Intersection>> buildTree(List<int[]> mstEdges)
    {
        List<Intersection> nodes = city.getIntersections();
        List<List<Intersection>> tree = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++)
        {
            tree.add(new ArrayList<>());
        }

        for (int[] edge : mstEdges)
        {
            int a = edge[0];
            int b = edge[1];

            tree.get(a).add(nodes.get(b));
            tree.get(b).add(nodes.get(a));
        }

        return tree;
    }

    private void dfsPreorder(Intersection current, List<List<Intersection>> tree, Set<Intersection> visited, List<Intersection> order)
    {
        visited.add(current);
        order.add(current);

        int index = city.getIntersections().indexOf(current);

        for (Intersection next : tree.get(index))
        {
            if (!visited.contains(next))
            {
                dfsPreorder(next, tree, visited, order);
            }
        }
    }
}