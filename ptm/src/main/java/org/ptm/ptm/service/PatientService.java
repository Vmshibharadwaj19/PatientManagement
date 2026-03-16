package org.ptm.ptm.service;

import jakarta.transaction.Transactional;
import org.ptm.ptm.Exception.EmailAlreadyExists;
import org.ptm.ptm.Exception.PatientNotFoundException;
import org.ptm.ptm.Repository.PatientRepo;
import org.ptm.ptm.dto.patientdto;
import org.ptm.ptm.grpc.BillingServiceGrpcClient;
import org.ptm.ptm.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.ptm.ptm.mapper.PatientMapper;
import org.ptm.ptm.dto.patientdto;
import org.ptm.ptm.dto.PatientRequestDto;


@Service
public class PatientService {

    private PatientRepo patientRepo;
    private final BillingServiceGrpcClient billingServiceGrpcClient;

    PatientService(PatientRepo patientRepo,BillingServiceGrpcClient billingServiceGrpcClient) {
        this.patientRepo = patientRepo;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
    }
    public List<patientdto>patientRepoList(){
        List<Patient>  patients=patientRepo.findAll();
        List<patientdto>patientdtos=new ArrayList<>();
        for(Patient p:patients){
            patientdtos.add(PatientMapper.patientMapper(p));
        }

        return patientdtos;

    }
//   public patientdto createPatient(PatientRequestDto pp)
//   {    if(patientRepo.existsByEmail(pp.getEmail()))
//   {
//        throw new EmailAlreadyExists("Email already exists");
//   }
//          Patient newPatient=patientRepo.save(PatientMapper.toPatient(pp));
//       billingServiceGrpcClient.createBillingAccount(newPatient.getId().toString(),newPatient.getName(),newPatient.getEmail());
//          return PatientMapper.patientMapper(newPatient);
//
//   }
public patientdto createPatient(PatientRequestDto pp) {

    if (patientRepo.existsByEmail(pp.getEmail())) {
        throw new EmailAlreadyExists("Email already exists");
    }

    Patient newPatient = patientRepo.save(PatientMapper.toPatient(pp));

    try {
        billingServiceGrpcClient.createBillingAccount(
                newPatient.getId().toString(),
                newPatient.getName(),
                newPatient.getEmail());
    } catch (Exception e) {
        System.out.println("Billing service failed: " + e.getMessage());
    }

    return PatientMapper.patientMapper(newPatient);
}
   public  patientdto updatePatient(UUID id)
   {
       Patient patient=patientRepo.findById(id).orElseThrow(() -> new PatientNotFoundException());
       patientdto patientdto=PatientMapper.patientMapper(patient);

       return patientdto;
   }

   public patientdto updatePatientByEmail(UUID id,PatientRequestDto p)
   {
       Patient patient=patientRepo.findById(id).orElseThrow(() -> new PatientNotFoundException());
       //if(patient.getId()!=id  && patientRepo.existsByEmail(p.getEmail()))
       if(patientRepo.existsByEmailAndIdNot(p.getEmail(),id))
       {
           throw new EmailAlreadyExists("Email already exists");
       }
           patientRepo.save(PatientMapper.updateMapper(patient, p));
           patientdto dto = PatientMapper.patientMapper(patient);
       return dto;
       //if(patientRepo.existsByEmailAndIdNot(p.getEmail(),id))

   }

    @Transactional
    public void deletePatient(String email) {

        long deleted = patientRepo.deleteByEmail(email);

        if (deleted == 0) {
            throw new PatientNotFoundException();
        }
    }


}
