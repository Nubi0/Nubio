package com.enjoyservice.domain.taste.service;

import com.enjoyservice.domain.taste.entity.Taste;
import com.enjoyservice.domain.taste.entity.constant.DetailType;

public interface TasteService {
    Taste saveTaste(Taste taste);
    Taste searchByDetailType(DetailType detailType);
}
