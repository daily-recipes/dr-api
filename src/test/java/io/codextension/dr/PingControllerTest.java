package io.codextension.dr;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import io.codextension.dr.controller.HomeController;

@SpringBootTest(classes = HomeController.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PingControllerTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	// @Test
	void textPing() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/manage/info"), HttpMethod.GET,
				entity, String.class);

		String expected = "{\"build\":{\"artifact\":\"daily-recipes\",\"name\":\"daily-recipes\",\"time\":1597935970.150000000,\"version\":\"0.0.1-SNAPSHOT\",\"group\":\"io.codextension\"}}";

		try {
			JSONAssert.assertEquals(expected, response.getBody(), false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
