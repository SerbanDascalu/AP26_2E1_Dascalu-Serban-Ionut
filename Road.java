import java.util.Objects;

public class Road
{
    private String roadName;
    private RoadTypes type;
    private Location from;
    private Location to;
    private double dist;

    public Road(String roadName,RoadTypes type,Location from,Location to,double dist)
    {
        this.roadName=roadName;
        this.type=type;
        this.from=from;
        this.to=to;
        this.dist=dist;
    }

    public String getRoadName()
    {
        return roadName;
    }

    public void setRoadName(String roadName)
    {
        this.roadName=roadName;
    }

    public RoadTypes getType()
    {
        return type;
    }

    public void setType(RoadTypes type)
    {
        this.type=type;
    }

    public Location getFrom()
    {
        return from;
    }

    public void setFrom(Location from)
    {
        this.from=from;
    }

    public Location getTo()
    {
        return to;
    }

    public void setTo(Location to)
    {
        this.to=to;
    }

    public double getDist()
    {
        return dist;
    }

    public void setDist(double dist)
    {
        this.dist=dist;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this==o)return true;
        if(o==null)return false;
        if(getClass()!=o.getClass())return false;

        Road other=(Road)o;

        return Double.compare(dist,other.dist)==0
                &&Objects.equals(roadName,other.roadName)
                &&type==other.type
                &&Objects.equals(from,other.from)
                &&Objects.equals(to,other.to);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(roadName,type,from,to,dist);
    }

    @Override
    public String toString()
    {
        String fromName=(from==null?"null":from.getNume());
        String toName=(to==null?"null":to.getNume());
        return "Road{nume='"+roadName+"',type="+type+",from="+fromName+",to="+toName+",dist="+dist+"km}";
    }
}