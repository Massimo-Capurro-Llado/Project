package it.uniroma3.siw.Silph.configurazioniLogin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/okRichiesta","/formRichiesta").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/InserisciFotografo/css/**").permitAll()
                .antMatchers("/home/**").permitAll()
                .antMatchers("/InserisciFotografo/images/**").permitAll()
        		.antMatchers("/images/**").permitAll()
        		.antMatchers("/uploads/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/funzionario")
                .permitAll()
                .and()
            .logout()
            	.logoutSuccessUrl("/logout")
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
             User.withDefaultPasswordEncoder()
                .username("u")
                .password("p")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}