package com.niksoft.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.niksoft.dao.GenericDataAccessService.IMapped_Values;

public class PayDAService extends GenericDataAccessService<PaymentTransaction, Integer> {

	private class mapped_values implements IMapped_Values{
		@Override
		public ArrayList<PaymentTransaction> maptovalues(ResultSet rs) throws SQLException{
			PaymentTransactions list = new PaymentTransactions();
			return list;
		}
		public void fill_parameters(PreparedStatement statement, Map<String, Object> parameters) throws SQLException{
		
		}
	}
}