package pt.technic.apps.minesfinder;

import java.util.ArrayList;

public class PlayerCache {
	int newPlayerRanking = 99;

	ArrayList<Player> list = new ArrayList<>();
	private static PlayerCache instance;

	private PlayerCache() {
	};

	public static synchronized PlayerCache getInstance() {
		if (instance == null) {
			instance = new PlayerCache();
		}
		return instance;
	}

	public void addPlayer(Player user) {
		if (user == null)
			throw new NullPointerException("Player 객체가 비었습니다.");
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i).getScore() < user.getScore()) {
				newPlayerRanking = i;
				list.add(i, user);
				list.remove(21);
			}
		}
	}

	public void setRecordWithList() {
		

	}

	public void getRecordWithList() {
		// 생략 가능?? 
	}

	public ArrayList getList() {
		return list;
	}

	public void setList(ArrayList list) {
		this.list = list;
	}

}
