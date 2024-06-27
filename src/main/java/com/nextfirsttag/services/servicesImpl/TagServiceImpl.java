package com.nextfirsttag.services.servicesImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nextfirsttag.entities.SelectedTag;
import com.nextfirsttag.entities.Tag;
import com.nextfirsttag.exceptions.TagNotFoundException;
import com.nextfirsttag.repositories.SelectedTagRepository;
import com.nextfirsttag.repositories.TagRepository;
import com.nextfirsttag.services.TagService;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final SelectedTagRepository selectedTagRepository;

    public TagServiceImpl(TagRepository tagRepository, SelectedTagRepository selectedTagRepository) {
        this.tagRepository = tagRepository;
        this.selectedTagRepository = selectedTagRepository;
    }

    @Override
    public Map<String, List<String>> getAllTags() throws TagNotFoundException {
        List<Tag> tags = tagRepository.findAll();
        List<String> tagNames = tags.stream().map(Tag::getTags).collect(Collectors.toList());
        if (tagNames.isEmpty()) {
            throw new TagNotFoundException("No tags found");
        }
        Map<String, List<String>> response = new HashMap<>();
        response.put("tags", tagNames);
        return response;       
    }

    @Transactional
    @Override
    public void saveSelectedTags(List<String> selectedTags) throws RuntimeException {
        try {
            List<SelectedTag> tags = selectedTags.stream()
                                                 .map(tag -> new SelectedTag(tag))
                                                 .collect(Collectors.toList());
            selectedTagRepository.saveAll(tags);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save tags to the database", e);
        }
    }

    @Override
    public List<SelectedTag> getSavedTags() throws TagNotFoundException {
        List<SelectedTag> savedTags = selectedTagRepository.findAll();
        if (savedTags.isEmpty()) {
            throw new TagNotFoundException("No saved tags found");
        }
        return savedTags;
    }

    @Override
    public void deleteTags(List<String> tags) throws RuntimeException {
        try {
            selectedTagRepository.deleteByTagsIn(tags);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete tags from the database", e);
        }
    }
}
