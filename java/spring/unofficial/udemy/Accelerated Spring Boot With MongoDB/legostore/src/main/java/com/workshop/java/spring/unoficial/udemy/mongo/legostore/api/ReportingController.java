package com.workshop.java.spring.unoficial.udemy.mongo.legostore.api;

import com.workshop.java.spring.unoficial.udemy.mongo.legostore.model.AvgRatingModel;
import com.workshop.java.spring.unoficial.udemy.mongo.legostore.persistence.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/legostore/api/reports")
public class ReportingController {
    private ReportService reportService;

    public ReportingController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("avgRatingsReport")
    public List<AvgRatingModel> avgRatingReport() {
        return this.reportService.getAvgRatingModel();
    }
}
