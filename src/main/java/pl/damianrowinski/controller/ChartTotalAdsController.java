package pl.damianrowinski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.damianrowinski.data_analysis.model.DistrictAdsAmount;
import pl.damianrowinski.service.TotalAdsService;

import java.util.List;

@Controller
public class ChartTotalAdsController {

    @RequestMapping("/total/chart")
    public String valueAndNamesToBuildChartColumns(Model model) {

        List<DistrictAdsAmount> totalAdsList = TotalAdsService.getTotal();
        StringBuilder sbChartColumnName = new StringBuilder();
        StringBuilder sbChartColumnValue = new StringBuilder();
        int lastItemOfAdsList = totalAdsList.size() - 1;

        for (int i = 0; i < totalAdsList.size(); i++) {
            String districtName = totalAdsList.get(i).getDistrictName();
            int noOfAds = totalAdsList.get(i).getNoOfAds();

            if (i < lastItemOfAdsList) {
                sbChartColumnName.append("'")
                        .append(districtName)
                        .append("', ");
                sbChartColumnValue.append(noOfAds)
                        .append(", ");
            } else {
                sbChartColumnName.append("'")
            .append(districtName);
            }
        }

        model.addAttribute("chartColumnNames", sbChartColumnName.toString());
        model.addAttribute("chartColumnValue", sbChartColumnValue.toString());

        //'Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'
        //12, 19, 3, 5, 2, 3

        return "chart_total_ads";
    }


}
