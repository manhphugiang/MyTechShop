package ca.project.giangma.controller;

import ca.project.giangma.beans.Item;
import ca.project.giangma.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/rest")

public class itemRestController {
    private ItemRepository itemRepository;

    @GetMapping(value = {"", "/"})
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }



}
