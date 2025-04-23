package hello.hello_spring;

import hello.hello_spring.aop.TimeTraceAop;
import hello.hello_spring.repository.*;
import hello.hello_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Time;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;
    private EntityManager em;
    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource,EntityManager em , MemberRepository memberRepository){
        this.dataSource = dataSource;
        this.em=em;
        this.memberRepository=memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }
}