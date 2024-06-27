package com.nextfirsttag.services;

import java.util.List;
import java.util.Map;

import com.nextfirsttag.entities.SelectedTag;
import com.nextfirsttag.exceptions.TagNotFoundException;

public interface TagService {
    Map<String, List<String>> getAllTags() throws TagNotFoundException;
    void saveSelectedTags(List<String> selectedTags) throws RuntimeException;
    List<SelectedTag> getSavedTags() throws TagNotFoundException;
    void deleteTags(List<String> tags) throws RuntimeException;
}
