package com.niksoft.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.niksoft.dao.GenericDataAccessService.IMapped_Values;

public class PayDAService extends GenericDataAccessService<Voucher, Integer> {

	private class mapped_values implements IMapped_Values{
		@Override
		public ArrayList<Object> maptovalues(ResultSet rs) throws SQLException{
			return null;
		}
		public void fill_parameters(PreparedStatement statement, Map<String, Object> parameters) throws SQLException{
		
		}
	}
}