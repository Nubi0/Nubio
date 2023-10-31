package com.enjoyservice.api.place.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceCsvInfoRes {

    @CsvBindByName(column = "address_name")
    private String addressName;

    @CsvBindByName(column = "category_group_code")
    private String categoryGroupCode;

    @CsvBindByName(column = "category_group_name")
    private String categoryGroupName;

    @CsvBindByName(column = "category_name")
    private String categoryName;

    @CsvBindByName(column = "id")
    private int kakaoId;

    @CsvBindByName(column = "phone")
    private String phone;

    @CsvBindByName(column = "place_name")
    private String name;

    @CsvBindByName(column = "place_url")
    private String url;

    @CsvBindByName(column = "road_address_name")
    private String addressRoadName;

    @CsvBindByName(column = "x")
    private double longitude;

    @CsvBindByName(column = "y")
    private double latitude;

    @CsvBindByName(column = "img_url")
    private String imgUrl;

}
