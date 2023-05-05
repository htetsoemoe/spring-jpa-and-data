package com.jdc.jpql.dto;

public record SaleProductCountByTownship(
		int townshipId,
		String township,
		int productId,
		String product,
		int price,
		long count
		) {

}
