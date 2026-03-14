package org.ptm.ptm.mapper;
import org.ptm.ptm.dto.PatientRequestDto;
import org.ptm.ptm.dto.patientdto;
import org.ptm.ptm.*;
import org.ptm.ptm.model.Patient;

import java.time.LocalDate;

public class PatientMapper {

    public static patientdto patientMapper(Patient patient)
    {
        patientdto patientdto=new patientdto();
        patientdto.setFirstName(patient.getName());
        patientdto.setAddress(patient.getAddress());
        patientdto.setId(String.valueOf(patient.getId()));
        patientdto.setEmail(patient.getEmail());
        patientdto.setDateOfBirth(patient.getDateOfBirth().toString());

        return patientdto;
    }
    public static Patient toPatient(PatientRequestDto p)
    {   Patient pat=new Patient();
        pat.setName(p.getName());
        pat.setAddress(p.getAddress());
        pat.setEmail(p.getEmail());
        pat.setDateOfBirth(LocalDate.parse(p.getDateOfBirth()));


        return pat;
    }
}
