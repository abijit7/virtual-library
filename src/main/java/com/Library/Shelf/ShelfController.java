package com.Library.Shelf;

import jdk.dynalink.linker.LinkerServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shelf")
public class ShelfController {

   private final ShelfService shelfService;

    public ShelfController(ShelfService shelfService) {
        this.shelfService = shelfService;
    }

    @PostMapping("/add")
    public ShelfDTO addShelf(@RequestBody ShelfDTO shelfDTO){
return this.shelfService.addShelf(shelfDTO);
    }
    @GetMapping("/all")
    public List<ShelfDTO> getAllShelf(){
       return this.shelfService.getAllShelf();
    }
    @GetMapping("/id/{id}")
    public ShelfDTO getShelfById(@PathVariable("id")Integer id){
        return this.shelfService.getShelfById(id);

    }@GetMapping("/location/{location}")
    public ShelfDTO getShelfByLocation(@PathVariable("location") String location){
        return this.shelfService.getByLocation(location);
    }
    @DeleteMapping("/delete/{id}")
   public void deleteShelf(@PathVariable("id") Integer id){
       this.shelfService.deleteShelf(id);
   }
   @PutMapping("/update/id/{id}")
   public ShelfDTO updateShelfById(@PathVariable("id") Integer id , @RequestBody ShelfDTO shelfDTO){
        return this.shelfService.updateShelfById(shelfDTO,id);

   }
}
