package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WinLoseController {
	
	@Autowired
	WinLoseService winLoseService;
	
	ModelAndView mav = new ModelAndView();
	
	@GetMapping("/")
	public ModelAndView index() {
		List<UserDTO> userList = winLoseService.userListInitter();
		UserListWrapper userListWrapper = new UserListWrapper();
		userListWrapper.setUserList(userList);
		mav.addObject("errMsg", null);
		mav.addObject("userListWrapper", userListWrapper);
		mav.setViewName("html/index");
		return mav;
	}
	
	@PostMapping("setting-first")
	public ModelAndView settingFirst(@ModelAttribute UserListWrapper userListWrapper){
		if(winLoseService.inputNameChecker(userListWrapper)) {
			mav.addObject("userListWrapper", userListWrapper);
			mav.addObject("errMsg", "４人の名前を入力してください");
			mav.setViewName("html/index");
			return mav;
		}
		userListWrapper = winLoseService.listFixer(userListWrapper);
		mav.addObject("errMsg", null);
		mav.addObject("userListWrapper", userListWrapper);
		mav.setViewName("html/setting");
		return mav;
	}
	
	@PostMapping("setting")
	public ModelAndView setting(@ModelAttribute UserListWrapper userListWrapper){
		userListWrapper = winLoseService.listFixer(userListWrapper);
		mav.addObject("userListWrapper", userListWrapper);
		mav.setViewName("html/setting");
		return mav;
	}
	@PostMapping("random")
	public ModelAndView random(@ModelAttribute UserListWrapper userListWrapper) {
		userListWrapper = winLoseService.listFixer(userListWrapper);
		userListWrapper = winLoseService.randomTeamSetter(userListWrapper);
		mav.addObject("userListWrapper", userListWrapper);
		mav.setViewName("html/setting");
		return mav;
	}
	@PostMapping("battle")
	public ModelAndView buttle(@ModelAttribute UserListWrapper userListWrapper, @RequestParam(name = "optional",required = false, defaultValue = "0") int optional) {
		if(winLoseService.inputTeamChecker(userListWrapper)) {
			mav.addObject("userListWrapper", userListWrapper);
			mav.addObject("errMsg", "チームは２人になるよう選択してください");
			mav.setViewName("html/setting");
			return mav;
		}
		userListWrapper = winLoseService.pinSetter(userListWrapper, optional);
		if(winLoseService.inputPinChecker(userListWrapper)) {
			mav.addObject("userListWrapper", userListWrapper);
			mav.addObject("errMsg", "ピン数を正しく選択してください");
			mav.setViewName("html/setting");
			return mav;
		}
		userListWrapper = winLoseService.listFixer(userListWrapper);
		mav.addObject("errMsg", null);
		mav.addObject("userListWrapper", userListWrapper);
		mav.setViewName("html/battle");
		return mav;
	}
	@PostMapping("result")
	public ModelAndView result(@ModelAttribute UserListWrapper userListWrapper) {
		
		userListWrapper = winLoseService.listFixer(userListWrapper);
		userListWrapper = winLoseService.resultSetter(userListWrapper);
		
		for(String history:userListWrapper.getUserList().get(0).getHistoryList()) {
			System.out.println(history);
		}
		
		mav.addObject("userListWrapper", userListWrapper);
		mav.setViewName("html/setting");
		return mav;
	}
	
}
