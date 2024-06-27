package com.nextfirsttag.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nextfirsttag.entities.SelectedTag;
import com.nextfirsttag.services.TagService;

@RestController
@CrossOrigin("*")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/listTags")
    public ResponseEntity<Map<String, List<String>>> listTags() {
        Map<String, List<String>> tags = tagService.getAllTags();
        return ResponseEntity.ok(tags);
    }

    @PostMapping("/saveSelectedTags")
    public ResponseEntity<Map<String, Object>> saveSelectedTags(@RequestBody Map<String, List<String>> request) {
        List<String> selectedTags = request.get("tags");
        tagService.saveSelectedTags(selectedTags);
        List<SelectedTag> savedTags = tagService.getSavedTags();
        List<String> tagNames = savedTags.stream().map(SelectedTag::getTags).collect(Collectors.toList());
        return ResponseEntity.ok(Map.of("message", "Selected tags shifted to database successfully", "savedTags", tagNames));
    }

    @GetMapping("/getSavedTags")
    public ResponseEntity<List<String>> getSavedTags() {
        List<SelectedTag> savedTags = tagService.getSavedTags();
        List<String> tagNames = savedTags.stream().map(SelectedTag::getTags).collect(Collectors.toList());
        return ResponseEntity.ok(tagNames);
    }

   @DeleteMapping("/deleteTags")
    public ResponseEntity<Map<String, String>> deleteTags(@RequestBody Map<String, List<String>> request) {
        List<String> tags = request.get("tags");
        try {
            tagService.deleteTags(tags);
            return ResponseEntity.ok(Map.of("message", "Tag deselected successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Failed to delete tags"));
        }
    }
}
