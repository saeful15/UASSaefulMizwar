package saefulProjectSpringBoot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import saefulProjectSpringBoot.model.TabunganModel;


public interface TabunganRepository extends JpaRepository<TabunganModel, Long> {
	@Query(value = "SELECT * FROM tabungan_tbl WHERE nik=:nik", nativeQuery=true)
	List<TabunganModel> getnik(@Param("nik") String nik);
	
}
