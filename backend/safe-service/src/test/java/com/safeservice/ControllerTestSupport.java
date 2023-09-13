package com.safeservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safeservice.api.report.controller.ReportController;
import com.safeservice.api.report.service.ReportInfoService;
import com.safeservice.domain.report.repository.ReportRepository;
import com.safeservice.domain.report.service.ReportFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {
        ReportController.class
})
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected ReportInfoService reportInfoService;

}
