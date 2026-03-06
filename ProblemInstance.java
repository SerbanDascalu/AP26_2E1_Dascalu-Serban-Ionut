import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

public class ProblemInstance
{
    private final Set<Location> locatii=new HashSet<>();
    private final Set<Road> drumuri=new HashSet<>();

    public boolean addLocation(Location l)
    {
        if(l==null)return false;
        return locatii.add(l);
    }

    public boolean addRoad(Road r)
    {
        if(r==null)return false;
        return drumuri.add(r);
    }

    public Set<Location> getLocatii()
    {
        return locatii;
    }

    public Set<Road> getDrumuri()
    {
        return drumuri;
    }

    public boolean eValid()
    {
        for(Road r:drumuri)
        {
            if(r==null)return false;
            if(r.getFrom()==null||r.getTo()==null)return false;
            if(r.getType()==null)return false;
            if(r.getDist()<=0)return false;
            if(!locatii.contains(r.getFrom()))return false;
            if(!locatii.contains(r.getTo()))return false;
        }
        return true;
    }

    public boolean canReach(Location start,Location target)
    {
        if(start==null||target==null)return false;
        if(!locatii.contains(start)||!locatii.contains(target))return false;
        if(start.equals(target))return true;
        if(!eValid())return false;

        Set<Location> viz=new HashSet<>();
        ArrayDeque<Location> q=new ArrayDeque<>();

        viz.add(start);
        q.add(start);

        while(!q.isEmpty())
        {
            Location cure=q.removeFirst();

            for(Road r:drumuri)
            {
                Location next=null;

                if(cure.equals(r.getFrom()))
                    next=r.getTo();
                else
                    if(cure.equals(r.getTo()))
                     next=r.getFrom();

                if(next!=null&&!viz.contains(next))
                {
                    if(next.equals(target))return true;
                    viz.add(next);
                    q.add(next);
                }
            }
        }

        return false;
    }
}