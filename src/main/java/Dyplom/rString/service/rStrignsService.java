package Dyplom.rString.service;

import Dyplom.rString.entity.RstringsEntity;
import Dyplom.rString.exeptions.srtingAllreadyExists;
import Dyplom.rString.repository.strings_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class rStrignsService {
    @Autowired
    private strings_repo Strings_Repo;

    public RstringsEntity add_rString(RstringsEntity sring) throws srtingAllreadyExists {
        if(Strings_Repo.findByName(sring.getName())!=null)
        {
            throw new srtingAllreadyExists("Строка уже существует");
        }
        return Strings_Repo.save(sring);
    }

   public Iterable <RstringsEntity> get_all_strings()
    {
        Iterable <RstringsEntity> viewall = Strings_Repo.findAll();
        return viewall;
    }

    public Long delete(Long id)
    {
        Strings_Repo.deleteById(id);
        return id;
    }


}
