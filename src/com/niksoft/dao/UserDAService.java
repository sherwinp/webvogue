package com.niksoft.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.naming.NamingException;

public class UserDAService extends GenericDataAccessService<User, Integer> {
	private static final Logger log = Logger.getLogger(UserDAService.class.getName());
	
	@Override public void save(User newEntity) {
		Users list = new Users();
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("username", newEntity.getUsername());
		parameters.put("city", newEntity.getCity());
		parameters.put("zip", newEntity.getZip());
		parameters.put("email", newEntity.getEmail());
		String commandText = "INSERT INTO A_USER(username, city, zip, email) VALUES(?, ?, ?, ?); SELECT id FROM A_USER;";
		try {
			list = (Users)execute(commandText, parameters, new mapped_values() );
		} catch (NamingException e) {
			log.warning(e.getMessage());
			
		} catch (SQLException e) {
			log.warning(e.getMessage());
		}
	};
	
	@Override
	public Users findAll() {
		Users list = new Users();
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		try {
			list = (Users) execute("SELECT * FROM A_USER;", parameters, new mapped_values() );
			
		} catch (NamingException ex) {
			log.warning(ex.getMessage());
		} catch (SQLException ex) {
			log.warning(ex.getMessage());
		} finally {

		}
		return list;
	}
	public Users findAllByIdentity(String username, String emailaddress) {
		Users list = new Users();
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("username", username);
		parameters.put("emailaddress", emailaddress);
		try {
			list = (Users) execute(String.format("SELECT id, username, city, zip, email FROM A_USER WHERE username = ? or email = ?;",
								                  username, emailaddress), parameters, new mapped_values() );
			
		} catch (NamingException ex) {
			log.warning(ex.getMessage());
		} catch (SQLException ex) {
			log.warning(ex.getMessage());
		} finally {

		}
		return list;
	}
	private class mapped_values implements IMapped_Values{
		@Override
		public Users maptovalues(ResultSet rs) throws SQLException{
			  Users list = new Users();
				while(rs.next()){
					list.add(new User(rs.getInt(1), rs.getString(2)));
				}
			  return list;
		}
		@Override
		public void fill_parameters(PreparedStatement statement, Map<String, Object> parameters) throws SQLException{
			int idx = 1;
			for (Map.Entry<String, Object> e: parameters.entrySet()){
 				statement.setObject(idx, e.getValue());
 				idx++;
 			}
		}
	}
}
