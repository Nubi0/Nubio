package com.chattingservice.api.chattingroom.dto.request;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupChattingRoomCsvReq {

    @CsvBindByName(column ="SIDO_NM")
    private String sidoName;
    @CsvBindByName(column ="SGG_NM")
    private String sggName;
    @CsvBindByName(column ="UMD_NM")
    private String umdName;
    @CsvBindByName(column ="RI_NM")
    private String riName;

}
