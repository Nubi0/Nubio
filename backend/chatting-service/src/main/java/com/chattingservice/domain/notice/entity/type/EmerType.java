package com.chattingservice.domain.notice.entity.type;

import com.chattingservice.domain.notice.exception.InvalidEmerTypeException;
import com.chattingservice.global.error.ErrorCode;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum EmerType {


    ETC("기타"),
    TYPHOON("태풍"),
    DRY("건조"),
    FOREST_FIRE("산불"),
    LANDSLIDE("산사태"),
    FLOOD("홍수"),
    DOWNPOUR("호우"),
    HEAT_WAVE("폭염"),
    FOG("안개"),
    STORM("풍랑"),
    FINE_DUST("미세먼지"),
    RISING_TIDE("대조기"),
    DROUGHT("가뭄"),
    HEAVY_SNOW("대설"),
    TSUNAMI("지진해일"),
    EARTHQUAKE("지진"),
    COLD_WAVE("한파"),
    DUST_STORM("황사"),
    GALE("강풍"),
    TRAFFIC_CONTROL("교통통제"),
    FIRE("화재"),
    COLLAPSE("붕괴"),
    EXPLOSION("폭발"),
    TRAFFIC_ACCIDENT("교통사고"),
    ENVIRONMENTAL_POLLUTION("환경오염사고"),
    ENERGY("에너지"),
    COMMUNICATION("통신"),
    TRAFFIC("교통"),
    FINANCE("금융"),
    MEDICAL("의료"),
    WATERWORKS("수도"),
    EPIDEMIC("전염병"),
    BLACKOUT("정전"),
    GAS("가스"),
    AI("AI"),
    CBR_ACCIDENT("화생방사고"),
    RIOT("폭동"),
    TERROR("테러"),
    EMERGENCY("비상사태"),
    CIVIL_DEFENSE("민방공");

    private String description;

    EmerType(String description) {
        this.description = description;
    }


    public static EmerType from(String description) {
        return Arrays.stream(EmerType.values())
                .filter(stage -> stage.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new InvalidEmerTypeException(ErrorCode.INVALID_EMER_TYPE));
    }


}
