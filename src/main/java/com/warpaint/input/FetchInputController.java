package com.warpaint.input;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warpaint.common.RestResult;
import com.warpaint.input.model.Input;
import com.warpaint.storage.StorageService;

@RestController
public class FetchInputController {

	@Autowired
	private FetchInputService fetchInputService;

	@Autowired
	private StorageService storageService;

	@RequestMapping("/fetch")
	public RestResult fetchInput() throws IOException {
		long start = System.currentTimeMillis();
		storageService.setInput(fetchInputService.fetchInput());
		long time = System.currentTimeMillis() - start;
		return new RestResult("ok", time);
	}

	@RequestMapping("/input")
	public Input showInput() throws IOException {
		Input input = storageService.getInput();
		if (input == null) {
			throw new IllegalStateException("Please fetch input first.");
		}
		return input;
	}

}
