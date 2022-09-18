package com.medaSolutions.repository.sequences;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medaSolutions.entities.sequence.AffectationSequence;

public interface AffectationSequenceRepo extends JpaRepository<AffectationSequence, Integer> {

}
