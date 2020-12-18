package kacper.example.epidemicsimulation;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RestController
public class WebController{

    private static final Logger logger = LoggerFactory.getLogger(WebController.class);
    private static int index = 0;
    private final SimulationService simulationService;
    private List<CompletableFuture<String>> futureList = new ArrayList<>();

    public WebController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity test() throws Exception {
        String result = simulationService.testMethod("ala123");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/simulation/start", method = RequestMethod.GET)
    public ResponseEntity startSimulation() throws Exception {
        int returnIndex = index;
        index++;
        futureList.add(simulationService.getSimulation());
        return new ResponseEntity<>(returnIndex, HttpStatus.OK);
    }

    @RequestMapping(value = "/simulation/result/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getSimulation(@PathVariable String id) throws Exception {
        if (!futureList.get(Integer.parseInt(id)).isDone()){
            return new ResponseEntity<>("IS NOT DONE", HttpStatus.PROCESSING);
        }
        String jsonString = futureList.get(Integer.parseInt(id)).get().toString();
        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }


    //TODO: parameters get from html body


}