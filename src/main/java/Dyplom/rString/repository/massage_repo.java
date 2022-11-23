package Dyplom.rString.repository;

import Dyplom.rString.entity.MasageEntity;
import Dyplom.rString.entity.RstringsEntity;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface massage_repo extends JpaRepository <MasageEntity, Long> {

    List<MasageEntity> findMasageEntitiesByStringsId(long strings_id);

    boolean existsByStext(String s_text);

    @Query("select count(string) from MasageEntity message join message.strings string where message.id = :massage_id")
    Long ifNotExistsMsg(Long massage_id);

    @Query("select massage.id from MasageEntity massage where massage.showed = 1")
    Long showed1();


    @Query("select message.id from MasageEntity message join message.strings string where string.id = :strings_id and message.showed = 1")
    List<Long> findMasageEntitiesByStringsIdAAndShowedEquals1(long strings_id);

    @Query("select message from MasageEntity message join message.strings string where string.id = :strings_id and message.showed = 1")
    List<MasageEntity> findMasageShowed(long strings_id);

    @Query("select message from MasageEntity message join message.strings string where string.id = :strings_id and message.showed = 0")
    List<MasageEntity> findMasageNotShowed(long strings_id);

    @Query("select count(message.id) from MasageEntity message join message.strings string where string.id = :strings_id")
    List<Long>  sizeMassagesIDByStringIdJPQL(@Param("strings_id") long strings_id);

    @Query("select massage from MasageEntity massage where massage.id = :id")
    List<MasageEntity> finById(@Param("id") Long id);

}


