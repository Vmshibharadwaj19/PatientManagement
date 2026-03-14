package org.ptm.ptm;

import jakarta.validation.Valid;
import org.ptm.ptm.model.Patient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ptm.ptm.dto.patientdto;
import org.ptm.ptm.dto.PatientRequestDto;
import org.ptm.ptm.service.PatientService;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class ParientController {

     private PatientService ps;
     public ParientController(PatientService ps) {
        this.ps = ps;
     }
    @GetMapping
    public ResponseEntity<List<patientdto>> getPatients() {

         return ResponseEntity.ok().body(ps.patientRepoList());
    }

    @PostMapping("/createPatient")
    public ResponseEntity<patientdto> createPatient(@Valid @RequestBody PatientRequestDto p) {

         patientdto pa= ps.createPatient(p);

         return ResponseEntity.status(HttpStatus.CREATED).body( pa);

    }
}
