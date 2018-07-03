package fr.dauphine.miage.msa.Webservice2;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationChangeRepository extends JpaRepository<OperationChange, Long> {

    OperationChange findByDeviseSrcAndDeviseDestAndDate(String deviseSrc, String deviseDest, String date);
    List<OperationChange> findAll();
    OperationChange save(OperationChange OperationChange);

    @Override
    void deleteById(Long id);
}
