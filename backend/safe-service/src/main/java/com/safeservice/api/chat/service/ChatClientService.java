package com.safeservice.api.chat.service;

import com.safeservice.api.chat.dto.ChatClientDto;

public interface ChatClientService {

    ChatClientDto getRegion(double longitude, double latitude);
}
