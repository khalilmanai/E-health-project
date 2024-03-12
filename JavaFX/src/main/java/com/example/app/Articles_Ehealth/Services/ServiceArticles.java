package com.example.app.Articles_Ehealth.Services;
import com.example.app.interfaces.*;
import com.example.app.models.*;
import com.example.app.utils.DBConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class ServiceArticles implements Iservice<Articles> {
    Connection con;
    public ServiceArticles(){con= DBConnection.getInstance().getConnection();}
    @Override
    public boolean add(Articles articles ) {
    String query ="INSERT INTO `articles`"
    +"(`article_id`, `title`, `topic`, `publish_date`, `views`,`id`) VALUES(?,?,?,?,?,?)";
        LocalDate date_pub1=LocalDate.now();
        String date_pub = String.valueOf( date_pub1);
        System.out.println(date_pub);
        Random rand = new Random();
        try { PreparedStatement stm=con.prepareStatement(query);
            stm.setInt(1,articles.getArticle_id());
            stm.setString(2,articles.getTitle());
            stm.setString(3,articles.getTopic());
            stm.setInt(6,articles.getSpecialiste_id());
            stm.setDate(4, Date.valueOf(date_pub));
            stm.setInt(5,rand.nextInt(100));
            stm.executeUpdate();
        }catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
return true;
    }

    @Override
    public void getById(int id) {

    }



    @Override
    public ArrayList<Articles> getAll() {
        ArrayList<Articles> list_articles=new ArrayList();
        String query="SELECT * FROM `articles`";
        try{  Statement stm=con.createStatement();
            ResultSet result=stm.executeQuery(query);
            while (result.next())
            {Articles a= new Articles();
                a.setArticle_id(result.getInt(1));
                a.setTitle(result.getString(2));
                a.setTopic(result.getString(3));
                a.setSpecialiste_id(result.getInt(7));
                a.setDate(String.valueOf(result.getDate(4)));
                a.setViews(result.getInt(5));
                a.setContent(result.getString(6));
                list_articles.add(a);}   }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return list_articles;
    }
    public ArrayList<Articles> getattribut() {
        ArrayList<Articles> list_articles=new ArrayList();
        String query="SELECT `title`, `topic`,`publish_date`, `views` FROM `articles`";
        try{  Statement stm=con.createStatement();
            ResultSet result=stm.executeQuery(query);
            while (result.next())
            {Articles a= new Articles();
                a.setTitle(result.getString(1));
                a.setTopic(result.getString(2));
                a.setDate(String.valueOf(result.getDate(3)));
                a.setViews(result.getInt(4));
                list_articles.add(a);}   }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return list_articles;
    }

    @Override
    public boolean update(Articles articles) {
    String query=
 "UPDATE `articles` SET `title`=?,`topic`=?, `publish_date`=?,`views`=? WHERE `article_id`=?";
        LocalDate date_pub1=LocalDate.now();
        String date_pub = String.valueOf( date_pub1);
        System.out.println(date_pub);
    try{PreparedStatement stm=con.prepareStatement(query);
        stm.setString(1,articles.getTitle());
        stm.setString(2, articles.getTopic());
        stm.setDate(3,Date.valueOf(date_pub));
        stm.setInt(4,articles.getViews());
        stm.setInt(5,articles.getArticle_id());
        stm.executeUpdate();
    return true;}

    catch(SQLException ex){System.out.println(ex.getMessage());
    }
    return false;}

    public void updateCard(Articles articles) {
        String query=
                "UPDATE `articles` SET `title`=?,`topic`=? WHERE `article_id`=?";

        try{PreparedStatement stm=con.prepareStatement(query);
            stm.setString(1,articles.getTitle());
            stm.setString(2, articles.getTopic());
            stm.setInt(3,articles.getArticle_id());
            stm.executeUpdate();
        }

        catch(SQLException ex){System.out.println(ex.getMessage());}
    }

    @Override
    public void delete(Articles articles) {
        String query = "DELETE  FROM `articles` where article_id = ? ";
        try {
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, articles.getArticle_id() );
            stm.executeUpdate();
            System.out.println("DELETED");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }
}
