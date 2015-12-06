package com.agh.to2.model;

import java.math.BigDecimal;
import java.util.List;

public interface ITeam {
	
	public BigDecimal getCostOfTeam();
	
	public List<IEmployee> getFullMemberList();
}