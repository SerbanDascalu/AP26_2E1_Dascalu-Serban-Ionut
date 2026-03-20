import java.util.Random;

public class RandomCityGen
{
    public static City generateCity(String name, int n)
    {
        City city = new City(name);
        Random random = new Random();

        Intersection[] intersections = new Intersection[n];

        for (int i = 0; i < n; i++)
        {
            int x = random.nextInt(100);
            int y = random.nextInt(100);
            intersections[i] = new Intersection("I" + (i + 1), x, y);
            city.addIntersection(intersections[i]);
        }

        int streetIndex = 1;

        for (int i = 0; i < n; i++)
        {
            for (int j = i + 1; j < n; j++)
            {
                int dx = intersections[i].getX() - intersections[j].getX();
                int dy = intersections[i].getY() - intersections[j].getY();
                int length = (int)Math.round(Math.sqrt(dx * dx + dy * dy));

                city.addStreet(new Street("S" + streetIndex, length, intersections[i], intersections[j]));
                streetIndex++;
            }
        }

        return city;
    }
}