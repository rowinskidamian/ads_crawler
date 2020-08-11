package pl.damianrowinski.parsing.dao;

import pl.damianrowinski.exceptions.CreationException;
import pl.damianrowinski.parsing.model.SingleAd;
import pl.damianrowinski.utils.DbUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class SingleAdDao {
    private static String dbName;

    private final String CREATE_AD_QUERY = generateCreateQuery();

    // poniższe zmienne na razie są niepotrzebne, ale jak będą to trzeba je uzyskać jak z generateCreateQuery()
    // wynika to stąd, że jest stworzona metoda statyczna tworząca obiekt, tak aby przy tworzeniu ustawić najpierw pole
    // dbName, gdyby było przez constructor, to nie zadziała, bo kolejność to:
    // 1. pola, 2. metody, 3. konstruktor
    // CREATE_AD_QUERY nie jest też statyczne, bo najpierw tworzone są statyczne, a później dynamiczne
    // dlatego jak jest zewnętrzna metoda statyczna tworząca obiekt, to najpierw ona jest wywołana i ustawi nam niestatyczne pola
    // gdyby były statyczne pola, to byłyby od razu ustawione przed wywołaniem metody (chyba tak to jest?)

//    private static final String READ_AD_BY_DISTRICT_QUERY = "SELECT * FROM " + dbName + " WHERE district = ?";
//    private static final String READ_AD_BY_UPLOADED_QUERY = "SELECT * FROM " + dbName + " WHERE uploaded = ?;";
    private final String FIND_ALL_AD_QUERY = generateFindAllQuery();

    public static SingleAdDao getSingleAdDaoForBase(String databaseNameFromUser) {
        dbName = databaseNameFromUser;
        return new SingleAdDao();
    }

    private String generateCreateQuery() {
        return "INSERT INTO " + dbName + " (title,link,price,uploaded, district) " +
                "VALUES (?,?,?,?,?)";
    }

    private String generateFindAllQuery() {
        return "SELECT * FROM " + dbName;
    }

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
        throw new CreationException("Problem z utworzeniem nowego wpisu z ogloszeniem.");
    }

    public List<SingleAd> findAll() {
        List<SingleAd> listOfSingleAds = new LinkedList<>();
        try (Connection connection = DbUtil.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_AD_QUERY)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String link = resultSet.getString("link");
                int price = resultSet.getInt("price");
                Date uploaded = resultSet.getDate("uploaded");
                String district = resultSet.getString("district");
                listOfSingleAds.add(new SingleAd(id, title, link, price, uploaded, district));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfSingleAds;
    }


}
