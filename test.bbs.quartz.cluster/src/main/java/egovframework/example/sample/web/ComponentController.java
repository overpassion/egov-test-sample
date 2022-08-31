/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.example.sample.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;
import egovframework.example.sample.web.ComponentController.UserException;
import lombok.extern.slf4j.Slf4j;

/**
 * @Class Name : EgovSampleController.java
 * @Description : EgovSample Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Slf4j
@Controller
public class ComponentController {

	@RequestMapping(value = "/mytest.do")
	//@ResponseBody
	public String selectSampleList(@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		model.addAttribute("sampleVO", new SampleVO());
		
		
		try {
			test();
		} catch (UserException e) {
			log.debug("1 - " + e.getMessage());
		} catch (Exception e) {
			log.debug("2 - " + e.getClass());
		}

		return "sample/egovSampleRegister";
	}
	
	private void test() throws Exception {
		throw new UserException("test message!");
	}
	
	class UserException extends Exception {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	    public UserException() {
	        super();
	    }

		public UserException(String a) {
	        super(a);
	    }
	}

}
