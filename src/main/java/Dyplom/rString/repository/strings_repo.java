package Dyplom.rString.repository;

import Dyplom.rString.entity.RstringsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface strings_repo extends JpaRepository<RstringsEntity, Long> {
    RstringsEntity findByName(String name);

    List<RstringsEntity> findRstringsEntitiesByMasagessId(Long massage_id);


}
