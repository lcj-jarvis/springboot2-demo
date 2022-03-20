package com.mrlu.springwebflux;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {

		//just方法直接声明
		//调用just或者其他方法只是声明数据流，数据流并没有发出.
		// 只有进行订阅之后才会触发数据流，不订阅什么都不会发送.subscribe(..)发出
		Flux.just(1,2,3,4).subscribe(System.out::println);
		Mono.just(1).subscribe(System.out::println);

		//其他的方法
		/*Integer[] array = {1,2,3,4};
		Flux.fromArray(array);

		Flux.fromIterable(Arrays.asList(array));

		Flux.fromStream(Arrays.asList(array).stream());*/

	}

}
