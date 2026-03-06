//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    public static void main(String[] args)
    {
        ProblemInstance inst=new ProblemInstance();

        City cluj=new City("Cluj","Romania",350,300000);
        City iasi=new City("Iasi","Romania",100,320000);
        Airport otp=new Airport("OTP","Romania",90,2);
        GasStation omv=new GasStation("OMV A1","Romania",120,7.35);

        System.out.println("Add locatii:");
        System.out.println(inst.addLocation(cluj));
        System.out.println(inst.addLocation(iasi));
        System.out.println(inst.addLocation(otp));
        System.out.println(inst.addLocation(omv));

        System.out.println("Add duplicate:");
        System.out.println(inst.addLocation(new City("Cluj","Romania",350,300000)));

        Road r1=new Road("A3",RoadTypes.HIGHWAY,cluj,iasi,450.5);
        Road r2=new Road("DN1",RoadTypes.NATIONAL,cluj,otp,420.0);
        Road r3=new Road("A1",RoadTypes.HIGHWAY,omv,cluj,12.7);

        System.out.println("Add drumuri:");
        System.out.println(inst.addRoad(r1));
        System.out.println(inst.addRoad(r2));
        System.out.println(inst.addRoad(r3));

        System.out.println("Add duplicate drum (r1 copy):");
        System.out.println(inst.addRoad(new Road("A3",RoadTypes.HIGHWAY,cluj,iasi,450.5)));

        System.out.println("Locatii:");
        for(Location l:inst.getLocatii())
        {
            System.out.println(l);
        }

        System.out.println("Drumuri:");
        for(Road r:inst.getDrumuri())
        {
            System.out.println(r);
        }

        System.out.println("Valid? "+inst.eValid());
        System.out.println("Can reach Cluj->Iasi? "+inst.canReach(cluj,iasi));
        System.out.println("Can reach Iasi->OMV? "+inst.canReach(iasi,omv));
    }
}