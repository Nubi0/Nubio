package com.safeservice.domain.emergencymessage.service;

import com.safeservice.domain.emergencymessage.entity.EmergencyMessage;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EmergencyMessageService {

    EmergencyMessage save(EmergencyMessage emergencyMessage);

    List<EmergencyMessage> searchLatestEM(LocalDateTime fifteenMinutesAgo);
}
