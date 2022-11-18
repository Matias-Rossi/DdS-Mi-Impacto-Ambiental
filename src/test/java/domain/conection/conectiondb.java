package domain.conection;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class conectiondb {

    @Test
    public void getConnection() throws URISyntaxException, SQLException {
        URI jdbUri = new URI("mysql://onsujful2p3gcwhv:tb6e11wbypntx43l@x71wqc4m22j8e3ql.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/jmvn0gz7epkkkw0q");

        String username = jdbUri.getUserInfo().split(":")[0];
        String password = jdbUri.getUserInfo().split(":")[1];
        String port = String.valueOf(jdbUri.getPort());
        String jdbUrl = "jdbc:mysql://" + jdbUri.getHost() + ":" + port + jdbUri.getPath();
        System.out.println(jdbUrl);
        System.out.println(username);
        System.out.println(password);

        assertNotNull(DriverManager.getConnection(jdbUrl, username, password));
    }
}