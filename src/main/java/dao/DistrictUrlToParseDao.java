package dao;

import model.DistrictUrlToParse;
import utils.DbUtil;

import java.sql.*;
import java.util.*;

public class DistrictUrlToParseDao {
    private static final String CREATE_URL_QUERY = "INSERT INTO urls_to_parse(district_name, url) " +
            "VALUES (?,?)";
    private static final String READ_URL_BY_DISTRICT_QUERY = "SELECT * FROM urls_to_parse WHERE district_name = ?";
    private static final String FIND_ALL_URLS_QUERY = "SELECT * FROM urls_to_parse";

    public List<DistrictUrlToParse> findAll() {
        List<DistrictUrlToParse> listOfUrls = new LinkedList<>();
        try (Connection connection = DbUtil.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_URLS_QUERY)) {

            while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String districtName = resultSet.getString("district_name");
            String url = resultSet.getString("url");
            listOfUrls.add(new DistrictUrlToParse(id, districtName, url));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfUrls;
    }

}
