package kacper.example.epidemicsimulation;

public class SimulationData {
    private int day;
    private int Pi; //infected
    private int Pv; //healthy
    private int Pm; //deaths
    private int Pr; // recovered
    private int P;

    public SimulationData(SimulationParameters simulationParameters) {
        day = 0;
        Pi = simulationParameters.getI();
        Pv = simulationParameters.getP() - Pi;
        Pm = 0;
        Pr = 0;
        P = Pi + Pv + Pm + Pr;
    }

    public SimulationData(int day, int pi, int pv, int pm, int pr) {
        this.day = day;
        Pi = pi;
        Pv = pv;
        Pm = pm;
        Pr = pr;
        P = Pi + Pv + Pm + Pr;
    }

    @Override
    public String toString() {
        return "SimulationData{" +
                "day=" + day +
                ", Pi=" + Pi +
                ", Pv=" + Pv +
                ", Pm=" + Pm +
                ", Pr=" + Pr +
                '}';
    }

    public int getDay() {
        return day;
    }

    public int getPi() {
        return Pi;
    }

    public int getPv() {
        return Pv;
    }

    public int getPm() {
        return Pm;
    }

    public int getPr() {
        return Pr;
    }
}
