package com.nextfirsttag.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.nextfirsttag.entities.Tag;

public interface TagRepository extends JpaRepository<Tag, String> {
}
