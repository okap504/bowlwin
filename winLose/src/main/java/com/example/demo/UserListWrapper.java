package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserListWrapper {
	private List<UserDTO> userList = new ArrayList<>();
	private int multi;
	private int count = 1;
	private int pinA;
	private int pinB;
}
