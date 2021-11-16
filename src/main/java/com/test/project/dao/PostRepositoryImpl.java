package com.test.project.dao;

import com.test.project.api.repository.PostRepository;
import com.test.project.entity.Post;
import com.test.project.exceptions.GlobalException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl  implements  PostRepository {

    private static final Logger logger= LoggerFactory.getLogger(PostRepositoryImpl.class);

    private final Connection connection;

    @Override
    public Post create(Post post) {
        String sql="INSERT INTO posts" + "  (id, text) VALUES "
                + " (?, ?);";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, post.getId());
            statement.setString(2, post.getText());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            logger.error("SqlException" + e.getMessage());
            throw new GlobalException("SqlException" + e.getMessage());
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                logger.error("SqlException" + e.getMessage());
            }
        }
        return post;
    }
    @Override
    public Post update(Post post) {
        String sql="update posts set text= ? where id = ?;";;
        PreparedStatement statement = null;
        try  {
            statement=connection.prepareStatement(sql);
            statement.setString(1, post.getText());
            statement.setLong(2, post.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SqlException" + e.getMessage());
            throw new GlobalException("SqlException" + e.getMessage());
        }finally {
            try {
                if(statement!=null)
                    statement.close();
            } catch (SQLException e) {
                logger.error("SqlException" + e.getMessage());
            }
        }
        return post;
    }

  @Override
    public Post read(Long id) {
        String sql="select id,text from posts where id =?";
        Post post = new Post();
        PreparedStatement statement = null;
        try  {
            statement=connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                post.setId(rs.getLong("id"));
                post.setText(rs.getString("text"));
            }
        } catch (SQLException e) {
            logger.error("SqlException" + e.getMessage());
            throw new GlobalException("SqlException" + e.getMessage());
        }finally {
            try {
                if(statement!=null)
                    statement.close();
            } catch (SQLException e) {
                logger.error("SqlException" + e.getMessage());
            }
        }
        return post;
    }
    @Override
    public Post delete(Long id) {
        PreparedStatement statement=null;
        Post post=read(id);
        String sql="DELETE FROM users WHERE id = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            logger.error("SqlException" + e.getMessage());
            throw new GlobalException("SqlException" + e.getMessage());
        }finally {
            try {
                if(statement!=null)
                    statement.close();
            } catch (SQLException e) {
                logger.error("SqlException" + e.getMessage());
            }
        }
        return post;
    }

}
