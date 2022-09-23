package com.medaSolutions.repository.sequences;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medaSolutions.entities.sequence.NoteSequence;

public interface NoteSequenceRepo extends JpaRepository<NoteSequence, Integer> {

}
