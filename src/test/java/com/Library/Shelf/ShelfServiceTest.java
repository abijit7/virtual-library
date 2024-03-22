package com.Library.Shelf;

import com.Library.Book.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.junit.MockitoTestListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Inherited;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class ShelfServiceTest {

        @Mock
    ShelfRepo shelfRepo;
        @Mock
        ShelfMapper shelfMapper;
        @InjectMocks
        ShelfService shelfService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
         shelfService = new ShelfService(shelfMapper,shelfRepo);
    }

    @Test
    void addShelf() {
        Shelf shelf = new Shelf(1,"shelf-1",null);
        ShelfDTO shelfDTO = new ShelfDTO(1,"shelf-1",null);
        //mock
        Mockito.when(shelfMapper.convertShelfDTOToShelf(shelfDTO)).thenReturn(shelf);
        Mockito.when(shelfRepo.save(shelf)).thenReturn(shelf);
        Mockito.when(shelfMapper.convertShelfToShelfDTO(shelf)).thenReturn(new ShelfDTO(1,"shelf-1",null));
//when
        ShelfDTO savedDTO = this.shelfService.addShelf(shelfDTO);
        //then
  assertEquals(shelfDTO.getLocation(),savedDTO.getLocation());
    }

    @Test
    void getAllShelf() {
        List<Shelf> shelfDTOList = new ArrayList<>();
        shelfDTOList.add(new Shelf(1,"shelf-1",null));
        //mock
        Mockito.when(shelfRepo.findAll()).thenReturn(shelfDTOList);
        Mockito.when(shelfMapper.convertShelfToShelfDTO(any(Shelf.class))).thenReturn(new ShelfDTO(1,"shelf-1",null));
        //when
        List<ShelfDTO> allShelf = this.shelfService.getAllShelf();
        //then
        assertEquals(shelfDTOList.size(),allShelf.size());
    }

    @Test
    void getShelfById() {
        Shelf shelf= new Shelf(1,"shelf-1",null);
        //mock
        Mockito.when(this.shelfRepo.findById(shelf.getId())).thenReturn(Optional.of(shelf));
        Mockito.when(shelfMapper.convertShelfToShelfDTO(shelf)).thenReturn(new ShelfDTO(1,"shelf-1",null));
    ShelfDTO findByIdShelf = this.shelfService.getShelfById(shelf.getId());
    //then
        assertEquals(shelf.getId(),findByIdShelf.getId());
        assertEquals(shelf.getLocation(),findByIdShelf.getLocation());
    }



    @Test
    void deleteShelf() {
        Shelf shelf = new Shelf(1,"shelf-1",null);
        //mock
        Mockito.when(shelfRepo.findById(shelf.getId())).thenReturn(Optional.of(shelf));
    Mockito.doNothing().when(shelfRepo).delete(shelf);
    //when
        shelfService.deleteShelf(shelf.getId());
        //then
        verify(shelfRepo).delete(shelf);
    }

    @Test
    void updateShelfById() {

        Shelf shelf = new Shelf(1,"shelf-1",null);
        ShelfDTO shelfDTO = new ShelfDTO(1,"shelf-10",null);
        //mock
        Mockito.when(shelfRepo.findById(shelf.getId())).thenReturn(Optional.of(shelf));
        Mockito.when(shelfMapper.convertShelfToShelfDTO(shelf)).thenReturn(shelfDTO);
        //when
        ShelfDTO DeleteshelfDTO = this.shelfService.updateShelfById(shelfDTO ,shelf.getId());
        //assert
        assertEquals(shelf.getLocation(),DeleteshelfDTO.getLocation());
    }
}