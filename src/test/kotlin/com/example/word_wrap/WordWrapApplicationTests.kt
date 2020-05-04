package com.example.word_wrap

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import java.net.URI


@SpringBootTest(classes = [WordWrapApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
	@LocalServerPort
	private val port = 0

	@Autowired
	private val restTemplate: TestRestTemplate? = null

	@Test
	fun testTransformGET() {
		val responseEntity = restTemplate
				?.getForEntity(URI("http://localhost:$port/transform?maxWidth=6&input=aaa%20bb%20cc%20ddddd&API_KEY=123"), String::class.java)

		assertEquals(200, responseEntity?.statusCodeValue)
		assertEquals("{\"input\":\"aaa bb cc ddddd\",\"output\":\"aaa bb\\ncc\\nddddd\"}", responseEntity?.body)
	}
}
