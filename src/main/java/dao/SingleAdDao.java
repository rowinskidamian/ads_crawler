package dao;

import exceptions.CreationException;
import model.SingleAd;
import utils.DbUtil;

import java.sql.*;

public class SingleAdDao {
    private static final String CREATE_AD_QUERY = "INSERT INTO single_ads(title,link,price,uploaded, district) " +
            "VALUES (?,?,?,?,?)";
    private static final String READ_AD_BY_DISTRICT_QUERY = "SELECT * FROM single_ads WHERE district = ?";
    private static final String READ_AD_BY_UPLOADED_QUERY = "SELECT * FROM single_ads WHERE uploaded = ?;";
    private static final String FIND_ALL_AD_QUERY = "SELECT * FROM single_ads";

    public SingleAd create(SingleAd singleAd) {
        try (Connection connection = DbUtil.getInstance().getConnection();
             PreparedStatement createStatement = connection.prepareStatement(CREATE_AD_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            createStatement.setString(1, singleAd.getTitle());
            createStatement.setString(2, singleAd.getLink());
            createStatement.setInt(3, singleAd.getPrice());
            createStatement.setDate(4, singleAd.getDateUploaded());
            createStatement.setString(5, singleAd.getDistrict());
            createStatement.executeUpdate();

            try (ResultSet resultSet = createStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    return new SingleAd(id, singleAd.getTitle(), singleAd.getLink(), singleAd.getPrice(),
                            singleAd.getDateUploaded(), singleAd.getDistrict());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new CreationException("Problem z utworzeniem nowego zadania/ćwiczenia.");
    }


}
