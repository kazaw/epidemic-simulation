package kacper.example.epidemicsimulation;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
public class SimulationService {

    private static final Logger logger = LoggerFactory.getLogger(SimulationService.class);
    private final SimulationParameters simulationParameters = new SimulationParameters();

    public SimulationService() {
    }

    public String testMethod(String s){
        return s + s;
    }

    @Async("webExecutor")
    public CompletableFuture<String> getSimulation() throws Exception{
        try{
            SimulationData initialSimulationData = new SimulationData(simulationParameters);
            logger.info("Starting new simulation with parameters:\n" + simulationParameters.toString());
            List<SimulationData> simulationDataList = new ArrayList<>();
            List<Integer> infectedList = new ArrayList<>();
            simulationDataList.add(initialSimulationData);
            infectedList.add(simulationParameters.getI());
            for (int i = 1; i <= simulationParameters.getTs(); i++){
                int pi = simulationDataList.get(i-1).getPi();
                int pv = simulationDataList.get(i-1).getPv();
                int pm = simulationDataList.get(i-1).getPm();
                int pr = simulationDataList.get(i-1).getPr();
                if (i >= simulationParameters.getTm()){ //calculate new Deaths
                    //int deaths = calculateDeaths(simulationDataList.get(i - simulationParameters.getTm()).getPi());
                    int deaths = calculateDeaths(infectedList.get(i - simulationParameters.getTm()));
                    pi -= deaths;
                    pm += deaths;
                    infectedList.set(i - simulationParameters.getTm(), infectedList.get(i - simulationParameters.getTm()) - deaths);

                    //Check if death/infected < total population
                }
                if (i >= simulationParameters.getTi()){ //calculate new recovered
                    //int recovered = simulationDataList.get(i - simulationParameters.getTi()).getPi();
                    int recovered = infectedList.get(i - simulationParameters.getTi());
                    pi -= recovered;
                    pr += recovered;
                }
                //add random
                int newInfected = 0;
                if(pv > 0){
                    newInfected = calculateNewInfected(pi);
                    if(pv - newInfected < 0){
                        newInfected = pv;
                        pv = 0;
                    }
                    else{
                        pv -= newInfected;
                    }
                    pi += newInfected;
                }
                infectedList.add(newInfected);
                simulationDataList.add(new SimulationData(i, pi,pv,pm,pr));
                //logger.info(simulationDataList.get(i).toString());
            }
            String jsonString = new Gson().toJson(simulationDataList);
            return CompletableFuture.completedFuture(jsonString);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    private int calculateDeaths(int infected){
        int result = (int) Math.round(simulationParameters.getM() * infected);
        return result;
    }

    private int calculateNewInfected(int infected){
        int result = (int) Math.round(simulationParameters.getR() * infected);
        return result;
    }


}
