package com.enjoyservice.api.taste.service;

import com.enjoyservice.api.taste.dto.search.TasteApiRes;
import com.enjoyservice.api.taste.dto.update.MemberTasteReq;

public interface TasteApiService {
    TasteApiRes searchTaste(String memberId);
    void updateTaste(String memberId, MemberTasteReq memberTasteReq);
    void createTaste(String memberId, MemberTasteReq memberTasteReq);
}
