package org.ptm.ptm.Exception;

public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException()
    {
        super("Patient not found");
    }
}
