package com.example.demo.services;
import com.example.demo.interfaces.*;
import com.example.demo.models.*;
import com.example.demo.utils.MyDataBase;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class ServiceArticles implements Iservice<Articles> {
    Connection con;
    public ServiceArticles(){con= MyDataBase.getInstance().getCon();}
    @Override
    public void add(Articles articles ) {
    String query ="INSERT INTO `articles`"
    +"(`article_id`, `title`, `topic`, `specialiste_id`, `publish_date`, `views`,`content`) VALUES(?,?,?,?,?,?,?)";
        LocalDate date_pub1=LocalDate.now();
        String date_pub = String.valueOf( date_pub1);
        System.out.println(date_pub);
        Random rand = new Random();
        try { PreparedStatement stm=con.prepareStatement(query);
            stm.setInt(1,articles.getArticle_id());
            stm.setString(2,articles.getTitle());
            stm.setString(3,articles.getTopic());
            stm.setInt(4,articles.getSpecialiste_id());
            stm.setDate(5, Date.valueOf(date_pub));
            stm.setInt(6,rand.nextInt(100));
            stm.setString(7,articles.getContent());
            stm.executeUpdate();
        }catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }

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
                a.setSpecialiste_id(result.getInt(4));
                a.setDate(String.valueOf(result.getDate(5)));
                a.setViews(result.getInt(6));
                list_articles.add(a);}   }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return list_articles;
    }


    @Override
    public void update(Articles articles) {
    String query=
 "UPDATE `articles` SET `title`=?,`topic`=?, `publish_date`=?,`views`=?, `content`=? WHERE `article_id`=?";
        LocalDate date_pub1=LocalDate.now();
        String date_pub = String.valueOf( date_pub1);

    try{PreparedStatement stm=con.prepareStatement(query);
        stm.setString(1,articles.getTitle());
        stm.setString(2, articles.getTopic());
        stm.setDate(3,Date.valueOf(date_pub));
        stm.setInt(4,articles.getViews());
        stm.setString(5,articles.getContent());
        stm.setInt(6,articles.getArticle_id());
        stm.executeUpdate();
    }

    catch(SQLException ex){System.out.println(ex.getMessage());}
    }

    public void updateCard(Articles articles) {
        String query=
                "UPDATE `articles` SET `title`=?,`topic`=?  WHERE `article_id`=?";
        LocalDate date_pub1=LocalDate.now();
        String date_pub = String.valueOf( date_pub1);
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


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }
}
