package com.enjoyservice.domain.KakaoPlace.entity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "카카오 장소")
@Getter
@Setter
@NoArgsConstructor
public class KakaoPlace {

    @Id
    private String id;

    @Field(name = "kakao_id", type = FieldType.Integer)
    private String kakaoId;

    @Field(name = "name", type = FieldType.Text)
    private String name;

    @Field(name = "category_group_code", type = FieldType.Text)
    private String categoryGroupCode;

    @Field(name = "category_group_name", type = FieldType.Text)
    private String categoryGroupName;

    @Field(name = "category_detail", type = FieldType.Text)
    private String categoryDetail;

    @Field(name = "phone_number", type = FieldType.Text)
    private String phoneNumber;

    @Field(name = "url", type = FieldType.Text)
    private String url;

    @Field(name = "address_name", type = FieldType.Text)
    private String addressName;

    @Field(name = "road_address_name", type = FieldType.Text)
    private String roadAddressName;

    @Field(name = "longitude", type = FieldType.Double)
    private String longitude;

    @Field(name = "latitude", type = FieldType.Double)
    private String latitude;

    @Field(name = "img_url", type = FieldType.Text)
    private String imgUrl;

    @Builder
    public KakaoPlace(String kakaoId, String name, String categoryGroupCode, String categoryGroupName,
                      String categoryDetail, String phoneNumber, String url, String addressName,
                      String roadAddressName, String longitude, String latitude, String imgUrl) {
        this.kakaoId = kakaoId;
        this.name = name;
        this.categoryGroupCode = categoryGroupCode;
        this.categoryGroupName = categoryGroupName;
        this.categoryDetail = categoryDetail;
        this.phoneNumber = phoneNumber;
        this.url = url;
        this.addressName = addressName;
        this.roadAddressName = roadAddressName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.imgUrl = imgUrl;
    }
}
