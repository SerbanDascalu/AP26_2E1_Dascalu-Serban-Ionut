public class Road
{
    private String roadName;
    private Location from;
    private Location to;
    private double dist;

    public Road(String roadName,Location from,Location to,double distance)
    {
        this.roadName=roadName;
        this.from=from;
        this.to=to;
        this.dist=distance;
    }

    public String getRoadName()
    {
        return roadName;
    }
    public void setRoadName(String roadName)
    {
        this.roadName=roadName;
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
    public double getDistance()

    {
        return dist;
    }
    public void setDistance(double distance)
    {
        this.dist=distance;
    }

    @Override
    public String toString()
    {
        return "Road{nume='"+roadName+"',from "+from.getNume()+",to "+to.getNume()+",distanta="+dist+"km}";
    }
}
