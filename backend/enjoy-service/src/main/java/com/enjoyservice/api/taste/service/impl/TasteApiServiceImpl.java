package com.enjoyservice.api.taste.service.impl;

import com.enjoyservice.api.taste.dto.search.TasteApiRes;
import com.enjoyservice.api.taste.dto.update.MemberTasteReq;
import com.enjoyservice.api.taste.service.TasteApiService;
import com.enjoyservice.domain.membertaste.entity.MemberTaste;
import com.enjoyservice.domain.membertaste.service.MemberTasteService;
import com.enjoyservice.domain.taste.entity.Taste;
import com.enjoyservice.domain.taste.entity.constant.DetailType;
import com.enjoyservice.domain.taste.entity.constant.Type;
import com.enjoyservice.domain.taste.exception.InvalidCheckTaste;
import com.enjoyservice.domain.taste.service.TasteService;
import com.enjoyservice.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TasteApiServiceImpl implements TasteApiService {

    private final TasteService tasteService;
    private final MemberTasteService memberTasteService;

    @Override
    public TasteApiRes searchTaste(String memberId) {
        List<MemberTaste> searchByMemberId = memberTasteService.findByMemberId(memberId);
        List<String> foods = new ArrayList<>();
        List<String> drinks = new ArrayList<>();
        List<String> plays = new ArrayList<>();
        searchByMemberId.forEach(memberTaste -> {
            String taste = memberTaste.getTaste().getType().getDescription();
            String description = memberTaste.getTaste().getDetailType().getDescription();
            if (taste.equals(Type.EAT.getDescription())) {
                foods.add(description);
            } else if (taste.equals(Type.DRINK.getDescription())) {
                drinks.add(description);
            } else {
                plays.add(description);
            }});
        checkIsCreated(foods, drinks, plays);
        return TasteApiRes.of(TasteApiRes.MemberTasteInfoDto.of("먹기",foods),
                TasteApiRes.MemberTasteInfoDto.of("마시기",drinks),
                TasteApiRes.MemberTasteInfoDto.of("놀기",plays));
    }

    @Override
    @Transactional
    public void updateTaste(String memberId, MemberTasteReq memberTasteReq) {
        memberTasteService.deleteTaste(memberId);
        saveTaste(memberId, memberTasteReq);
    }

    @Override
    @Transactional
    public void createTaste(String memberId, MemberTasteReq memberTasteReq) {
        saveTaste(memberId, memberTasteReq);
    }

    private void saveTaste(String memberId, MemberTasteReq memberTasteReq) {
        List<MemberTaste> memberTastes = new ArrayList<>();
        for (MemberTasteReq.MemberTasteInfo memberTasteInfo : memberTasteReq.getTaste()) {
            for (String detailType : memberTasteInfo.getDetailType()) {
                Taste taste = tasteService.searchByDetailType(DetailType.from(detailType));
                MemberTaste buildMemberTaste = MemberTaste.builder().taste(taste)
                        .memberId(memberId).build();
                memberTastes.add(buildMemberTaste);
            }
        }
        memberTasteService.saveAll(memberTastes);
    }


    private void checkIsCreated(List<String> foods,List<String> drinks, List<String> plays) {
        if (foods.size() == 0 && drinks.size() == 0 && plays.size() == 0) {
            throw new InvalidCheckTaste(ErrorCode.INVALID_CHECK_TASTE);
        }
    }
}
