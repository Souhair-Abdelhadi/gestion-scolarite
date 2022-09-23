package com.medaSolutions.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.medaSolutions.entities.Absence;
import com.medaSolutions.entities.Notes;
import com.medaSolutions.entities.NotesPK;

public interface NoteRepo extends JpaRepository<Notes, NotesPK> {

	@Query(value = "SELECT * FROM notes a WHERE a.id = :id",nativeQuery = true)
	Optional<Notes> findNoteById(@Param("id") int id);
	
	@Query(value = "SELECT * FROM notes a WHERE a.etudiant_id = :etudiantId AND a.module_id = :moduleId",nativeQuery = true)
	Optional<Notes> findNoteByEtudianIdtAndModuleId(@Param("etudiantId") int etudiantId,@Param("moduleId") int moduleId);
	
	@Query(value = "SELECT n.id,n.etudiant_id,n.module_id,m.nom,n.note,m.coef FROM etudiants e inner join notes n on e.id = n.etudiant_id inner join modules m on n.module_id = m.id WHERE n.etudiant_id = :etudiantId",nativeQuery = true)
	List<Map<String, Object>> findEtudiantNoteByModule(@Param("etudiantId") int etudiantId);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM notes WHERE id = :id",nativeQuery = true)
	int deleteNoteById(@Param("id") int id);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE notes SET note = :note WHERE id = :id",nativeQuery = true)
	int updateNoteById(@Param("id") int id,@Param("note") float note);
	
}
