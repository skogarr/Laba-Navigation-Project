package org.laba.service.my_batis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.laba.dao.ITransitPointDAO;
import org.laba.exception.MapperException;
import org.laba.exception.RemoveByIdException;
import org.laba.exception.SaveException;
import org.laba.exception.UpdateException;
import org.laba.model.TransitPoint;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import static org.laba.exception.Error.*;
import static org.laba.exception.Error.UPDATE_ERROR;

public class TransitPointService {
    SqlSessionFactory sqlSessionFactory;
    Logger logger = Logger.getLogger(TransitPointService.class.getName());

    public TransitPointService() {
        try {
            Reader reader = Resources.getResourceAsReader("myBatis-config.xml");
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public TransitPoint getById(Long id) {
        TransitPoint transitPoint;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ITransitPointDAO transitPointDAO = sqlSession.getMapper(ITransitPointDAO.class);
            transitPoint = transitPointDAO.getEntityById(id);
        }
        return transitPoint;
    }

    public List<TransitPoint> getAllTransitPoints() {
        List<TransitPoint> resulList;
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ITransitPointDAO transitPointDAO = sqlSession.getMapper(ITransitPointDAO.class);
            resulList = transitPointDAO.getAllTransitPoints();
        }
        return resulList;
    }

    public TransitPoint save(TransitPoint transitPoint) throws SaveException, MapperException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ITransitPointDAO transitPointDAO = sqlSession.getMapper(ITransitPointDAO.class);

            try {
                transitPointDAO.createEntity(transitPoint);
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
                logger.log(Level.ERROR, SAVE_ERROR.getDescription(), e);
                throw new SaveException(SAVE_ERROR.getDescription(), e, SAVE_ERROR.getErrorCode());
            }

        } catch (Exception e) {
            logger.log(Level.ERROR, MAPPER_ERROR.getDescription(), e);
            throw new MapperException(MAPPER_ERROR.getDescription(), e, MAPPER_ERROR.getErrorCode());
        }
        return transitPoint;
    }

    public void update(TransitPoint transitPoint) throws UpdateException, MapperException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ITransitPointDAO transitPointDAO = sqlSession.getMapper(ITransitPointDAO.class);

            try {
                transitPointDAO.updateEntity(transitPoint);
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
                logger.log(Level.ERROR, UPDATE_ERROR.getDescription(), e);
                throw new UpdateException(UPDATE_ERROR.getDescription(), e, UPDATE_ERROR.getErrorCode());
            }

        } catch (Exception e) {
            logger.log(Level.ERROR, MAPPER_ERROR.getDescription(), e);
            throw new MapperException(MAPPER_ERROR.getDescription(), e, MAPPER_ERROR.getErrorCode());
        }
    }

    public void removeById(long id) throws RemoveByIdException, MapperException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ITransitPointDAO transitPointDAO = sqlSession.getMapper(ITransitPointDAO.class);

            try {
                transitPointDAO.removeEntity(id);
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
                logger.log(Level.ERROR, REMOVE_BY_ID_ERROR.getDescription(), e);
                throw new RemoveByIdException(REMOVE_BY_ID_ERROR.getDescription(), e, REMOVE_BY_ID_ERROR.getErrorCode());
            }

        } catch (Exception e) {
            logger.log(Level.ERROR, MAPPER_ERROR.getDescription(), e);
            throw new MapperException(MAPPER_ERROR.getDescription(), e, MAPPER_ERROR.getErrorCode());
        }
    }
}
