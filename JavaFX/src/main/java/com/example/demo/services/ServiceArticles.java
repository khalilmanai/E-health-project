package com.example.demo.services;
import com.example.demo.interfaces.*;
import com.example.demo.models.*;
import com.example.demo.utils.MyDataBase;
import java.sql.*;
import java.util.ArrayList;

public class ServiceArticles implements Iservice<Articles> {
    Connection con;
    public ServiceArticles(){con= MyDataBase.getInstance().getCon();}
    @Override
    public void add(Articles articles ) {
    String query ="INSERT INTO `articles`"
    +"(`article_id`, `title`, `topic`, `specialiste_id`, `publish_date`, `views`) VALUES(?,?,?,?,?,?)";
        try { PreparedStatement stm=con.prepareStatement(query);
            stm.setInt(1,articles.getArticle_id());
            stm.setString(2,articles.getTitle());
            stm.setString(3,articles.getTopic());
            stm.setInt(4,articles.getSpecialiste_id());
            stm.setDate(5, Date.valueOf(articles.getDate()));
            stm.setInt(6,articles.getViews());
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
 "UPDATE `articles` SET `title`=?,`topic`=?, `publish_date`=?,`views`=? WHERE `article_id`=?";

    try{PreparedStatement stm=con.prepareStatement(query);

        stm.setString(1,articles.getTitle());
        stm.setString(2, articles.getTopic());
        stm.setDate(3,Date.valueOf(articles.getDate()));
        stm.setInt(4,articles.getViews());
        stm.setInt(5,articles.getArticle_id());
        stm.executeUpdate();
    }

    catch(SQLException ex){System.out.println(ex.getMessage());}
    }

    @Override
    public boolean delete(Articles articles) {
        String query = "DELETE  FROM `articles` where aricle_id = ? ";
        try {
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, articles.getArticle_id() );
            stm.executeUpdate();
            System.out.println("DELETED");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
