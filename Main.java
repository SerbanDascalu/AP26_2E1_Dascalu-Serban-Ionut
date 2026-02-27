public class Main
{
    public static void main(String[] args)
    {
        Location l1=new Location("Iasi","Romania",50.2);
        Location l2=new Location("Bucuresti","Romania", 245.3);

        Road r1=new Road("A2",l1,l2,500.4);
        System.out.println(l1);
        System.out.println(l2);
        System.out.println(r1);

    }
}