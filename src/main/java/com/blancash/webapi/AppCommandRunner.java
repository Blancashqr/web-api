package com.blancash.webapi;

import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppCommandRunner implements CommandLineRunner {

  @Transactional
  @Override
  public void run(String... args) {

  }

}
