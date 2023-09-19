package com.safeservice.api.shelter.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.safeservice.api.shelter.dto.request.NearShelter;
import com.safeservice.api.shelter.dto.request.ShelterDto;
import com.safeservice.api.shelter.dto.response.NearShelterPageResponseDto;
import com.safeservice.api.shelter.service.ShelterInfoService;
import com.safeservice.domain.shelter.constant.ShelterType;
import com.safeservice.domain.shelter.entity.Shelter;
import com.safeservice.domain.shelter.service.ShelterService;
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
public class ShelterInfoServiceImpl implements ShelterInfoService {

    private final ShelterService shelterService;

    @Override
    public void registerShelter(MultipartFile file, ShelterType shelterType) {

        if (!StringUtils.endsWithIgnoreCase(file.getOriginalFilename(), ".csv")) {
            throw new BusinessException(ErrorCode.INVALID_CSV_FORMAT);
        }

        try {
            CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));

            // Use CsvToBean to map CSV records to a list of objects
            CsvToBean<ShelterDto> csvToBean = new CsvToBeanBuilder<ShelterDto>(reader)
                    .withType(ShelterDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<ShelterDto> parse = csvToBean.parse();
            List<Shelter> safetyBellList = parse.stream()
                    .map(shelterDto -> Shelter.builder()
                            .address(shelterDto.getAddress())
                            .phoneNumber(shelterDto.getPhone())
                            .shelterType(shelterType)
                            .location(new Point(shelterDto.getLongitude(), shelterDto.getLatitude()))
                            .name(shelterDto.getName())
                            .build())
                    .collect(Collectors.toList());

            shelterService.saveAll(safetyBellList);

        } catch (IOException e) {
            throw new BusinessException(ErrorCode.FILE_FORMAT);
        }

    }

    @Override
    public NearShelterPageResponseDto findShelterNearWithPaging(NearShelter nearShelter, ShelterType shelterType, Pageable pageable) {

        Point point = new Point(nearShelter.getLongitude(), nearShelter.getLatitude());
        Distance distance = new Distance(nearShelter.getDistance(), Metrics.KILOMETERS);
        Page<Shelter> shelterNearWithPaging = shelterService.findShelterNearWithPaging(point, distance, shelterType, pageable);

        return NearShelterPageResponseDto.from(shelterNearWithPaging);
    }

}
