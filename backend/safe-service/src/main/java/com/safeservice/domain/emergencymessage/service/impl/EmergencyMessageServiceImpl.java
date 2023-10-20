package com.safeservice.domain.emergencymessage.service.impl;

import com.safeservice.domain.emergencymessage.entity.EmergencyMessage;
import com.safeservice.domain.emergencymessage.entity.type.MdId;
import com.safeservice.domain.emergencymessage.repository.EmergencyMessageRepository;
import com.safeservice.domain.emergencymessage.service.EmergencyMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class EmergencyMessageServiceImpl implements EmergencyMessageService {

    private final EmergencyMessageRepository emergencyMessageRepository;

    @Override
    public EmergencyMessage save(EmergencyMessage emergencyMessage) {
        if (!emergencyMessageRepository.existsByMdId(MdId.from(emergencyMessage.getMdId().getValue()))) {
            return emergencyMessageRepository.save(emergencyMessage);
        }
        return emergencyMessage;
    }

    @Override
    public List<EmergencyMessage> searchLatestEM(LocalDateTime fifteenMinutesAgo, String city) {
        return emergencyMessageRepository.findLatestEmergencyMessage(fifteenMinutesAgo, city);
    }

}
