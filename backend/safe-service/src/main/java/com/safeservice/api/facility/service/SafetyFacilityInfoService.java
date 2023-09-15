package com.safeservice.api.facility.service;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.safeservice.api.facility.dto.request.NearSafetyFacility;
import com.safeservice.api.facility.dto.request.SafetyFacilityDto;
import com.safeservice.api.facility.dto.response.NearSafetyPageResponseDto;
import com.safeservice.api.facility.dto.response.NearSafetyResponseDto;
import com.safeservice.domain.facility.constant.FacilityType;
import com.safeservice.domain.facility.entity.SafetyFacility;
import com.safeservice.domain.facility.service.SafetyFacilityService;
import com.safeservice.global.error.ErrorCode;
import com.safeservice.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SafetyFacilityInfoService {

    private final SafetyFacilityService safetyFacilityService;

    public NearSafetyPageResponseDto findFacilityNearWithPaging(NearSafetyFacility nearSafetyFacility, FacilityType facilityType, Pageable pageable) {
        Point point = new Point(nearSafetyFacility.getLongitude(), nearSafetyFacility.getLatitude());
        Distance distance = new Distance(nearSafetyFacility.getDistance(), Metrics.KILOMETERS);
        Page<SafetyFacility> facilityNearWithPaging = safetyFacilityService.findFacilityNearWithPaging(point, distance, facilityType, pageable);
        return  NearSafetyPageResponseDto.from(facilityNearWithPaging);
    }


    public void registerSafetyBell(MultipartFile file, FacilityType facilityType) {

        if (!StringUtils.endsWithIgnoreCase(file.getOriginalFilename(), ".csv")) {
            throw new BusinessException(ErrorCode.INVALID_CSV_FORMAT);
        }

        try {
            CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));

            // Use CsvToBean to map CSV records to a list of objects
            CsvToBean<SafetyFacilityDto> csvToBean = new CsvToBeanBuilder<SafetyFacilityDto>(reader)
                    .withType(SafetyFacilityDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<SafetyFacilityDto> parse = csvToBean.parse();
            List<SafetyFacility> safetyBellList = parse.stream()
                    .map(safeBellDto -> SafetyFacility.builder()
                            .address(safeBellDto.getAddress())
                            .location(new Point(safeBellDto.getLongitude(), safeBellDto.getLatitude()))
                            .facilityType(facilityType)
                            .build())
                    .collect(Collectors.toList());

            safetyFacilityService.saveAll(safetyBellList);

        } catch (IOException e) {
            throw new BusinessException(ErrorCode.FILE_FORMAT);
        }

    }

}
