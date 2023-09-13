package com.safeservice.api.safetybell.service;

import com.safeservice.api.safetybell.dto.SafeBellDto;
import com.safeservice.domain.safetybell.entity.SafetyBell;
import com.safeservice.domain.safetybell.service.SafetyBellService;
import com.safeservice.global.error.ErrorCode;
import com.safeservice.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Service
@RequiredArgsConstructor
public class SafetyBellInfoService {

    private final SafetyBellService safetyBellService;
    public void register(MultipartFile file) {

        if (!StringUtils.endsWithIgnoreCase(file.getOriginalFilename(), ".csv")) {
            throw  new BusinessException(ErrorCode.INVALID_CSV_FORMAT);
        }

        try {
            CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));

            // Use CsvToBean to map CSV records to a list of objects
            CsvToBean<SafeBellDto> csvToBean = new CsvToBeanBuilder<SafeBellDto>(reader)
                    .withType(SafeBellDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<SafeBellDto> parse = csvToBean.parse();
            List<SafetyBell> safetyBellList = parse.stream()
                    .map(safeBellDto -> SafetyBell.builder()
                            .address(safeBellDto.getAddress())
                            .location(new Point(safeBellDto.getLongitude(), safeBellDto.getLatitude()))
                            .build())
                    .collect(Collectors.toList());

            safetyBellService.saveAll(safetyBellList);

        } catch (IOException e) {
            throw new BusinessException(ErrorCode.FILE_FORMAT);
        }

    }

}
