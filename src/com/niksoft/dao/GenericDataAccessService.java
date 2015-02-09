package com.niksoft.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.metamodel.EntityType;
import javax.sql.DataSource;

public abstract class GenericDataAccessService<EntityType, PrimaryKeyType extends Serializable> {

    // Constructor

    public void save(EntityType newEntity) {
        // TODO Auto-generated method stub
    }


    public void update(EntityType entity) {
        // TODO Auto-generated method stub

    }

 
    public EntityType find(PrimaryKeyType primaryKey) {
        // TODO Auto-generated method stub
        return null;
    }


    public List<EntityType> findByProperty(String property) {
        // TODO Auto-generated method stub
        return null;
    }


    public List<EntityType> findAll() {
        // TODO Auto-generated method stub
        return null;
    }


    public void delete(PrimaryKeyType primaryKey) {
        // TODO Auto-generated method stub

    }

    public void delete(EntityType entity) {
        // TODO Auto-generated method stub

    }
    protected  List<?> execute(String commandText, Map<String, Object> parameters, IMapped_Values imv) throws NamingException, SQLException{
    	InitialContext ic = new InitialContext();
 		try {
 			String dsName = "java:jboss/datasources/DemoDSsqlite";
 			DataSource ds = (javax.sql.DataSource) ic.lookup(dsName);
 			Connection connection = ds.getConnection();
 			try {
 				PreparedStatement statement = connection.prepareStatement(commandText);
 				imv.fill_parameters( statement, parameters);
 				try {
 					ResultSet resultSet = statement.executeQuery();
 					try{
 						return imv.maptovalues(resultSet);
 					} finally {
 						resultSet.close();
 					}
 				} finally {
 					statement.close();
 				}

 			} finally {
 				connection.close();
 			}
 		} finally {
 			ic.close();
 			ic = null;
 		}
    }
    protected void execute(Integer result, String commandText, Map<String, Object> parameters, IMapped_Values imv) throws NamingException, SQLException{
    	InitialContext ic = new InitialContext();
 		try {
 			String dsName = "java:jboss/datasources/DemoDSsqlite";
 			DataSource ds = (javax.sql.DataSource) ic.lookup(dsName);
 			Connection connection = ds.getConnection();
 			try {
 				PreparedStatement statement = connection.prepareStatement(commandText);
 				imv.fill_parameters( statement, parameters);
 				try {
 					result = statement.executeUpdate();
 				} finally {
 					statement.close();
 				}

 			} finally {
 				connection.close();
 			}
 		} finally {
 			ic.close();
 			ic = null;
 		}
    }
    interface IMapped_Values{
 	   List<?> maptovalues(ResultSet rs) throws SQLException;
 	  void fill_parameters(PreparedStatement statement, Map<String, Object> parameters) throws SQLException;
    }
}