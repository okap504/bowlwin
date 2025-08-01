package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class WinLoseService {
	//	userList初期化
	public List<UserDTO> userListInitter() {
		List<UserDTO> list = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			list.add(new UserDTO());
		}
		return list;
	}

	//	userListチーム セット
	public List<UserDTO> userListTeamSetter(List<UserDTO> list) {
		for (int i = 0; i < 4; i++) {
			list.add(new UserDTO());
		}
		return list;
	}

	//	結果を格納
	public UserListWrapper resultSetter(UserListWrapper userListWrapper) {
		userListWrapper.setCount(userListWrapper.getCount() + 1);
		if (userListWrapper.getPinA() == userListWrapper.getPinB()) {
			for (UserDTO user : userListWrapper.getUserList()) {
				user.getHistoryList().add("±0");
			}

		} else if (userListWrapper.getPinA() > userListWrapper.getPinB()) {
			int scoreDiff = userListWrapper.getPinA() - userListWrapper.getPinB();
			int money = scoreDiff * userListWrapper.getMulti();

			for (UserDTO user : userListWrapper.getUserList()) {
				if (user.getTeam() == 0) {
					user.setMoney(user.getMoney() + money);
					user.getHistoryList().add("+" + String.valueOf(money));
				} else {
					user.setMoney(user.getMoney() - money);
					user.getHistoryList().add("-" + String.valueOf(money));
				}
			}
		} else {
			int scoreDiff = userListWrapper.getPinB() - userListWrapper.getPinA();
			int money = scoreDiff * userListWrapper.getMulti();

			for (UserDTO user : userListWrapper.getUserList()) {
				if (user.getTeam() == 1) {
					user.setMoney(user.getMoney() + money);
					user.getHistoryList().add("+" + String.valueOf(money));
				} else {
					user.setMoney(user.getMoney() - money);
					user.getHistoryList().add("-" + String.valueOf(money));
				}
			}
		}
		return userListWrapper;
	}

	public UserListWrapper listFixer(UserListWrapper userListWrapper) {
		for (UserDTO user : userListWrapper.getUserList()) {
			for (int i = 0; i < user.getHistoryList().size(); i++) {
				String history = user.getHistoryList().get(i).replaceAll("[\\[\\]]", "");
				user.getHistoryList().set(i, history);
			}
		}
		return userListWrapper;
	}

	public boolean inputNameChecker(UserListWrapper userListWrapper) {
		boolean errFlag = false;
		System.out.println("tyekku");
		if (userListWrapper.getUserList().size() == 4) {
			for (UserDTO user : userListWrapper.getUserList()) {
				if (user.getName() == null || user.getName().isEmpty()) {
					errFlag = true;
				}
			}
		} else {
			errFlag = true;
		}
		return errFlag;
	}

	public boolean inputTeamChecker(UserListWrapper userListWrapper) {
		boolean errFlag = false;
		int countA = 0;
		int countB = 0;
		for (UserDTO user : userListWrapper.getUserList()) {
			if (user.getTeam() == 0) {
				countA++;
			} else if (user.getTeam() == 1) {
				countB++;
			}
		}
		if (countA != 2 || countB != 2) {
			errFlag = true;
		}
		return errFlag;
	}

	public UserListWrapper pinSetter(UserListWrapper userListWrapper, int optional) {
		if (userListWrapper.getMulti() == 60) {
			userListWrapper.setMulti(optional);
		}
		return userListWrapper;
	}

	public boolean inputPinChecker(UserListWrapper userListWrapper) {
		boolean errFlag = false;
		if (userListWrapper.getMulti() == 0) {
			errFlag = true;
		}
		return errFlag;
	}

	public UserListWrapper randomTeamSetter(UserListWrapper userListWrapper) {
		Random random = new Random();
		int randomInt1 = random.nextInt(4);
		int randomInt2 = 0;
		while (true) {
			randomInt2 = random.nextInt(4);
			if (randomInt2 == randomInt1) {
				continue;
			} else {
				break;
			}
		}
		userListWrapper.getUserList().get(randomInt1).setTeam(1);
		userListWrapper.getUserList().get(randomInt2).setTeam(1);
		return userListWrapper;
	}
}
