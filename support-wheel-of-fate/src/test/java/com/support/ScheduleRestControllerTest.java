package com.support;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.support.controller.rest.ScheduleRestController;
import com.support.domain.mapper.ScheduleDomainMapper;
import com.support.service.ScheduleService;

@RunWith(SpringRunner.class)
@WebMvcTest(ScheduleRestController.class)
public class ScheduleRestControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private ScheduleService scheduleService;
    
    @MockBean
    private ScheduleDomainMapper scheduleDomainMapper;
    
    @Test
    public void generateScheduleTest() throws Exception {
        mvc.perform(post("/schedule/generate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("startDate", "2018-03-25")).andExpect(status().isOk());
    }

}
