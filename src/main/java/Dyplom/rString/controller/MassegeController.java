package Dyplom.rString.controller;

import Dyplom.rString.entity.MasageEntity;
import Dyplom.rString.entity.RstringsEntity;
import Dyplom.rString.exeptions.ResourceNotFoundException;
import Dyplom.rString.repository.massage_repo;
import Dyplom.rString.repository.strings_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/geet")
@CrossOrigin
public class MassegeController {

    @Autowired
    private massage_repo Massage_repo;
    @Autowired
    private strings_repo Strings_repo;


    @GetMapping("/string/{stringId}/massage")
    public ResponseEntity<List<MasageEntity>> getAllMassagentsByRstringId(@PathVariable(value = "stringId") Long stringId) {
        List<MasageEntity> masageents = Massage_repo.findMasageEntitiesByStringsId(stringId);
        return new ResponseEntity<>(masageents, HttpStatus.OK);
    }

    @GetMapping("/string/{stringId}/massageS")
    public ResponseEntity<List<MasageEntity>> getAllMassagentsByRstringIdShowed(@PathVariable(value = "stringId") Long stringId) {
        List<MasageEntity> masageents = Massage_repo.findMasageShowed(stringId);
        return new ResponseEntity<>(masageents, HttpStatus.OK);
    }

    @GetMapping("/string/{stringId}/massageNS")
    public ResponseEntity<List<MasageEntity>> getAllMassagentsByRstringIdNotShowed(@PathVariable(value = "stringId") Long stringId) {
        List<MasageEntity> masageents = Massage_repo.findMasageNotShowed(stringId);
        return new ResponseEntity<>(masageents, HttpStatus.OK);
    }



    @GetMapping("/tags/{id}")
    public ResponseEntity<List<MasageEntity>> getTagsById(@PathVariable(value = "id") Long id) {
        List<MasageEntity> tag = Massage_repo.finById(id);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @GetMapping("/string/{stringId}/mas")
    public ResponseEntity<List<Long>> getAllIdMassagentsByRstringId(@PathVariable(value = "stringId") Long stringId) {
        List<Long> masageents = Massage_repo.findMasageEntitiesByStringsIdAAndShowedEquals1(stringId);
        return new ResponseEntity<>(masageents, HttpStatus.OK);
    }

    @GetMapping("/string/{stringId}/masize")
    public ResponseEntity<List<Long>> sizeAllIdMassagentsByRstringId(@PathVariable(value = "stringId") Long stringId) {
        List<Long> masageents = Massage_repo.sizeMassagesIDByStringIdJPQL(stringId);
        return new ResponseEntity<>(masageents, HttpStatus.OK);
    }

    @DeleteMapping("/massage/{id}")
    public ResponseEntity<HttpStatus> deleteMassage(@PathVariable("id") long id) {
       Massage_repo.deleteById(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @PostMapping("/massage")
    public ResponseEntity<MasageEntity> createTutorial(@RequestBody MasageEntity tutorial) {

        MasageEntity _tutorial = new MasageEntity (tutorial.getStext(), tutorial.getString_speed(), tutorial.getString_color_type(),
                tutorial.getString_color(), tutorial.getString_timing_type(), tutorial.getString_timing(), tutorial.getShowed());
        boolean exists = Massage_repo.existsByStext(tutorial.getStext());
        if (exists == false) {
            Massage_repo.save(_tutorial);
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        }
        else  return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    }

  @PutMapping("/massage/{id}")
  public ResponseEntity<MasageEntity> updateTutorial(@PathVariable("id") long id, @RequestBody MasageEntity tutorial) {
    MasageEntity _tutorial = Massage_repo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found massage with id = " + id));
    _tutorial.setStext(tutorial.getStext());
    _tutorial.setString_speed(tutorial.getString_speed());
    _tutorial.setString_color_type(tutorial.getString_color_type());
    _tutorial.setString_color(tutorial.getString_color());
    _tutorial.setString_timing_type(tutorial.getString_timing_type());
    _tutorial.setString_timing(tutorial.getString_timing());
    _tutorial.setShowed(tutorial.getShowed());
    return new ResponseEntity<>(Massage_repo.save(_tutorial), HttpStatus.OK);
  }


  @PostMapping("/massage/{tutorialId}/string")
  public ResponseEntity<RstringsEntity> addTag(@PathVariable(value = "tutorialId") Long tutorialId, @RequestBody RstringsEntity tagRequest) {
        RstringsEntity tag = Massage_repo.findById(tutorialId).map(tutorial -> {
            long tagId = tagRequest.getId();

            // tag is existed
            if (tagId != 0L) {
                RstringsEntity _tag = Strings_repo.findById(tagId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " + tagId));
                tutorial.addString(_tag);
                Massage_repo.save(tutorial);
                return _tag;
            }

            // add and create new Tag
            tutorial.addString(tagRequest);
            return Strings_repo.save(tagRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));
        return new ResponseEntity<>(tag, HttpStatus.CREATED);
  }

  @DeleteMapping("/massage/{massageId}/string/{stringId}")
  public ResponseEntity<HttpStatus> deleteTagFromTutorial(@PathVariable(value = "massageId") Long tutorialId, @PathVariable(value = "stringId") Long tagId) {
        MasageEntity tutorial = Massage_repo.findById(tutorialId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));

        tutorial.removeStrings(tagId);
        Massage_repo.save(tutorial);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}