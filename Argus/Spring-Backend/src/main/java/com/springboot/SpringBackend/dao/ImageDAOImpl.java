package com.springboot.SpringBackend.dao;

import com.springboot.SpringBackend.model.Image;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

//import javax.annotation.PostConstruct;
//import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@Repository("ImageDAO")
public class ImageDAOImpl //extends JdbcDaoSupport implements ImageDAO
{
    /*
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public List<Image> findAllImgs() {
        String sql = "SELECT * FROM photo_Table";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Image> result = new ArrayList<Image>();
        for(Map<String, Object> row:rows){
            Image x = new Image();
            x.setImageId((Long)row.get("photo_id"));
            x.setImage((String)row.get("image"));
            result.add(x);
        }

        return result;
    }

    @Override
    public Image getImgById(Long id) {
        String sql = "SELECT image FROM photoTable WHERE photo_id = ?";
        return getJdbcTemplate().queryForObject(sql, new Object[]{id}, String.class);
    }

    @Override
    public Image createImg(Image x) {
        String sql = "INSERT INTO photo_Table " + "(image) VALUES (?)" ;
        getJdbcTemplate().update(sql, new Object[]{ x.getImage() });
        return x;
    }

    @Override
    public Image updateImg(Image x) {

    }

    @Override
    public void deleteImg(Image x) {

    }

    @Override
    public void deleteImgById(Long id) {

    }
    */
}

