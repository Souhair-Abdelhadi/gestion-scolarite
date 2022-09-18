package com.medaSolutions.repository.sequences;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medaSolutions.entities.sequence.AbsenceSequence;

public interface AbsenceSequenceRepo extends JpaRepository< AbsenceSequence ,Integer > {

}
