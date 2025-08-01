package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	private String name;
	private int team;
	private int money;
	private List<String> historyList = new ArrayList<String>();
}
