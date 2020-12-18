package kacper.example.epidemicsimulation;

public class SimulationParameters {
    private String N; //Simulation name
    private int P; // Population
    private int I; // infected
    private int R; // how many people are infected by one person
    private double M; // Mortality rate
    private int Ti; // days till recovery
    private int Tm; // days till death
    private int Ts; // simulation length in days

    public String getN() {
        return N;
    }

    public int getP() {
        return P;
    }

    public int getI() {
        return I;
    }

    public int getR() {
        return R;
    }

    public double getM() {
        return M;
    }

    public int getTi() {
        return Ti;
    }

    public int getTm() {
        return Tm;
    }

    public int getTs() {
        return Ts;
    }

    public SimulationParameters() {
        N = "S1";
        P = 1000000;
        I = 1;
        R = 2;
        M = 0.1; //Check it
        Ti = 7;
        Tm = 5;
        Ts = 100;
    }

    public SimulationParameters(String n, int p, int i, int r, double m, int ti, int tm, int ts) {
        N = n;
        P = p;
        I = i;
        R = r;
        M = m;
        Ti = ti;
        Tm = tm;
        Ts = ts;
    }

    @Override
    public String toString() {
        return "SimulationParameters{" +
                "N='" + N + '\'' +
                ", P=" + P +
                ", I=" + I +
                ", R=" + R +
                ", M=" + M +
                ", Ti=" + Ti +
                ", Tm=" + Tm +
                ", Ts=" + Ts +
                '}';
    }
}
