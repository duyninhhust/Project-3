package vn.edu.hust.project3.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // Được quyền truy cập khi chưa login
        http.authorizeRequests().antMatchers("/login", "/","/register").permitAll();

        // Có những quyền Admin, Member sẽ được truy cập
        http.authorizeRequests().antMatchers("/user","/user/*", "/cart/*", "/checkout","/sendMail").hasAnyAuthority("admin", "member");

        //chỉ có quyền Adminmới được truy cập
        http.authorizeRequests().antMatchers("/manage-phone/*","/manage-category/*","manage-bill/*").hasAnyAuthority("admin")
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/user/admin");

        // Khi không đủ quyền truy cập sẽ bị chuyển hướng
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/error");


        // Cấu hình cho login form
        http.authorizeRequests().and().formLogin()
                .loginProcessingUrl("/authentication") // xử lí đăng nhập
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true") // <- đăng nhập thất bại chuyển hướng đến đường dẫn này
                .usernameParameter("username")
                .passwordParameter("password")

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/") // đăng xuất sẽ chuyển hướng đến đường dẫn này
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");


    }

}
