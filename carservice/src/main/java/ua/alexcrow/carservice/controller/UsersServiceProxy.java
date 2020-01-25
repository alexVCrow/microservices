package ua.alexcrow.carservice.controller;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="zull")
@RibbonClient(name="zull")
public interface UsersServiceProxy {

	@GetMapping("/userservice/users/status/check")
	String usersStatusCheck();
}
