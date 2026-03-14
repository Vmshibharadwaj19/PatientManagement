package org.ptm.ptm;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ptm.ptm.dto.patientdto;
import org.ptm.ptm.dto.PatientRequestDto;
import org.ptm.ptm.service.PatientService;

import java.util.List;
import java.util.UUID;

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
        @GetMapping("/{id}")
        public ResponseEntity<patientdto> getPatientById(@PathVariable UUID id)
        {
           patientdto p= ps.updatePatient(id);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(p);
        }

        @PutMapping("/{id}")
    public ResponseEntity<patientdto> updatePatient(@PathVariable UUID id, @Valid @RequestBody PatientRequestDto p)
        {
           patientdto pd= ps.updatePatientByEmail(id, p);

              return ResponseEntity.status(HttpStatus.ACCEPTED).body(pd);
        }
}
