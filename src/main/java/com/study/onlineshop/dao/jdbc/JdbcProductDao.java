package com.study.onlineshop.dao.jdbc;

import com.study.onlineshop.dao.ProductDao;
import com.study.onlineshop.dao.jdbc.mapper.ProductRowMapper;
import com.study.onlineshop.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("productDao")
public class JdbcProductDao implements ProductDao {

    private static final String GET_ALL_SQL = "SELECT id, name, creation_date, price FROM product;";
    private static final String GET_PRODUCT_SQL = "SELECT id, name, creation_date, price FROM product WHERE id = ?;";
    private static final String SET_PRODUCT_SQL = "UPDATE product SET name = ?, creation_date = ?, price = ?  WHERE id = ?;";
    private static final String ADD_PRODUCT_SQL = "INSERT INTO product (id, name, creation_date, price ) VALUES (nextval('public.product_id_seq'),?, ?, ?);";
    private static final String REMOVE_PRODUCT_SQL = "DELETE FROM product WHERE id = ?;";

    private static final ProductRowMapper PRODUCT_ROW_MAPPER = new ProductRowMapper();

    private DataSource dataSource;

    @Override
    public List<Product> getAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_SQL)) {


            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = PRODUCT_ROW_MAPPER.mapRow(resultSet);
                products.add(product);
            }

            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    // "UPDATE product SET name = ?, creation_date = ?, price = ?  WHERE id = ?;"
    public void set(Product product) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_PRODUCT_SQL);
            ) {
            statement.setString(1, product.getName());
            statement.setTimestamp(2, Timestamp.valueOf(product.getCreationDate()));
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //"INSERT INTO product (id, name, creation_date, price ) VALUES (nextval('public.product_id_seq'),?, ?, ?);";
    @Override
    public void add(Product product) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT_SQL);
        ) {
            statement.setString(1, product.getName());
            statement.setTimestamp(2, Timestamp.valueOf(product.getCreationDate()));
            statement.setDouble(3, product.getPrice());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    //"DELETE FROM product WHERE id = ?;"
    public void removeById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE_PRODUCT_SQL);
            ) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override //"SELECT id, name, creation_date, price FROM product WHERE id = ?;";
    public Product getProduct(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_SQL);
             ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            Product product = null;
            while (resultSet.next()) {
                product = PRODUCT_ROW_MAPPER.mapRow(resultSet);
            }
            return product;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
