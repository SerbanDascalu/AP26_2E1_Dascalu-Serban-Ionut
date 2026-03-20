public class Main
{
    public static void main(String[] args)
    {
        City city = RandomCityGen.generateCity("RandomCity", 6);

        System.out.println(city);
        System.out.println();

        System.out.println("Intersections:");
        for (Intersection intersection : city.getIntersections())
        {
            System.out.println(intersection.getName() + " (" + intersection.getX() + ", " + intersection.getY() + ")");
        }

        System.out.println();
        System.out.println("Streets:");
        for (Street street : city.getStreets())
        {
            System.out.println(street);
        }

        System.out.println();
        System.out.println("Query streets:");
        for (Street street : city.queryStreets(20))
        {
            System.out.println(street);
        }

        System.out.println();
        MaintenanceCar planner = new MaintenanceCar(city);
        Route route = planner.findMaintenanceRoute(city.getIntersections().get(0));

        System.out.println("Maintenance route:");
        System.out.println(route);
    }
}