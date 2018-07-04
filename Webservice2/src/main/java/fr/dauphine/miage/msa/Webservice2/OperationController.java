package fr.dauphine.miage.msa.Webservice2;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class OperationController {

    @Autowired
    private OperationChangeRepository repository;

    @GetMapping("/operation-change/source/{source}/dest/{dest}/date/{date}")
    public Optional<OperationChange> retrouveOperationChange
            (@PathVariable String source, @PathVariable String dest , @PathVariable String date) {

        Optional<OperationChange> operationChange = Optional.of(repository.findByDeviseSrcAndDeviseDestAndDate(source, dest, date));

        // java.util.function.Supplier<TauxNotFoundException> s = () -> new TauxNotFoundException("taux not found","ok", "ok");
        return operationChange;
        //.orElseThrow(() -> new TauxNotFoundException("taux not found",source, dest));
    }


    @GetMapping("/operation-change/Liste")
    public List<OperationChange> listeOperationChange
            () {
        List<OperationChange> operationChange = repository.findAll();
        return operationChange;
    }

    @GetMapping("/operation-change/insert/source/{source}/dest/{dest}/montant/{montant}")
    public OperationChange insertOperationChange
            (@PathVariable String source, @PathVariable String dest, @PathVariable double montant) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        OperationChange oC = new OperationChange();
        oC.setDeviseDest(dest);
        oC.setDeviseSrc(source);
        oC.setMontant(montant);
        oC.setDate(df.format(new Date()));

RestTemplate restTemplate = new RestTemplate();
    Map<String,String> param = new HashMap<String,String>();
    param.put("source",source);
        param.put("dest",dest);
        param.put("date",df.format(new Date()));


        TauxChange tC = restTemplate.getForObject("http://localhost:8000/devise-change/source/{source}/dest/{dest}/date/{date}",TauxChange.class,param);
        System.out.println(tC);

        oC.setTaux(tC.getTaux());

        OperationChange operationChange = repository.save(oC);
        return operationChange;
    }

    @GetMapping("/operation-change/delete/{id}")
    public void deleteOperationChange
            (@PathVariable Long id) {

        repository.deleteById(id);

    }

    @GetMapping("/operation-change/modif/{source}/{dest}/{date}/{newmontant}")
    public void modifOperationChange
            (@PathVariable String source, @PathVariable String dest, @PathVariable String date, @PathVariable double newmontant) {
        Optional<OperationChange> operationChange = Optional.of(repository.findByDeviseSrcAndDeviseDestAndDate(source,dest,date));
        if(operationChange.isPresent()){
            OperationChange oC1= operationChange.get();
            oC1.setMontant(newmontant);
            repository.save(oC1);}

    }

    @GetMapping("" +
            "/{source}/{dest}/{montant}")
    public double SimulOperationChange
            (@PathVariable String source, @PathVariable String dest, @PathVariable double montant)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        RestTemplate restTemplate = new RestTemplate();
        Map<String,String> param = new HashMap<String,String>();
        param.put("source",source);
        param.put("dest",dest);
        param.put("date",df.format(new Date()));


        TauxChange tC = restTemplate.getForObject("http://localhost:8000/devise-change/source/{source}/dest/{dest}/date/{date}",TauxChange.class,param);

        return montant*tC.getTaux();



    }
}
