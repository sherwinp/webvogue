package com.niksoft.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.naming.NamingException;

public class VoucherDAService extends GenericDataAccessService<Voucher, Integer> {
	private static final Logger log = Logger.getLogger(VoucherDAService.class.getName());
	
	@Override 
	public Voucher find(Integer primaryKey) {
		Vouchers list = new Vouchers();
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", primaryKey);
		try{
		list = (Vouchers) execute(String.format("SELECT _id, refitem FROM vouchers WHERE _id = ? ;"), parameters, new mapped_values() );
		} catch (NamingException ex) {
			log.warning(ex.getMessage());
		} catch (SQLException ex) {
			log.warning(ex.getMessage());
		} finally {

		}
		if(list.isEmpty())
			return null;
		else
			return list.get(0);
	}
	
	@Override
	public Vouchers findAll() {
		Vouchers list = new Vouchers();
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		try {
			list =  (Vouchers) execute("SELECT vouchers._id, vouchers.refitem FROM voucheruser join vouchers on vouchers._id=voucheruser.voucher_id;", parameters, new mapped_values() );
			
		} catch (NamingException ex) {
			log.warning(ex.getMessage());
		} catch (SQLException ex) {
			log.warning(ex.getMessage());
		} finally {

		}
		return list;
	}

	public Vouchers findAllForUser() {
		Vouchers list = new Vouchers();
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		try {
			list =  (Vouchers) execute("SELECT vouchers._id, vouchers.refitem, voucheruser.*"
										+ " FROM voucheruser JOIN vouchers ON vouchers._id=voucheruser.voucher_id"
										+ " LEFT OUTER JOIN uservendor on  uservendor.voucheruser_id = voucheruser._id" 
										+ " WHERE  a_user_vendor_id ISNULL", parameters, new mapped_values() );
			
		} catch (NamingException ex) {
			log.warning(ex.getMessage());
		} catch (SQLException ex) {
			log.warning(ex.getMessage());
		} finally {

		}
		return list;
	}
	
	public Vouchers findAllForVendor() {
		Vouchers list = new Vouchers();
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		try {
			list =  (Vouchers) execute("SELECT vouchers._id, vouchers.refitem, uservendor.*"
										+ " FROM voucheruser JOIN vouchers ON vouchers._id=voucheruser.voucher_id"
										+ " JOIN uservendor on voucheruser._id = uservendor.voucheruser_id", parameters, new mapped_values() );
			
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
	public Vouchers maptovalues(ResultSet rs) throws SQLException{
		  Vouchers list = new Vouchers();
			while(rs.next()){
				list.add(new Voucher(rs.getInt(1), rs.getString(2)));
			}
		  return list;
	}
	public void fill_parameters(PreparedStatement statement, Map<String, Object> parameters) throws SQLException{
		int idx = 1;
		for (Map.Entry<String, Object> e: parameters.entrySet()){
				statement.setObject(idx++, e.getValue());
			}
	}
   }
}
