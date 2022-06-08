package Dyplom.rString.service;

import Dyplom.rString.entity.MasageEntity;
import Dyplom.rString.entity.RstringsEntity;
import Dyplom.rString.exeptions.srtingAllreadyExists;
import Dyplom.rString.repository.massage_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class MassageService {
    @Autowired
    private massage_repo Massage_repo;

    public MasageEntity add_Masage(MasageEntity masage) throws srtingAllreadyExists {
        return Massage_repo.save(masage);
    }
}
