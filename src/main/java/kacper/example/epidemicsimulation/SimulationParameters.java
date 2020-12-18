package kacper.example.epidemicsimulation;

public class SimulationParameters {
    String N; //Simulation name
    int P; // Population
    int I; // infected
    int R; // how many people are infected by one person
    double M; // Mortality rate
    int Ti; // days till recovery
    int Tm; // days till death
    int Ts; // simulation length in days

    public SimulationParameters() {
        N = "S1";
        P = 1000000;
        I = 1000;
        R = 10;
        M = 0.1; //Check it
        Ti = 7;
        Tm = 10;
        Ts = 100;
    }
}
