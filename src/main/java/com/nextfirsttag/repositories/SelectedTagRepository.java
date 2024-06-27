package com.nextfirsttag.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.nextfirsttag.entities.SelectedTag;

public interface SelectedTagRepository extends JpaRepository<SelectedTag, String>{
  @Transactional
    void deleteByTagsIn(List<String> tags);
}
