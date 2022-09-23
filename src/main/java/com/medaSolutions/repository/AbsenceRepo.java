package com.medaSolutions.repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.medaSolutions.entities.Absence;
import com.medaSolutions.entities.AbsencePK;


public interface AbsenceRepo extends JpaRepository<Absence, AbsencePK> {

	@Query(value = "SELECT * FROM absences a WHERE a.id = :id",nativeQuery = true)
	Optional<Absence> findAbsenceById(@Param("id") int id);
	
	@Query(value = "SELECT a.id,e.nom as e_nom,e.prenom as e_prenom,m.nom as m_nom,a.date_absence,a.justification FROM etudiants e inner join absences a on e.id = a.etudiant_id "
			+ " inner join modules m on a.module_id = m.id WHERE e.cin = :cin",nativeQuery = true
			)
	List<Map<String, Object>> findEtudiantAbsences(@Param("cin") String cin);
	
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM absences WHERE id = :id",nativeQuery = true)
	int deleteAbsenceById(@Param("id") int id);
	
}
