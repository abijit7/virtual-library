package com.Library.Shelf;

import com.Library.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShelfService {
    private final ShelfMapper shelfMapper;
    private final ShelfRepo shelfRepo;
    public ShelfService(ShelfMapper shelfMapper, ShelfRepo shelfRepo) {
        this.shelfMapper = shelfMapper;
        this.shelfRepo = shelfRepo;
    }

    public ShelfDTO addShelf(ShelfDTO shelfDTO) {
        Shelf shelf = this.shelfMapper.convertShelfDTOToShelf(shelfDTO);
        Shelf savedshelf = this.shelfRepo.save(shelf);
        return this.shelfMapper.convertShelfToShelfDTO(savedshelf);
    }

    public List<ShelfDTO> getAllShelf() {
        return  this.shelfRepo.findAll().stream().
                map(shelfMapper::convertShelfToShelfDTO).collect(Collectors.toList());
    }

    public ShelfDTO getShelfById(Integer id) {
        Shelf shelf = this.shelfRepo.findById(id).orElseThrow(() -> new NotFoundException("Shelf not found of  Id :"+ id));
        if (shelf != null) {
            return this.shelfMapper.convertShelfToShelfDTO(shelf);
        } else {
            return null;
        }
    }
    public ShelfDTO getByLocation(String location){
        Shelf shelf = this.shelfRepo.findByLocation(location);
        if (shelf!=null){
            return this.shelfMapper.convertShelfToShelfDTO(shelf);
        }
        else {
            throw new NotFoundException("Shelf Location "+" "+location+" "+"not found");
        }

    }


    public void deleteShelf(Integer id) {

        Shelf deleteShelf = shelfRepo.findById(id).orElseThrow(()->
                new NotFoundException("Shelf not found of Id :"+" "+id));

        this.shelfRepo.delete(deleteShelf);
    }

    public ShelfDTO updateShelfById(ShelfDTO shelfDTO, Integer id) {
        Shelf shelf = this.shelfRepo.findById(id).orElseThrow(()->
                new NotFoundException("Shelf not found of Id"+" "+id));
        shelf.setLocation(shelfDTO.getLocation());
        return this.shelfMapper.convertShelfToShelfDTO(shelf);
    }
}
