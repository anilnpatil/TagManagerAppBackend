package com.nextfirsttag.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextfirsttag.entities.SelectedTag;

public interface SelectedTagRepository extends JpaRepository<SelectedTag, String>{
  
  
}
