package com.contoller;

import com.repository.HolidayRequestRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HolidayRequestController {

    HolidayRequestRepository holidayRequestRepository;
}
