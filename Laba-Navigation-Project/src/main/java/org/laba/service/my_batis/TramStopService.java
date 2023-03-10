package org.laba.service.my_batis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.laba.dao.IMetroStopDAO;
import org.laba.dao.ITramStopDAO;

import java.io.IOException;
import java.io.Reader;

public class TramStopService {
    SqlSessionFactory sqlSessionFactory;

    public TramStopService() {
        try {
            Reader reader = Resources.getResourceAsReader("myBatis-config.xml");
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public TramStop getById(Long id) {
        TramStop tramStop;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ITramStopDAO tramStopDAO = sqlSession.getMapper(ITramStopDAO.class);
            tramStop = tramStopDAO.getEntityById(id);
        }
        return tramStop;
    }

    public TramStop save(TramStop tramStop) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ITramStopDAO tramStopDAO = sqlSession.getMapper(ITramStopDAO.class);

            try {
                tramStopDAO.createEntity(tramStop);
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tramStop;
    }

    public void update(TramStop tramStop) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ITramStopDAO tramStopDAO = sqlSession.getMapper(ITramStopDAO.class);

            try {
                tramStopDAO.updateEntity(tramStop);
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeById(long id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ITramStopDAO tramStopDAO = sqlSession.getMapper(ITramStopDAO.class);

            try {
                tramStopDAO.removeEntity(id);
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
