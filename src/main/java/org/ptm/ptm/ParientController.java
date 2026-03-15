package org.ptm.ptm;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="Patient",description = "Api For Managing Patients")
public class ParientController {

     private PatientService ps;
     public ParientController(PatientService ps) {
        this.ps = ps;
     }
    @GetMapping
    @Operation(summary = "Get Patients")
    public ResponseEntity<List<patientdto>> getPatients() {

         return ResponseEntity.ok().body(ps.patientRepoList());
    }

    @PostMapping("/createPatient")
    @Operation(summary = "Create Patient")
    public ResponseEntity<patientdto> createPatient(@Valid @RequestBody PatientRequestDto p) {

         patientdto pa= ps.createPatient(p);

         return ResponseEntity.status(HttpStatus.CREATED).body( pa);

    }
        @GetMapping("/{id}")
        @Operation(summary="Fetch a patient")
        public ResponseEntity<patientdto> getPatientById(@PathVariable UUID id)
        {
           patientdto p= ps.updatePatient(id);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(p);
        }

        @PutMapping("/{id}")
        @Operation(summary = "Update")
    public ResponseEntity<patientdto> updatePatient(@PathVariable UUID id, @Valid @RequestBody PatientRequestDto p)
        {
           patientdto pd= ps.updatePatientByEmail(id, p);

              return ResponseEntity.status(HttpStatus.ACCEPTED).body(pd);
        }
        @DeleteMapping("{email}")
        @Operation(summary = "Delete")
    public ResponseEntity DeletePatient(@PathVariable String email)
        {
            ps.deletePatient(email);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
}
