package fr.dauphine.miage.msa.Webservice_project;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TauxChangeRepository extends JpaRepository<TauxChange, Long> {

    TauxChange findByDeviseSrcAndDeviseDestAndDate(String deviseSrc, String deviseDest, String date);
    List<TauxChange> findAll();
    TauxChange save(TauxChange tauxChange);

    @Override
    void deleteById(Long id);
}
