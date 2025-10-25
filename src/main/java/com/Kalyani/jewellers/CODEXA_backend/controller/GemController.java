package com.Kalyani.jewellers.CODEXA_backend.controller;

import com.Kalyani.jewellers.CODEXA_backend.dto.GemDTO;
import com.Kalyani.jewellers.CODEXA_backend.model.Gem;
import com.Kalyani.jewellers.CODEXA_backend.service.GemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/gems")
@RequiredArgsConstructor
public class GemController {

    private final GemService service;

    // Get all Gems
    @GetMapping
    public ResponseEntity<List<GemDTO>> getAllGems() {
        List<GemDTO> gems = service.getAllGems();
        return new ResponseEntity<>(gems, HttpStatus.OK);
    }


    // Get Gem by id
    @GetMapping("/{id}")
    public ResponseEntity<GemDTO> getGemById(@PathVariable Integer id) {
        GemDTO gem = service.getGem(id);
        return new ResponseEntity<>(gem, HttpStatus.OK);
    }


    // Create gem with optional image in gem row (multipart/form-data)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GemDTO> createGem(
            @RequestParam String gemName,
            @RequestParam(required = false) String karatRate,
            @RequestParam(required = false, name = "image") MultipartFile image
    ) {
        try {
            GemDTO created = GemDTO.fromEntity(service.create(gemName,karatRate,image));
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Get Gem image
    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getImageByGemId(@PathVariable Integer id) {
        Gem gem = service.getGemEntity(id);
        byte[] imageFile = gem.getImageData();

        return ResponseEntity.ok().contentType(MediaType.valueOf(gem.getImageContentType())).body(imageFile);

    }

    // Update Gem
    @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GemDTO> updateGem(@PathVariable int id,
                                            @RequestPart("gemName") String gemName,
                                            @RequestPart(value = "karatRate", required = false) String karatRate,
                                            @RequestPart(value = "image", required = false) MultipartFile imageFile) {
        try {
            GemDTO updated = service.updateGem(id, gemName, karatRate, imageFile);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete Gem
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGem(@PathVariable int id) {
        service.deleteGem(id);
        return new ResponseEntity<>("Gem deleted successfully", HttpStatus.OK);
    }
}




