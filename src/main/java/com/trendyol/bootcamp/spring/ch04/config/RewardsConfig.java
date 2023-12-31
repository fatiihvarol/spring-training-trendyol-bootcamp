package com.trendyol.bootcamp.spring.ch04.config;

import com.trendyol.bootcamp.spring.ch04.repository.account.AccountRepository;
import com.trendyol.bootcamp.spring.ch04.repository.account.JdbcAccountRepository;
import com.trendyol.bootcamp.spring.ch04.repository.restaurant.JdbcRestaurantRepository;
import com.trendyol.bootcamp.spring.ch04.repository.restaurant.RestaurantRepository;
import com.trendyol.bootcamp.spring.ch04.repository.reward.JdbcRewardRepository;
import com.trendyol.bootcamp.spring.ch04.repository.reward.RewardRepository;
import com.trendyol.bootcamp.spring.ch04.service.RewardNetwork;
import com.trendyol.bootcamp.spring.ch04.service.RewardNetworkImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * TODO-07: Perform component-scanning and run the test again
 * - Add an appropriate annotation to this class to cause component scanning.
 * - Set the base package to pick up all the classes we have annotated so far.
 * - Save all changes, Re-run the RewardNetworkTests.  It should now pass.
 */
@Configuration
@ComponentScan(basePackages = "com.trendyol.bootcamp.spring.ch04")
public class RewardsConfig {

	DataSource dataSource;

	public RewardsConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
		

	public RewardNetwork rewardNetwork(){
		return new RewardNetworkImpl(
			accountRepository(), 
			restaurantRepository(), 
			rewardRepository());
	}
	
	public AccountRepository accountRepository(){
		JdbcAccountRepository repository = new JdbcAccountRepository();
		repository.setDataSource(dataSource);
		return repository;
	}
	

	public RestaurantRepository restaurantRepository(){
		JdbcRestaurantRepository repository = new JdbcRestaurantRepository(dataSource);
		return repository;
	}
	
	public RewardRepository rewardRepository(){
		JdbcRewardRepository repository = new JdbcRewardRepository();
		repository.setDataSource(dataSource);
		return repository;
	}
	
	// TODO-02: Remove all of the @Bean methods above.
	// - Remove the code that autowires DataSource as well.
    // - Run the RewardNetworkTests test. It should fail. Why?
	
}
