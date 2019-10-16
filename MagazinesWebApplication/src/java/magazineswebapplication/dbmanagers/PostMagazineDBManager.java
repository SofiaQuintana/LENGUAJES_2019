/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazineswebapplication.dbmanagers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import magazineswebapplication.dummyclasses.Catalog;
import magazineswebapplication.dummyclasses.Magazine;
import magazineswebapplication.dummyclasses.Post;
import magazineswebapplication.dummyclasses.Rate;
import magazineswebapplication.exceptions.BlankSpaceException;

/**
 *
 * @author zofia
 */
public class PostMagazineDBManager {
    private Connection connection;
    private List<Magazine> magazines = new ArrayList<>();
    private List<Post> versions = new ArrayList<>();
    private List<Catalog> tags = new ArrayList<>();
    private Rate rate;

    private static final String SELECT_MAGAZINE_QUERY = "SELECT * FROM Magazine WHERE MagazineId = '";
    private static final String SELECT_POST_QUERY = "SELECT * FROM Post WHERE MagazineId = '";
    private static final String SELECT_TAGS_QUERY = "SELECT * FROM Catalog WHERE MagazineId = '";

    public PostMagazineDBManager(Connection connection) {
        this.connection = connection;
    }
    
    public List<Magazine> getMagazines(String query) {
        magazines.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String magazineId = result.getString("MagazineId");
                String name = result.getString("Name");
                String autor = result.getString("Autor");
                String description = result.getString("Description");
                double price = result.getDouble("Price");
                boolean blockLikes = result.getBoolean("BlockLike");
                boolean blockCommentary = result.getBoolean("BlockCommentary");
                boolean blockSubscription = result.getBoolean("BlockSubscription");
                double costoDia = result.getDouble("CostoDia");
                double charge = result.getDouble("Charge");
                Magazine magazine = new Magazine(magazineId, name, autor, description, price, blockLikes, 
                        blockCommentary, blockSubscription, costoDia, charge);
                magazines.add(magazine);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return magazines;
    }

    public List<Magazine> getMagazineByPost(List<Post> posts, List<Magazine> magazines) {
        int contador = 0;
        List<Magazine> temporal = new ArrayList<>();
        for(Post post : posts) {
            for(Magazine magazine : magazines) {
                if(magazine.getMagazineId().equals(post.getMagazineName())) {
                    temporal.add(contador, magazine);
                    contador++;
                }
            }          
        }
        return temporal;
    }
    
    public Magazine getMagazineInList(String name) {
        Magazine magazine;
        String magazineQuery = SELECT_MAGAZINE_QUERY + name + "' AND Charge > 0;";
        this.magazines = getMagazines(magazineQuery);
        if(this.magazines.isEmpty()) {
            magazine = null;
            return magazine;
        } else {
            magazine = this.magazines.get(0);
            return magazine;
        } 
    }

    
    public List<Post> getVersions(String query) {
        versions.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String id = result.getString("IdPost");
                String magazineId = result.getString("MagazineId");
                String username = result.getString("Username");
                String pdfUrl = result.getString("PdfUrl");
                String version = result.getString("Version");
                Date postDate = result.getDate("Date");
                LocalDate date = postDate.toLocalDate();
                Post post = new Post(id, magazineId, username, pdfUrl, version, date);
                versions.add(post);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return versions;
    }
    
    public List<Catalog> getTags(String query) {
        tags.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String magazineName = result.getString("MagazineName");
                String nameTag = result.getString("NameTag");
                String type = result.getString("Type");
                Catalog catalog = new Catalog(magazineName, nameTag, type);
                tags.add(catalog);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return tags;
    }
    
    public void validateProfileRegistration(Magazine magazine, Post post, Catalog catalog, Date date) throws Exception{
        if(magazine.getName().equals("") || magazine.getAutor().equals("") || 
                magazine.getDescription().equals("") || post.getVersion().equals("")) {
            throw new BlankSpaceException("Ingrese todos los elementos marcados con *");
        } else {  
            
            if(date == null) {
                throw new Exception("Ingrese una fecha correcta");
            } else if(catalog != null) {
                addMagazine(magazine);
                addVersion(post, "----", date);
                addTag(catalog);
            } else {
                addMagazine(magazine);
                addVersion(post, "----", date);
            }
        }
    }
    
    public void addMagazine(Magazine magazine) throws Exception {
        try {
            String query = ("INSERT INTO Magazine (MagazineId, Name, Autor, Description, Price, BlockLike, "
                    + "BlockCommentary, BlockSubscription, CostoDia, Charge) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement object = connection.prepareStatement(query);
            object.setString(1, magazine.getMagazineId());
            object.setString(2, magazine.getName());
            object.setString(3, magazine.getAutor());
            object.setString(4, magazine.getDescription());
            object.setDouble(5, magazine.getPrice());
            object.setBoolean(6, magazine.isBlockLike());
            object.setBoolean(7, magazine.isBlockCommentary());
            object.setBoolean(8, magazine.isBlockSubscription());
            object.setDouble(9, magazine.getCostoDia());
            object.setDouble(10, magazine.getCharge());
            object.execute();
        } catch(SQLException e) {
            throw new Exception("Esta revista ya existe, debe corroborar el nombre.");
        }
    }
    
    public void addVersion(Post post, String pdfUrl, Date date) {
        try {
            String query = ("INSERT INTO Post (IdPost, MagazineId, Username, PdfUrl, Version, Date) VALUES (?, ?, ?, ?, ?, ?)");
            PreparedStatement object = connection.prepareStatement(query);
            object.setString(1, post.getIdPost());
            object.setString(2, post.getMagazineName());
            object.setString(3, post.getUsername());
            object.setString(4, pdfUrl);
            object.setString(5, post.getVersion());
            object.setDate(6, date);
            object.execute();
        } catch(SQLException e) {
        }
    }
    
    public void addTag(Catalog catalog) {
        try {
            String query = ("INSERT INTO Catalog (MagazineId, NameTag, Type) VALUES (?, ?, ?)");
            PreparedStatement object = connection.prepareStatement(query);
            object.setString(1, catalog.getMagazineName());
            object.setString(2, catalog.getNameTag());
            object.setString(3, catalog.getType());
            object.execute();
        } catch(SQLException e) {
        }
    }
    
    public Rate getRate(String query) {
        rate = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String username = result.getString("Username");
                double globalCharge = result.getDouble("GlobalPrice");
                double costoDia = result.getDouble("CostoGlobalDia");
                this.rate = new Rate(username, globalCharge, costoDia);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.rate;
    }
}
