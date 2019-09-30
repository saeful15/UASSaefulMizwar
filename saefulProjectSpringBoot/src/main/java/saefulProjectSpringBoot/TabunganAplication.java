package saefulProjectSpringBoot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TabunganAplication {
	public static void main(String[] x) {
		SpringApplication.run(TabunganAplication.class,x);
	}

}
