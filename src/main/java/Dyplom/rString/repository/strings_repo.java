package Dyplom.rString.repository;

import Dyplom.rString.entity.RstringsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface strings_repo extends JpaRepository<RstringsEntity, Long> {
    RstringsEntity findByName(String name);

    List<RstringsEntity> findRstringsEntitiesByMasagessId(Long massage_id);


    /*@Query ("delete from MasageEntity message where massage.strings string.string_id = :string_id")
    void Delete_real(long string_id);*/

}
