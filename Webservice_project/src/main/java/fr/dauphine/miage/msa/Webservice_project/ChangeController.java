package fr.dauphine.miage.msa.Webservice_project;

import fr.dauphine.miage.msa.Webservice_project.Exception.TauxNotFoundException;
import javassist.NotFoundException;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController

public class ChangeController {
    //@Autowired
    //private Environment environment;
    @Autowired
    private TauxChangeRepository repository;

    @GetMapping("/devise-change/source/{source}/dest/{dest}/date/{date}")
    public Optional<TauxChange> retrouveTauxChange
            (@PathVariable String source, @PathVariable String dest , @PathVariable String date) {

        Optional<TauxChange> tauxChange = Optional.of(repository.findByDeviseSrcAndDeviseDestAndDate(source, dest, date));

       // java.util.function.Supplier<TauxNotFoundException> s = () -> new TauxNotFoundException("taux not found","ok", "ok");
        return tauxChange;
                //.orElseThrow(() -> new TauxNotFoundException("taux not found",source, dest));
    }


    @GetMapping("/devise-change/Liste")
    public List<TauxChange> listeTauxChange
            () {
        List<TauxChange> tauxChange = repository.findAll();
        return tauxChange;
    }

    @GetMapping("/devise-change/insert/source/{source}/dest/{dest}/taux/{taux}")
    public TauxChange insertTauxChange
            (@PathVariable String source, @PathVariable String dest, @PathVariable double taux) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        TauxChange tC = new TauxChange();
        tC.setDeviseDest(dest);
        tC.setDeviseSrc(source);
        tC.setDate(df.format(new Date()));
        tC.setTaux(taux);
        TauxChange tauxChange = repository.save(tC);
        return tauxChange;
    }

    @GetMapping("/devise-change/delete/{id}")
    public String deleteTauxChange
            (@PathVariable Long id) {

             repository.deleteById(id);
        String result="row successfully deleted";
        return result;
        }

    @GetMapping("/devise-change/modif/{source}/{dest}/{date}/{newtaux}")
    public void modifTauxchange
            (@PathVariable String source, @PathVariable String dest, @PathVariable String date, @PathVariable double newtaux) {
        Optional<TauxChange> tauxChange = Optional.of(repository.findByDeviseSrcAndDeviseDestAndDate(source,dest,date));
        if(tauxChange.isPresent()){
          TauxChange tC1= tauxChange.get();
          tC1.setTaux(newtaux);
        repository.save(tC1);}

    }




}
