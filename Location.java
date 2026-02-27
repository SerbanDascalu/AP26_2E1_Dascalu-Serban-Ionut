//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Location
{
    private String nume;
    private String tara;
    private double altitudine;

    public Location(String nume,String tara,double altitudine)
    {
        this.nume=nume;
        this.tara=tara;
        this.altitudine=altitudine;
    }
    public void setNume(String nume)
    {
        this.nume=nume;
    }
    public String getNume()
    {
        return nume;
    }

    public void setTara(String tara)
    {
        this.tara=tara;
    }
    public String getTara()
    {
        return tara;
    }

    public void setAltitudine(double altitudine)
    {
        this.altitudine=altitudine;
    }
    public double getAltitudine()
    {
        return altitudine;
    }


    @Override
    public String toString()
    {
        return "Location{numele este'"+nume+"',tara este'"+tara+"',altitudine="+altitudine+"m}";
    }
}


