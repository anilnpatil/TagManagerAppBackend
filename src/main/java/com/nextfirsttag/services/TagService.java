package com.nextfirsttag.services;

import java.util.List;
import java.util.Map;

import com.nextfirsttag.entities.SelectedTag;

public interface TagService {
 Map<String, List<String>> getAllTags(); 
 
 void saveSelectedTags(List<String> selectedTags);
 
 List<SelectedTag> getSavedTags();
}
