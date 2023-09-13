package com.enjoyservice.domain.place.entity.type;

import com.enjoyservice.domain.place.entity.constant.GroupCode;
import com.enjoyservice.domain.place.entity.constant.GroupName;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Enumerated(EnumType.STRING)
    @Column(name = "category_group_code")
    private GroupCode groupCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "category_group_name")
    private GroupName groupName;

    @Embedded
    private Detail detail;

    private Category(GroupCode groupCode, GroupName groupName, Detail detail) {
        this.groupCode = groupCode;
        this.groupName = groupName;
        this.detail = detail;
    }

    public static Category from(final GroupCode groupCode, final GroupName groupName, final Detail detail) {
        // TODO: 각 카테고리별 짝이 맞는지 검증하는 validate() 넣어야 됨
        return new Category(groupCode, groupName, detail);
    }
}
