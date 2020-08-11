package pl.damianrowinski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.damianrowinski.data_analysis.model.DistrictAdsAmount;
import pl.damianrowinski.service.TotalAdsService;

import java.util.List;

@Controller
public class TotalAdsController {

    @RequestMapping("/total/ads")
    public String totalAds(Model model) {
        List<DistrictAdsAmount> totalAdsList = TotalAdsService.getTotal();
        model.addAttribute("totalAdsList", totalAdsList);
        return "totalads";
    }
}
