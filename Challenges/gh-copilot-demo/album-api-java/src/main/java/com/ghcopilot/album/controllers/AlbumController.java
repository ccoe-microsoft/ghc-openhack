package com.ghcopilot.album.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ghcopilot.album.models.Album;

import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    // GET: /albums
    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums() {
        List<Album> albums = Album.getAll();
        return ResponseEntity.ok(albums);
    }

    // GET: /albums/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable int id) {
        // Assuming a method Album.getById(id) exists
        Album album = Album.getById(id);
        return ResponseEntity.ok(album);
    }
}