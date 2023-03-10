package org.laba.service.my_batis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.laba.dao.IBusStopDAO;
import org.laba.dao.IMetroStopDAO;

import java.io.IOException;
import java.io.Reader;

public class MetroStopService {
    SqlSessionFactory sqlSessionFactory;

    public MetroStopService() {
        try {
            Reader reader = Resources.getResourceAsReader("myBatis-config.xml");
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public MetroStop getById(Long id) {
        MetroStop metroStop;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IMetroStopDAO metroStopDAO = sqlSession.getMapper(IMetroStopDAO.class);
            metroStop = metroStopDAO.getEntityById(id);
        }
        return metroStop;
    }

    public MetroStop save(MetroStop metroStop) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IMetroStopDAO metroStopDAO = sqlSession.getMapper(IMetroStopDAO.class);

            try {
                metroStopDAO.createEntity(metroStop);
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return metroStop;
    }

    public void update(MetroStop metroStop) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IMetroStopDAO metroStopDAO = sqlSession.getMapper(IMetroStopDAO.class);

            try {
                metroStopDAO.updateEntity(metroStop);
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
            IMetroStopDAO metroStopDAO = sqlSession.getMapper(IMetroStopDAO.class);

            try {
                metroStopDAO.removeEntity(id);
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
