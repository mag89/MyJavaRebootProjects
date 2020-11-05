package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource implements AutoCloseable {
    private static final String DB_NAME = "music.db";
    private static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

    private static final String TABLE_ALBUMS = "albums";
    private static final String COLUMN_ALBUM_ID = "_id";
    private static final String COLUMN_ALBUM_NAME = "name";
    private static final String COLUMN_ALBUM_ARTIST = "artist";

    private static final String TABLE_ARTISTS = "artists";
    private static final String COLUMN_ARTIST_ID = "_id";
    private static final String COLUMN_ARTIST_NAME = "name";

    private static final String TABLE_SINGS = "songs";
    private static final String COLUMN_SONG_TRACK = "track";
    private static final String COLUMN_SONG_TITLE = "title";
    private static final String COLUMN_SONG_ALBUM = "album";

    private Connection conn;

    public boolean openConn() {
        try {
            this.conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void close() throws Exception {
        try {
            if (!this.conn.isClosed()) {
                this.conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Artist> queryArtists() {
        try (Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery("SELECT * FROM " + TABLE_ARTISTS)) {

            List<Artist> artists = new ArrayList<>();
            while (result.next()) {
                Artist artist = new Artist(result.getInt(COLUMN_ARTIST_ID), result.getString(COLUMN_ARTIST_NAME));
                artists.add(artist);
            }

            return artists;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}