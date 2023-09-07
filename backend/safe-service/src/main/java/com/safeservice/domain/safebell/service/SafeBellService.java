package com.safeservice.domain.safebell.service;

import com.safeservice.domain.safebell.entity.SafeBell;

public interface SafeBellService {

    SafeBell register(SafeBell safeBell);

    void delete(SafeBell safeBell);

}
