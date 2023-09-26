package com.enjoyservice.api.taste.service;

import com.enjoyservice.api.taste.dto.create.TasteInfoReq;
import com.enjoyservice.api.taste.dto.search.TasteApiRes;
import com.enjoyservice.api.taste.dto.update.MemberTasteReq;

public interface TasteApiService {
    TasteApiRes searchTaste(String memberId);
    void createTaste(String memberId, MemberTasteReq memberTasteReq);
    void saveTaste(TasteInfoReq tasteInfoReq);
}
