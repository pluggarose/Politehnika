package Dyplom.rString.controller;


import Dyplom.rString.entity.RstringsEntity;
import Dyplom.rString.exeptions.srtingAllreadyExists;
import Dyplom.rString.repository.massage_repo;
import Dyplom.rString.service.rStrignsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/rStrings")
@CrossOrigin
public class add_rString_Controller {

    @Autowired
    private rStrignsService RStrignsService;
    @Autowired
    massage_repo Masage_repo;

    @PostMapping
    public ResponseEntity add_string(@RequestBody RstringsEntity sring)
    {
        try {
            RStrignsService.add_rString(sring);
            return ResponseEntity.ok("Добавлено");
        } catch (srtingAllreadyExists e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @GetMapping
    public ResponseEntity getRStrings ()
    {
        try {
            return ResponseEntity.ok(RStrignsService.get_all_strings());
        } catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRStrings(@PathVariable Long id)
    {
        try {
            return ResponseEntity.ok(RStrignsService.delete(id));
        } catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}

