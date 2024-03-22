package com.Library.Shelf;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ShelfMapper {

    private final ModelMapper modelMapper;
    public ShelfMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public ShelfDTO convertShelfToShelfDTO(Shelf shelf){
        modelMapper.getConfiguration().setMatchingStrategy(
                MatchingStrategies.LOOSE
        );
        ShelfDTO shelfDTO= new ShelfDTO();
        shelfDTO = this.modelMapper.map(shelf,ShelfDTO.class);
        return shelfDTO;
    }



    public Shelf convertShelfDTOToShelf(ShelfDTO shelfDTO){
        Shelf shelf = new Shelf();
        shelf = this.modelMapper.map(shelfDTO, Shelf.class);
        return shelf;
    }
}
