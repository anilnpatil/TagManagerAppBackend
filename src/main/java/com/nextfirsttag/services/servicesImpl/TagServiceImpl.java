package com.nextfirsttag.services.servicesImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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
    public Map<String, List<String>> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        List<String> tagNames = tags.stream().map(Tag::getTags).collect(Collectors.toList());
        Map<String, List<String>> response = new HashMap<>();
        response.put("tags", tagNames);
        if (response.isEmpty()) {
            throw new TagNotFoundException("No tags found");
        }
        return response;       
    }
    @Override
    public void saveSelectedTags(List<String> selectedTags) {
        List<SelectedTag> tags = selectedTags.stream().map(tag -> new SelectedTag(tag)).collect(Collectors.toList());
        selectedTagRepository.saveAll(tags);
    }

    @Override
    public List<SelectedTag> getSavedTags() {
        // Fetch saved tags from the database
        return selectedTagRepository.findAll();
    }
}
