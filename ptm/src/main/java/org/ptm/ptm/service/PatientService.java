package org.ptm.ptm.service;

import org.ptm.ptm.Repository.PatientRepo;
import org.ptm.ptm.dto.patientdto;
import org.ptm.ptm.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.ptm.ptm.mapper.PatientMapper;
import org.ptm.ptm.dto.patientdto;
import org.ptm.ptm.dto.PatientRequestDto;


@Service
public class PatientService {

    private PatientRepo patientRepo;

    PatientService(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }
    public List<patientdto>patientRepoList(){
        List<Patient>  patients=patientRepo.findAll();
        List<patientdto>patientdtos=new ArrayList<>();
        for(Patient p:patients){
            patientdtos.add(PatientMapper.patientMapper(p));
        }

        return patientdtos;

    }
   public patientdto createPatient(PatientRequestDto pp)
   {    if(patientRepo.existsByEmail(pp.getEmail()))
   {
        throw new EmailAlreadyExists("Email already exists");
   }
          Patient newPatient=patientRepo.save(PatientMapper.toPatient(pp));

          return PatientMapper.patientMapper(newPatient);

   }

}
